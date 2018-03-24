package com.huyu.action;

import com.huyu.entity.Baobiao;
import com.huyu.entity.FazhanOrder;
import com.huyu.entity.Receipt_Payment;
import com.huyu.entity.User;
import com.huyu.service.FazhanOrderService;
import com.huyu.service.Receipt_PaymentService;
import com.huyu.service.UserService;
import com.huyu.util.page;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adminFazhanOrder")
public class FazhanOrderAction {
    @Resource
    private FazhanOrderService service;
    @Resource
    private UserService userService;
    @Resource
    private Receipt_PaymentService rpservice;
    @RequestMapping("/baobiao")
    public String baobiao(HttpServletResponse response) throws IOException {
        List<Baobiao> list=service.baobiao();
        JSONArray jsonArray = JSONArray.fromObject(list);
        response.getWriter().print(jsonArray);
        return null;
    }
    @RequestMapping("/listPage")
    public String listPage(String name,String curpage,HttpServletResponse response) throws IOException {
        name = name == null ? "" : name;
        int count = service.count(name);
        page page = new page(curpage, count, 10);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("startRecord", page.getStartRecord());
        map.put("pageSize", page.getPageSize());
        map.put("name", name);
        List<FazhanOrder> list = service.listPage(map);
        for (FazhanOrder order:list) {
            User user = userService.getid(order.getUserId());
            if(user!=null){
                order.setName(user.getName());
                order.setPhone(user.getPhone());
            }

        }
        map.put("page", page);
        map.put("list", list);
        map.put("count", count);
        JSONArray json = JSONArray.fromObject(map);
        response.getWriter().print(json);
        return null;
    }
    @RequestMapping("/fenhongList")
    public String fenhongList(String name,String curpage,HttpServletResponse response) throws IOException {
        name = name == null ? "" : name;
        int count = rpservice.countf(name);
        page page = new page(curpage, count, 10);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("startRecord", page.getStartRecord());
        map.put("pageSize", page.getPageSize());
        map.put("name", name);
        List<Receipt_Payment> list = rpservice.listPage(map);
        for (Receipt_Payment order:list) {
            User user = userService.getid(order.getUser_id());
            if(user!=null){
                order.setName(user.getName());
                order.setPhone(user.getPhone());
            }

        }
        map.put("page", page);
        map.put("list", list);
        map.put("count", count);
        JSONArray json = JSONArray.fromObject(map);
        response.getWriter().print(json);
        return null;
    }
}

