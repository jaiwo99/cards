package com.jaiwo99.cards.service;

import com.jaiwo99.cards.deal.JiangPickingStrategy;
import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.domain.JiangPicking;
import com.jaiwo99.cards.repository.JiangPickingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Service
public class JiangPickingServiceImpl implements JiangPickingService {

    @Autowired
    private JiangPickingRepository jiangPickingRepository;

    @Autowired
    private JiangPickingStrategy jiangPickingStrategy;

    @Override
    public List<Jiang> listRest() {
        return jiangPickingStrategy.list();
    }

    @Override
    public List<Jiang> choose() {
        return jiangPickingStrategy.choose();
    }

    @Override
    public Jiang pick(String id) {
        return jiangPickingStrategy.pick(id);
    }

    @Override
    public List<JiangPicking> listPicked() {
        return jiangPickingRepository.findAll();
    }

    @Override
    public void reset() {
        jiangPickingStrategy.reset();
    }
}
