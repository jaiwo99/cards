package com.jaiwo99.cards.domain;

import org.apache.commons.lang3.Validate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public Jiang(String name, Kingdom kingdom, int health, String picName, Skill... skills) {
        super(picName);
        Validate.notNull(name, "Jiang must have a name");
        Validate.notNull(kingdom, "Kingdom must be given");
        Validate.isTrue(health > 0, "health must be positive number");
        Validate.notNull(skills, "skills must not be null");

        this.name = name;
        this.kingdom = kingdom;
        this.health = health;
        this.skills = Arrays.asList(skills);
    }

    private String name;

    @DBRef
    private List<Skill> skills = new ArrayList<>();

    private Kingdom kingdom;

    private Integer health = Integer.valueOf(0);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jiang jiang = (Jiang) o;

        if (!id.equals(jiang.id)) return false;
        if (!name.equals(jiang.name)) return false;
        if (!picName.equals(jiang.picName)) return false;
        if (kingdom != jiang.kingdom) return false;
        if (health != jiang.health) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, picName, kingdom, health);
    }
}
