package com.jaiwo99.cards.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Document(collection = "jiang_picking")
public class JiangPicking extends Picking implements Serializable {

    private static final long serialVersionUID = 2l;

    private String jiang;

    public JiangPicking() {}

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
