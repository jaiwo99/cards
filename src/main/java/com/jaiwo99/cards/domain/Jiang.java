package com.jaiwo99.cards.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

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
    private Integer hp;
    private String skills;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

}
