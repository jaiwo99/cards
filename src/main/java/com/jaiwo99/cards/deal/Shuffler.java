package com.jaiwo99.cards.deal;

import com.jaiwo99.cards.domain.Card;

import java.util.List;

/**
 * @author liang - jaiwo99@gmail.com
 */
public interface Shuffler<T extends Card> {

    List<T> shuffle(List<T> cards);
}
