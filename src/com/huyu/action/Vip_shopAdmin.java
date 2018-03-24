package com.huyu.action;

import com.huyu.entity.Goods;
import com.huyu.entity.Picture;
import com.huyu.entity.User;
import com.huyu.entity.Vip_shop;
import com.huyu.service.GoodsService;
import com.huyu.service.PictureService;
import com.huyu.service.UserService;
import com.huyu.service.Vip_shopService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/adminVip_shop")
public class Vip_shopAdmin {
    @Resource
    private Vip_shopService service;
    @Resource
    private UserService userService;
    private PictureService pictureService;
    private GoodsService goodsService;

    public PictureService getPictureService() {
        return pictureService;
    }
    @Resource
    public void setPictureService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    public GoodsService getGoodsService() {
        return goodsService;
    }
    @Resource
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }
    @RequestMapping("/add")
    public String add(Vip_shop vip_shop){
        service.add(vip_shop);
        return "redirect:../main/activity/vip_shop_manage.html";
    }
    @RequestMapping("/list")
    public void list(HttpServletResponse response) throws IOException {
        List<Vip_shop> list=service.list();
        JSONArray json=JSONArray.fromObject(list);
        response.getWriter().print(json);
    }
    @RequestMapping("/del")
    public String del(Integer id ){
        service.del(id);
        return "redirect:../main/activity/vip_shop_manage.html";
    }
    @RequestMapping("/update")
    public void update(Vip_shop vip_shop){
        service.update(vip_shop);
    }
    @RequestMapping("/show")
    public String show(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login";
        }else {
            User user1=userService.getid(user.getId());
            if(user1.getMember()==1){
                List<Vip_shop> list=service.list();
                request.setAttribute("vipshopList",list);
                return "vipshop";
            }else {
                //获取banner图片
                List<Picture> bannerList = pictureService.list(0);
                //获取最新商品集合
                List<Goods> newGoodsList = goodsService.listBynew();
                //获取推荐商品集合
                List<Goods> recommendGoodsList = goodsService.listByrecommend();
                //获取热销商品集合
                List<Goods> hotGoodsList = goodsService.listByhot();
                //获取猜你喜欢集合
                List<Goods> goodsByClickNum = goodsService.getGoodsByClickNum();
                request.setAttribute("bannerList", bannerList);
                request.setAttribute("index_", "ok");
                request.setAttribute("newGoodsList", newGoodsList);
                request.setAttribute("recommendGoodsList", recommendGoodsList);
                request.setAttribute("hotGoodsList", hotGoodsList);
                request.setAttribute("goodsByClickNum", goodsByClickNum);
                request.setAttribute("message","进入VIP商城需开通VIP");
                return "index";
            }
        }
    }
}
