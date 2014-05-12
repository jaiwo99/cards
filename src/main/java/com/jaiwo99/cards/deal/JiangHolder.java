package com.jaiwo99.cards.deal;

import com.jaiwo99.cards.domain.Jiang;

import java.util.List;

/**
 * @author liang - jaiwo99@gmail.com
 */
public interface JiangHolder {

    Jiang getMajor();

    Jiang getMinor();

    List<Jiang> getSelection();
}
