package com.jaiwo99.cards.deal;

import com.jaiwo99.cards.domain.CardDeal;
import com.jaiwo99.cards.domain.Role;
import com.jaiwo99.cards.repository.CardDealRepository;
import com.jaiwo99.cards.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import static com.jaiwo99.cards.domain.CardStatus.PICKED;

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

    @Override
    public Role pick(String id) {
        cardDealRepository.save(new CardDeal(id, PICKED));
        return roleRepository.findOne(id);
    }

}
