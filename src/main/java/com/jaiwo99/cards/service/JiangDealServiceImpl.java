package com.jaiwo99.cards.service;

import com.jaiwo99.cards.deal.JiangDealStrategy;
import com.jaiwo99.cards.domain.Jiang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Service
public class JiangDealServiceImpl implements JiangDealService {

    @Autowired
    private JiangDealStrategy jiangDealStrategy;

    @Override
    public List<Jiang> choose() {
        return jiangDealStrategy.choose();
    }

    @Override
    public Jiang pick(String id) {
        return jiangDealStrategy.pick(id);
    }

    @Override
    public List<Jiang> listNew() {
        return jiangDealStrategy.listNew();
    }

    @Override
    public List<Jiang> listChosen() {
        return jiangDealStrategy.listChosen();
    }

    @Override
    public List<Jiang> listPicked() {
        return jiangDealStrategy.listPicked();
    }

    @Override
    public void reset() {
        jiangDealStrategy.reset();
    }
}
