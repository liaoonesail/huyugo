package com.huyu.service;

import com.huyu.dao.FazhanjinDao;
import com.huyu.entity.Fazhanjin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service("fazhanjinService")
@Transactional
public class FazhanjinService {
    @Resource
    private FazhanjinDao dao;
    public Fazhanjin getFazhanjin() {
        List<Fazhanjin> list=dao.getfazhanjin();
        return list.get(0);
    }

    public void update(Fazhanjin fazhanjin) {
        dao.update(fazhanjin);
    }
}
