package com.huyu.dao;

import com.huyu.entity.Baobiao;
import com.huyu.entity.FazhanOrder;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FazhanOrderDao {
    @Resource
    private SqlSessionTemplate sm;
    public void add(FazhanOrder order) {
        sm.insert("com.huyu.entity.FazhanOrder.add",order);
    }

    public FazhanOrder getid(int orderId) {
        return sm.selectOne("com.huyu.entity.FazhanOrder.getById",orderId);
    }

    public FazhanOrder getByOrderNum(String orderNum) {
        return sm.selectOne("com.huyu.entity.FazhanOrder.getByOrderNum",orderNum);
    }

    public void update(FazhanOrder fazhanOrder) {
        sm.update("com.huyu.entity.FazhanOrder.update",fazhanOrder);
    }

    public List<FazhanOrder> listByUserId(int userId) {
        return sm.selectList("com.huyu.entity.FazhanOrder.getByUserId",userId);
    }

    public List<FazhanOrder> getListByToday(int userId) {
        return sm.selectList("com.huyu.entity.FazhanOrder.getListByToday",userId);
    }

    public double getPayTotalByUserId(int userId) {
        return sm.selectOne("com.huyu.entity.FazhanOrder.getPayTotalByUserId",userId);
    }

    public FazhanOrder getFirstById(int id) {
        return sm.selectOne("com.huyu.entity.FazhanOrder.getFirstById",id);
    }

    public List<Baobiao> baobiao() {
        return sm.selectList("com.huyu.entity.FazhanOrder.baobiao");
    }

    public int count(String name) {
        return sm.selectOne("com.huyu.entity.FazhanOrder.count",name);
    }

    public List<FazhanOrder> listPage(HashMap<String, Object> map) {
        return sm.selectList("com.huyu.entity.FazhanOrder.listPage",map);
    }

    public int countId(int userId) {
        return sm.selectOne("com.huyu.entity.FazhanOrder.countId",userId);
    }

    public List<FazhanOrder> pageList(HashMap<String, Object> map) {
        return sm.selectList("com.huyu.entity.FazhanOrder.pageList",map);
    }
}
