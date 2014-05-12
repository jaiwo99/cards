package com.jaiwo99.cards.domain;

import com.jaiwo99.cards.deal.CardStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Document(collection = "card_deal")
public class CardDeal extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2l;

    private String card;

    private CardStatus status;

    public CardDeal() {
    }

    public CardDeal(String card, CardStatus status) {
        this.card = card;
        this.status = status;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }
}
