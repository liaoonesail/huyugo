package com.huyu.service;

import com.huyu.dao.Integral_shopDao;
import com.huyu.entity.Integral_shop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class Integral_shopService {
    @Resource
    private Integral_shopDao dao;
    public void add(Integral_shop integral_shop) {
        dao.add(integral_shop);
    }

    public List<Integral_shop> list() {
        return dao.list();
    }

    public void del(Integer id) {
        dao.del(id);
    }

    public void update(Integral_shop integral_shop) {
        dao.update(integral_shop);
    }

    public Integral_shop getgoods_id(int goods_id) {
        return dao.getgoods_id(goods_id);
    }
}
