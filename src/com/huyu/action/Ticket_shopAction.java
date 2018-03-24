package com.huyu.action;

import com.huyu.entity.*;
import com.huyu.service.GoodsService;
import com.huyu.service.PictureService;
import com.huyu.service.Ticket_shopService;
import com.huyu.service.UserService;
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
@RequestMapping("/adminTicket_shop")
public class Ticket_shopAction {
    @Resource
    private Ticket_shopService service;

    @RequestMapping("/add")
    public String add(Ticket_shop ticket_shop) {
        service.add(ticket_shop);
        return "redirect:../main/activity/ticket_shop_manage.html";
    }

    @RequestMapping("/list")
    public void list(HttpServletResponse response) throws IOException {
        List<Ticket_shop> list = service.list();
        JSONArray json = JSONArray.fromObject(list);
        response.getWriter().print(json);
    }

    @RequestMapping("/del")
    public String del(Integer id) {
        service.del(id);
        return "redirect:../main/activity/ticket_shop_manage.html";
    }

    @RequestMapping("/update")
    public void update(Ticket_shop ticket_shop) {
        service.update(ticket_shop);
    }

    @RequestMapping("/show")
    public String show(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login";
        } else {
                List<Ticket_shop> list = service.list();
                request.setAttribute("ticketshop", list);
                return "ticketshop";
        }
    }
}
