package com.jaiwo99.cards.deal;

import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.repository.CardDealRepository;
import com.jaiwo99.cards.repository.JiangRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhou - kevinzcf@gmail.com
 */
@Component
public class SimpleRoleDealStrategy extends AbstractRoleDealStrategy {

    private static final Logger logger = LoggerFactory.getLogger(SimpleRoleDealStrategy.class);

    @Autowired
    private JiangRepository RoleRepository;

    @Autowired
    private CardDealRepository cardDealRepository;

	@Override
	public List<Jiang> choose() {
		// TODO Auto-generated method stub
		return null;
	}

}
