package com.huyu.action;

import com.huyu.entity.Fazhanjin;
import com.huyu.service.FazhanjinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/adminFazhanjin")
public class FazhanjinAction {
    @Resource
    private FazhanjinService service;
    @RequestMapping("getFazhanjin")
    public String getFazhanjin(HttpServletRequest request) throws IOException {
        Fazhanjin fazhanjin=service.getFazhanjin();
       request.setAttribute("fazhanjin",fazhanjin);
       return "article_manage3";
    }
    @RequestMapping("update")
    public String update(Fazhanjin fazhanjin){
        service.update(fazhanjin);
        return "redirect:/adminFazhanjin/getFazhanjin.do";
    }
}
