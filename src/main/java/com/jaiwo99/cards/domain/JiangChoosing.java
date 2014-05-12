package com.jaiwo99.cards.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Document(collection = "jiang_choosing")
public class JiangChoosing extends CardDeal implements Serializable {

    private static final long serialVersionUID = 1l;

    public JiangChoosing() {
    }

    public JiangChoosing(String card) {
        super(card);
    }
}
