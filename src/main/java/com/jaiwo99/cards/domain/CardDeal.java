package com.jaiwo99.cards.domain;

/**
 * @author liang - jaiwo99@gmail.com
 */
public abstract class CardDeal extends BaseEntity {

    private String card;

    protected CardDeal() {
    }

    protected CardDeal(String card) {
        this.card = card;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
