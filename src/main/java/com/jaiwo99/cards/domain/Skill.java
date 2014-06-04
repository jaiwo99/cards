package com.jaiwo99.cards.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Document(collection = "skill")
public class Skill extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1l;

    private SkillType type;
    private String name;
    private String description;

    public SkillType getType() {
        return type;
    }

    public void setType(SkillType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
