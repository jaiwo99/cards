package com.jaiwo99.cards.service;

import com.jaiwo99.cards.domain.Jiang;

import java.util.List;

/**
 * @author liang - jaiwo99@gmail.com
 */
public interface JiangAdminService {

    List<Jiang> listAll();

    Jiang save(Jiang jiang);

    void remove(String id);
}
