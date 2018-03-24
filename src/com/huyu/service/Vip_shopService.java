package com.huyu.service;

import com.huyu.dao.Vip_shopDao;
import com.huyu.entity.Vip_shop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class Vip_shopService {
    @Resource
    private Vip_shopDao dao;
    public void add(Vip_shop vip_shop) {
        dao.add(vip_shop);
    }

    public List<Vip_shop> list() {
        return dao.list();
    }

    public void del(Integer id) {
        dao.del(id);
    }

    public void update(Vip_shop vip_shop) {
        dao.update(vip_shop);
    }

    public Vip_shop getgoods_id(int goods_id) {
        return dao.getgoods_id(goods_id);
    }
}
