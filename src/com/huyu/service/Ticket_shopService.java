package com.huyu.service;

import com.huyu.dao.Ticket_shopDao;
import com.huyu.entity.Ticket_shop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class Ticket_shopService {
    @Resource
    private Ticket_shopDao dao;
    public void add(Ticket_shop ticket_shop) {
        dao.add(ticket_shop);
    }

    public List<Ticket_shop> list() {
        return dao.list();
    }

    public void del(Integer id) {
        dao.del(id);
    }

    public void update(Ticket_shop ticket_shop) {
        dao.update(ticket_shop);
    }

    public Ticket_shop getgoods_id(int goods_id) {
        return dao.getgoods_id(goods_id);
    }
}
