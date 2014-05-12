package com.jaiwo99.cards.service;

import com.jaiwo99.cards.domain.Jiang;

import java.util.List;

/**
 * @author liang - jaiwo99@gmail.com
 */
public interface JiangDealService {

    List<Jiang> choose();

    Jiang pick(String id);

    List<Jiang> listNew();

    List<Jiang> listChosen();

    List<Jiang> listPicked();

    void reset();
}
