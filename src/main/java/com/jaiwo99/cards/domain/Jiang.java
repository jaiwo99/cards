package com.jaiwo99.cards.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Document(collection = "jiang")
public class Jiang extends Card implements Serializable {

    private static final long serialVersionUID = 2l;

    public Jiang() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jiang jiang = (Jiang) o;

        if (!id.equals(jiang.id)) return false;
        if (!name.equals(jiang.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
