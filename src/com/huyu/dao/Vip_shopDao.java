package com.huyu.dao;

import com.huyu.entity.Vip_shop;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class Vip_shopDao {
    @Resource
    private SqlSessionTemplate sm;
    public void add(Vip_shop vip_shop) {
        sm.insert("com.huyu.entity.Vip_shop.add",vip_shop);
    }

    public List<Vip_shop> list() {
        return sm.selectList("com.huyu.entity.Vip_shop.list");
    }

    public void del(Integer id) {
        sm.delete("com.huyu.entity.Vip_shop.del",id);
    }

    public void update(Vip_shop vip_shop) {
        sm.update("com.huyu.entity.Vip_shop.update",vip_shop);
    }

    public Vip_shop getgoods_id(int goods_id) {
        return sm.selectOne("com.huyu.entity.Vip_shop.getgoods_id",goods_id);
    }
}
