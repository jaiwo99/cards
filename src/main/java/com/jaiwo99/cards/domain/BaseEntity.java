package com.jaiwo99.cards.domain;

import org.springframework.data.annotation.Id;

/**
 * @author liang - jaiwo99@gmail.com
 */
public abstract class BaseEntity {

    @Id
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
