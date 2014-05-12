package com.jaiwo99.cards.repository;

import com.jaiwo99.cards.domain.CardStatus;
import com.jaiwo99.cards.domain.CardDeal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author liang shi
 * @date 2014/May/12
 */
public interface CardDealRepository extends MongoRepository<CardDeal, String> {
    CardDeal findByCard(String card);
    List<CardDeal> findByStatus(CardStatus status);
}
