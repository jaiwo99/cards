package com.jaiwo99.cards.deal;

import com.jaiwo99.cards.domain.CardDeal;
import com.jaiwo99.cards.domain.CardStatus;
import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.repository.CardDealRepository;
import com.jaiwo99.cards.repository.JiangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static com.jaiwo99.cards.domain.CardStatus.*;
import static java.util.stream.Collectors.toList;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Component
public class SimpleJiangDealStrategy extends AbstractJiangDealStrategy {

    private static final Logger logger = LoggerFactory.getLogger(SimpleJiangDealStrategy.class);

    @Autowired
    private JiangRepository jiangRepository;

    @Autowired
    private CardDealRepository cardDealRepository;

    @Override
    public List<Jiang> listNew() {
        final List<String> used = cardDealRepository.findAll().stream().map(CardDeal::getCard).collect(toList());
        logger.debug("Found {} chosen or picked cards", used.size());
        return jiangRepository.findAll().stream().filter(x -> !used.contains(x.getId())).collect(toList());
    }

    @Override
    public List<Jiang> listChosen() {
        return listByStatus(CHOSEN);
    }

    @Override
    public List<Jiang> listPicked() {
        return listByStatus(PICKED);
    }

    @Override
    public List<Jiang> choose() {
        final List<Jiang> availableCards = listNew();
        if(availableCards.size() < jiangPickingCount) {
            throw new IllegalStateException("Not enough Jiang exist!");
        }

        List<Jiang> chosen = ((Shuffler<Jiang>) cards -> {
            Collections.shuffle(cards);
            return cards;
        }).shuffle(availableCards).subList(0, jiangPickingCount);

        cardDealRepository.save(chosen.stream().map(x -> new CardDeal(x.getId(), CHOSEN)).collect(toList()));

        return chosen;
    }

    private List<Jiang> listByStatus(final CardStatus status) {
        final List<String> list = cardDealRepository.findByStatus(status).stream().map(CardDeal::getCard).collect(toList());
        logger.debug("Found {} cards with status[{}]", list.size(), status);
        return jiangRepository.findAll().stream().filter(x -> list.contains(x.getId())).collect(toList());
    }
}
