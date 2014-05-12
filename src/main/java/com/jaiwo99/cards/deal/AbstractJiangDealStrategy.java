package com.jaiwo99.cards.deal;

import com.jaiwo99.cards.domain.CardDeal;
import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.repository.CardDealRepository;
import com.jaiwo99.cards.repository.JiangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.jaiwo99.cards.deal.CardStatus.PICKED;

/**
 * @author liang - jaiwo99@gmail.com
 */
public abstract class AbstractJiangDealStrategy implements JiangDealStrategy {

    @Autowired
    private JiangRepository jiangRepository;

    @Autowired
    private CardDealRepository cardDealRepository;

    @Value("${jiang.picking.count}")
    protected int jiangPickingCount;

    @Override
    public void reset() {
        cardDealRepository.deleteAll();
    }

    @Override
    public Jiang pick(String id) {
        cardDealRepository.save(new CardDeal(id, PICKED));
        return jiangRepository.findOne(id);
    }
}
