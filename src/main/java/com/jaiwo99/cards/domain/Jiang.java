package com.jaiwo99.cards.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Document(collection = "jiang")
public class Jiang extends Card {

    public Jiang(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
