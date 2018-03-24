package com.huyu.action;

import com.huyu.entity.Integral_shop;
import com.huyu.service.Integral_shopService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/adminIntegral_shop")
public class Integral_shopAdmin {
    @Resource
    private Integral_shopService service;
    @RequestMapping("/add")
    public String add(Integral_shop integral_shop){
        service.add(integral_shop);
        return "redirect:../main/activity/integral_shop_manage.html";
    }
    @RequestMapping("/list")
    public void list(HttpServletResponse response) throws IOException {
        List<Integral_shop> list=service.list();
        JSONArray json=JSONArray.fromObject(list);
        response.getWriter().print(json);
    }
    @RequestMapping("/del")
    public String del(Integer id){
        service.del(id);
        return "redirect:../main/activity/integral_shop_manage.html";
    }
    @RequestMapping("/update")
    public void update(Integral_shop integral_shop){
        service.update(integral_shop);
    }
    @RequestMapping("/show")
    public String show(HttpServletRequest request){
        List<Integral_shop> list=service.list();
        request.setAttribute("integralList",list);
        return "integral";
    }
}
