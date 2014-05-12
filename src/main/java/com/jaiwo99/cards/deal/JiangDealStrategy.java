package com.jaiwo99.cards.deal;

import com.jaiwo99.cards.domain.Jiang;

import java.util.List;

/**
 * @author liang - jaiwo99@gmail.com
 */
public interface JiangDealStrategy {

    void reset();

    Jiang pick(String id);

    List<Jiang> choose();

    List<Jiang> listNew();

    List<Jiang> listChosen();

    List<Jiang> listPicked();
}
