package com.jaiwo99.cards.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Document(collection = "jiang_picking")
public class JiangPicking extends Picking {

    private String jiang;

    public JiangPicking(String jiang) {
        this.jiang = jiang;
    }


    public String getJiang() {
        return jiang;
    }

    public void setJiang(String jiang) {
        this.jiang = jiang;
    }
}
