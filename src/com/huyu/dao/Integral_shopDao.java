package com.huyu.dao;

import com.huyu.entity.Integral_shop;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class Integral_shopDao {
    @Resource
    private SqlSessionTemplate sm;
    public void add(Integral_shop integral_shop) {
        sm.insert("com.huyu.entity.Integral_shop.add",integral_shop);
    }

    public List<Integral_shop> list() {
        return sm.selectList("com.huyu.entity.Integral_shop.list");
    }

    public void del(Integer id) {
        sm.delete("com.huyu.entity.Integral_shop.del",id);
    }

    public void update(Integral_shop integral_shop) {
        sm.update("com.huyu.entity.Integral_shop.update",integral_shop);
    }

    public Integral_shop getgoods_id(int goods_id) {
        return sm.selectOne("com.huyu.entity.Integral_shop.getgoods_id",goods_id);
    }
}
