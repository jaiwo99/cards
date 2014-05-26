package com.jaiwo99.cards.service;

import com.jaiwo99.cards.domain.Role;

/**
 * @author zhou - kevinzcf@gmail.com
 */
public interface RoleDealService {

    Role pick();
    void reset();
}
