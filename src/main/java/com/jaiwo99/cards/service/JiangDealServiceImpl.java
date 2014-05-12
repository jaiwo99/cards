package com.jaiwo99.cards.service;

import com.jaiwo99.cards.deal.JiangDealStrategy;
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
public class JiangDealServiceImpl implements JiangDealService {

    @Autowired
    private JiangPickingRepository jiangPickingRepository;

    @Autowired
    private JiangDealStrategy jiangDealStrategy;

    @Override
    public List<Jiang> listRest() {
        return jiangDealStrategy.list();
    }

    @Override
    public List<Jiang> choose() {
        return jiangDealStrategy.choose();
    }

    @Override
    public Jiang pick(String id) {
        return jiangDealStrategy.pick(id);
    }

    @Override
    public List<JiangPicking> listPicked() {
        return jiangPickingRepository.findAll();
    }

    @Override
    public void reset() {
        jiangDealStrategy.reset();
    }
}
