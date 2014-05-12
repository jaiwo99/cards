package com.jaiwo99.cards.service;

import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.repository.JiangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Service
public class JiangAdminServiceImpl implements JiangAdminService {

    @Autowired
    private JiangRepository jiangRepository;

    @Override
    public List<Jiang> list() {
        return jiangRepository.findAll();
    }

    @Override
    public Jiang save(Jiang jiang) {
        return jiangRepository.save(jiang);
    }

    @Override
    public void remove(String id) {
        jiangRepository.delete(id);
    }
}
