package com.jaiwo99.cards.domain;

/**
 * @author liang - jaiwo99@gmail.com
 */
public abstract class Card extends BaseEntity {

    protected String picName;

    protected Card(){}

    protected Card(String picName) {
        this.picName = picName;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

}
