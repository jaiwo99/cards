package com.jaiwo99.cards.deal;

import com.jaiwo99.cards.repository.CardDealRepository;
import com.jaiwo99.cards.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhou - kevinzcf@gmail.com
 */
public abstract class AbstractRoleDealStrategy implements RoleDealStrategy {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CardDealRepository cardDealRepository;

    @Override
    public void reset() {
        cardDealRepository.deleteAll();
    }


}
