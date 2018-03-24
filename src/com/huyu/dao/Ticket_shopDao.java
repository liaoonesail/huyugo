package com.huyu.dao;

import com.huyu.entity.Ticket_shop;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class Ticket_shopDao {
    @Resource
    private SqlSessionTemplate sm;
    public void add(Ticket_shop ticket_shop) {
        sm.insert("com.huyu.entity.Ticket_shop.add",ticket_shop);
    }

    public List<Ticket_shop> list() {
        return sm.selectList("com.huyu.entity.Ticket_shop.list");
    }

    public void del(Integer id) {
        sm.delete("com.huyu.entity.Ticket_shop.del",id);
    }

    public void update(Ticket_shop ticket_shop) {
        sm.update("com.huyu.entity.Ticket_shop.update",ticket_shop);
    }

    public Ticket_shop getgoods_id(int goods_id) {
        return sm.selectOne("com.huyu.entity.Ticket_shop.getgoods_id",goods_id);
    }
}
