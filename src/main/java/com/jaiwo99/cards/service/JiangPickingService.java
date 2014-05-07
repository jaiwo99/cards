package com.jaiwo99.cards.service;

import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.domain.JiangPicking;

import java.util.List;

/**
 * @author liang - jaiwo99@gmail.com
 */
public interface JiangPickingService {

    List<Jiang> listRest();

    List<Jiang> choose();

    Jiang pick(String id);

    List<JiangPicking> listPicked();

    void reset();
}
