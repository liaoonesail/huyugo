package com.huyu.controller;

import com.huyu.entity.FazhanOrder;
import com.huyu.entity.Fazhanjin;
import com.huyu.entity.Order;
import com.huyu.entity.User;
import com.huyu.service.FazhanOrderService;
import com.huyu.service.FazhanjinService;
import com.huyu.service.UserService;
import com.huyu.util.OrderNum;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fazhanOrder")
public class FazhanOrderController {
    @Resource
    private FazhanOrderService service;
    @Resource
    private UserService userService;
    @Resource
    private FazhanjinService fazhanjinService;
    @RequestMapping("/pay")
    public String pay(String title,String yue, HttpServletRequest request,HttpServletResponse response)  {
        System.out.println("yue:"+yue);
        double payMoney=0;
        double yue1=0;
        if(yue==null||"".equals(yue)){
            yue="0";
        }
        yue1=Double.parseDouble(yue);
        if(title.equals("1")) payMoney=100;
        else if(title.equals("2")) payMoney=500;
        else if(title.equals("3")) payMoney=1000;
        else if(title.equals("4")) payMoney=5000;
        else if(title.equals("5")) payMoney=10000;
        else if(title.equals("6")) payMoney=50000;
        else if(title.equals("7")) payMoney=100000;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login";
        }
        Fazhanjin fazhanjin=fazhanjinService.getFazhanjin();
        FazhanOrder order=new FazhanOrder();
        order.setUserId(user.getId());
        order.setDays((int) (100*(fazhanjin.getChongzhi()+1)));
        order.setPayMoney(payMoney);
        order.setYue(yue1);
        order.setOrderNum(OrderNum.getOrderNum());
        order.setOrderTime(OrderNum.getregTime());
        order.setFazhanjin(payMoney*(1+fazhanjin.getChongzhi()));
        order.setStatus(0);
        service.add(order);
        if(yue1==payMoney){
         service.update(order);
           return "redirect:/fazhanOrder/showFazhanjin.do";
        }else {
            request.setAttribute("orderId", order.getId());
            request.setAttribute("type",2);
            return "showCode2";
        }
    }
    @RequestMapping("/istrue")
    public String isture(String title, HttpServletRequest request,HttpServletResponse response) throws IOException {
        double payMoney=0;
        if(title.equals("1")) payMoney=100;
        else if(title.equals("2")) payMoney=500;
        else if(title.equals("3")) payMoney=1000;
        else if(title.equals("4")) payMoney=5000;
        else if(title.equals("5")) payMoney=10000;
        else if(title.equals("6")) payMoney=50000;
        else if(title.equals("7")) payMoney=100000;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login";
        }
        List<FazhanOrder> list=service.listByUserId(user.getId());
        double total=0;
        for (FazhanOrder f:list
                ) {
            total+=f.getPayMoney();
        }
        if(total+payMoney>100000){
            response.getWriter().print(false);

        }else {
            response.getWriter().print(true);
        }
        return null;
    }
    /**
     * 商品购买支付
     *
     * @param orderId
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/orderPay")
    public String orderPay(int orderId, HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        String result = "";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            result = "notLogin";
            response.getWriter().print(result);
            return null;
        } else {

            FazhanOrder order = service.getid(orderId);
            double amount = order.getPayMoney()-order.getYue();
            BigDecimal b   =   new   BigDecimal(amount);
            amount =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
            result = "attach=3&detail=加盟费&desc=加盟费&goodSn=" + "111"
                    + "&orderSn=" + order.getOrderNum() + "&amount=" + amount;
            // System.out.println(result);
            // result = "attach=1&detail=商品购买&desc=商品购买&goodSn=" + order.getId()
            // + "&orderSn=" + order.getOrder_num() + "&amount=0.01";
            response.getWriter().print(result);
            return null;
        }

    }

    /**
     * 获取发展金订单
     * @param response
     * @return
     */
    @RequestMapping("/showFazhanjin")
    public String shouwFazhanjin(String curpage,HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login";
        }
        User u = userService.getid(user.getId());
        Map<String,Object> orderList = service.pageList(user.getId(),curpage);
        request.setAttribute("user", u);
        request.setAttribute("orderList", orderList);
        request.setAttribute("curpage", curpage);
        return "memberCenter/right/fazhanjin";
    }
}
