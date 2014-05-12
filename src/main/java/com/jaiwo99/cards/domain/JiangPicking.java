package com.jaiwo99.cards.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Document(collection = "jiang_picking")
public class JiangPicking extends CardDeal implements Serializable {

    private static final long serialVersionUID = 2l;

    public JiangPicking() {
    }

    public JiangPicking(String card) {
        super(card);
    }
}
