package com.jaiwo99.cards.deal;

import static com.jaiwo99.cards.domain.CardStatus.PICKED;
import static java.util.stream.Collectors.toList;

import com.jaiwo99.cards.domain.CardDeal;
import com.jaiwo99.cards.domain.Role;
import com.jaiwo99.cards.repository.CardDealRepository;
import com.jaiwo99.cards.repository.RoleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author zhou - kevinzcf@gmail.com
 */
@Component
public class SimpleRoleDealStrategy extends AbstractRoleDealStrategy {

    private static final Logger logger = LoggerFactory.getLogger(SimpleRoleDealStrategy.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CardDealRepository cardDealRepository;


    @Override
    public List<Role> listRest() {
        final List<String> used = cardDealRepository.findAll().stream().map(CardDeal::getCard).collect(toList());
        logger.debug("Found {} chosen or picked cards", used.size());
        return roleRepository.findAll().stream().filter(x -> !used.contains(x.getId())).collect(toList());
    }
    
    @Override
    public Role randomPick() {
        final List<Role> availableCards = listRest();
        if(availableCards.size() < 1) {
            throw new IllegalStateException("maximum Role number exceeded!");
        }

        Role toPick = ((Shuffler<Role>) cards -> {
            Collections.shuffle(cards);
            return cards;
        }).shuffle(availableCards).get(0);
        
        cardDealRepository.save(new CardDeal(toPick.getId(), PICKED));

        return toPick;
    }

}
