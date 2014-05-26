package com.jaiwo99.cards.domain;
import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author zhou - kevinzcf@gmail.com
 */
@Document(collection = "role")
public class Role extends Card implements Serializable {

    private static final long serialVersionUID = 2l;
    
    private RoleType type;

	public RoleType getType() {
		return type;
	}

	public void setType(RoleType type) {
		this.type = type;
	}
    
}


