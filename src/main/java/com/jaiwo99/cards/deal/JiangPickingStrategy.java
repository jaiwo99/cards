package com.jaiwo99.cards.deal;

import com.jaiwo99.cards.domain.Jiang;

import java.util.List;

/**
 * @author liang - jaiwo99@gmail.com
 */
public interface JiangPickingStrategy {

    void reset();

    Jiang pick(String id);

    List<Jiang> choose();

    List<Jiang> shuffle(final List<Jiang> jiangList);

    List<Jiang> list();
}
