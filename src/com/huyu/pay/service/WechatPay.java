package com.huyu.pay.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huyu.entity.FazhanOrder;
import com.huyu.service.FazhanOrderService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Order;
import com.huyu.entity.Vip_order;
import com.huyu.service.OrderService;
import com.huyu.service.Vip_orderService;
import com.sean.controler.WechatPayControler;
import com.sean.util.Log;
import com.sean.util.PropertiesUtil;
import com.sean.util.SignatureUtils;
@Controller
@RequestMapping("/wechatpay")
public class WechatPay{
	@Resource
	private OrderService service;
	@Resource
	private Vip_orderService service1;
	@Resource
    private FazhanOrderService service2;
	/**
     * 获取PC端网页支付二维码
     * @param attach    商品类型（1商品支付，2会员开通）
     * @param detail    商品描述
     * @param desc      商品详情
     * @param goodSn    商品编号
     * @param orderSn   订单号
     * @param amount    金额
     * @param response
     * @throws IOException
     *下面的是在html页面显示二维码支付图片 #img 为<img alt="" src="" id="img">的ID
     *  function wechatpay(){
		 var xhr=new XMLHttpRequest();
		 xhr.open("GET","../../wechatpay/pc_pay.do?attach=1&detail=飞机杯&desc=大号飞机杯&goodSn=1&orderSn=1497519255674790337&amount=0.01",true);
		 xhr.responseType="blob";
		 xhr.onload=function(){
			if(this.status==200){
				var blob=this.response;
				var img=document.createElement("img");
				img.onload=function(e){
					window.URL.revokeObjectURL(img.src);
				};
					img.src=window.URL.createObjectURL(blob);
					$("#img").html(img);
			} 
		 };xhr.send();
		 
	 }
     * 
     */
	@RequestMapping("/pc_pay")
	public void PC_pay(String attach,String detail, String desc, String goodSn, String orderSn, String amount,HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		if("1".equals(attach)){
			desc = "商品购买";
		}else if("2".equals(attach)){
			desc = "vip购买";
		}else if("3".equals(attach)){
		    desc="发展金加盟费用";
		    detail="发展金加盟费用";
        }
		WechatPayControler wechatPayControler = new WechatPayControler();
		wechatPayControler.nativeOrder(attach,detail, desc, goodSn, orderSn, amount, request, response);// //生成二维码QRCode图片ImageIO.write(bufImg, "jpg", response.getOutputStream());
		//wechatPayControler.nativeOrder("1","帽子", "高顶帽子", "201", "201706281703592653698", "0.01", request, response);
	}
	 /**
     * 微信支付回调函数
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/callback")
    public void callBack(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        InputStream is = request.getInputStream();
        HashMap<String, String> map = new HashMap<String, String>();
       Log.info("微信回调函数:"+PropertiesUtil.getValue("wechat.properties", "wx_callback"), null);
        // 1、读取传入信息并转换为map
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(is);
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }
        String payType = "";
        String memberId = "";
        Element root = document.getRootElement();
        List<Element> list = root.elements();
        for (Element e : list) {
            if (e.getName().trim().equals("payType")) {
                payType = e.getText().trim();
            } else if (e.getName().trim().equals("memberId")) {
                memberId = e.getText().trim();
            } else {
                map.put(e.getName().trim(), e.getText().trim());
            }
        }
        is.close();
        //System.out.println(map.toString());
        // 2、克隆传入的信息并进行验签
        HashMap<String, String> signMap = (HashMap<String, String>) map.clone();
        signMap.remove("sign");
        Log.info(map.toString(), null);
        String key= PropertiesUtil.getValue("wechat.properties","wx_key");
        String sign = SignatureUtils.signature(signMap,key);
        //System.out.println(sign);
        //System.out.println(map.get("sign"));
        if (!sign.equals(map.get("sign"))) {
            Log.error( "微信支付回调函数：验签错误", null);
            return;
        }
        // 信息处理
        String result_code = map.get("result_code");
        String attach=map.get("attach");//原样返回  1商品购买  2会员开通  
        try {

            if ("SUCCESS".equals(result_code)) {
                //由于微信后台会同时回调多次，所以需要做防止重复提交操作的判断
                //此处放防止重复提交操作
            	String order_num=map.get("out_trade_no");
            	String total=map.get("total_fee");
            	Order order=service.getorder_num(order_num);
            	Vip_order vip_order=service1.getvip_order(order_num);
                FazhanOrder fazhanOrder=service2.getByOrderNum(order_num);
            	if(order!=null&&"1".equals(attach)&&Integer.parseInt(total)==order.getTotal()*100){
            		//购买商品返回
            		order.setStatus(1);
            		service.update(order);
            	}else if(vip_order!=null&&"2".equals(attach)&&Integer.parseInt(total)==vip_order.getTotal()*100){
            		//开通会员返回
            		vip_order.setStatus(1);
            		service1.update(vip_order);
            		
            	}else if(fazhanOrder!=null&&"3".equals(attach)&&Integer.parseInt(total)+fazhanOrder.getYue()*100==fazhanOrder.getPayMoney()*100){
            	    //购买发展金
                    fazhanOrder.setStatus(1);
                    service2.update(fazhanOrder);
                }

            } else if ("FAIL".equals(result_code)) {

            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        try {
           //进行业务逻辑操作

        } catch (Exception e) {
            e.printStackTrace();
            Log.error("回调用户中心错误", e);
        }


        // 返回信息，防止微信重复发送报文
        String result = "<xml>"
                + "<return_code><![CDATA[SUCCESS]]></return_code>"
                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml>";
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.print(result);
        out.flush();
        out.close();

    }
}
