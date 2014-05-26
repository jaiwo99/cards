package com.jaiwo99.cards.service;

import com.jaiwo99.cards.deal.RoleDealStrategy;
import com.jaiwo99.cards.domain.Role;
import com.jaiwo99.cards.repository.CardDealRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhou - kevinzcf@gmail.com
 */
@Service
public class RoleDealServiceImpl implements RoleDealService {

    @Autowired
    private RoleDealStrategy roleDealStrategy;

    @Autowired
    private CardDealRepository cardDealRepository;
    
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Role pick() {
		// TODO Auto-generated method stub
		return null;
	}


}
