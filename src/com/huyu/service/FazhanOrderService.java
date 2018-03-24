package com.huyu.service;

import com.huyu.dao.FazhanOrderDao;
import com.huyu.entity.*;
import com.huyu.threadDay.FazhanSystem;
import com.huyu.util.OrderNum;
import com.huyu.util.page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("fservice")
@Transactional
public class FazhanOrderService {
    @Resource
    private FazhanOrderDao dao;
    @Resource
    private UserService userService;
    @Resource
    private FazhanjinService fazhanjinService;
    @Resource
    private Receipt_PaymentService receiptPaymentService;
    public void add(FazhanOrder order) {
        dao.add(order);
    }

    public FazhanOrder getid(int orderId) {
        return dao.getid(orderId);
    }

    public FazhanOrder getByOrderNum(String order_num) {
        return dao.getByOrderNum(order_num);
    }

    public void update(FazhanOrder fazhanOrder) {
        Fazhanjin fazhanjin=fazhanjinService.getFazhanjin();
        User user = userService.getid(fazhanOrder.getUserId());
        new FazhanSystem().systemsecond(user,fazhanOrder);//调用进入发展金系统
        user.setPension(user.getPension()+fazhanOrder.getPayMoney()*fazhanjin.getYanglao());//购买者获得养老金
        user.setFazhanjin(user.getFazhanjin()+fazhanOrder.getFazhanjin());
        user.setFazhanStatus(1);
        user.setAccount(user.getAccount()-fazhanOrder.getYue());
        if(fazhanOrder.getYue()!=0){
            receiptPaymentService.add(new Receipt_Payment(0,fazhanOrder.getYue(), OrderNum.getregTime(),user.getId(),1,18,0,0,0));
            receiptPaymentService.add(new Receipt_Payment(0,fazhanOrder.getPayMoney()-fazhanOrder.getYue(), OrderNum.getregTime(),user.getId(),5,18,0,0,0));
        }
        if(user.getPensionTime()==null||"".equals(user.getPensionTime())){
            user.setPensionTime(OrderNum.getdate());
        }
        userService.update(user);//修改用户养老金
        receiptPaymentService.add(new Receipt_Payment(fazhanOrder.getPayMoney()*fazhanjin.getYanglao(),0, OrderNum.getregTime(),user.getId(),6,18,0,0,0));
        fazhanOrder.setStatus(1);
        dao.update(fazhanOrder);//修改订单状态
    }
    public void updatejintai(FazhanOrder fazhanOrder){
        dao.update(fazhanOrder);
    }

    public List<FazhanOrder> listByUserId(int userId) {

        return dao.listByUserId(userId);
    }

    private int countId(int userId) {
        return dao.countId(userId);
    }

    public List<FazhanOrder> getListByToday(int userId) {
        return dao.getListByToday(userId);
    }

    public double getPayTotalByUserId(int userId) {
        return dao.getPayTotalByUserId(userId);
    }

    public FazhanOrder getFirstById(int id) {
        return dao.getFirstById(id);
    }

    public List<Baobiao> baobiao() {
        return dao.baobiao();
    }

    public int count(String name) {
        return dao.count(name);
    }

    public List<FazhanOrder> listPage(HashMap<String, Object> map) {
        return dao.listPage(map);
    }

    public Map<String, Object> pageList(int userId, String curpage) {
        int count=countId(userId);
        page page=new page(curpage, count, 20);
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("startRecord", page.getStartRecord());
        map.put("pageSize", page.getPageSize());
        map.put("userId", userId);
        List<FazhanOrder> list = dao.pageList(map);
        map.put("page", page);
        map.put("countPage", page.getPageCount());
        map.put("list", list);
        map.put("count", count);
        return map;
    }
}
