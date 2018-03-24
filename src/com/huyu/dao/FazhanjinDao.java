package com.huyu.dao;

import com.huyu.entity.Fazhanjin;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Repository
public class FazhanjinDao {
    @Resource
    private SqlSessionTemplate sm;
    public List<Fazhanjin> getfazhanjin() {
        return sm.selectList("com.huyu.entity.Fazhanjin.list");
    }

    public void update(Fazhanjin fazhanjin) {
        sm.update("com.huyu.entity.Fazhanjin.update",fazhanjin);
    }
}
