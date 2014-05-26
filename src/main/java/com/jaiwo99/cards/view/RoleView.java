package com.jaiwo99.cards.view;

import com.jaiwo99.cards.domain.Role;

/**
 * @author zhou - kevinzcf@gmail.com
 */
public final class RoleView {

    private final Role role;

	public RoleView(Role role) {
        this.role = role;
    }

	public Role getRole() {
		return role;
	}
}
