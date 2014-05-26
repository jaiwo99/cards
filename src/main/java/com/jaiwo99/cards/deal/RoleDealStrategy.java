package com.jaiwo99.cards.deal;

import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.domain.Role;

import java.util.List;

/**
 * @author zhou - keivnzcf@gmail.com
 */
public interface RoleDealStrategy {

    void reset();

    Role pick(String id);

    List<Jiang> choose();
}
