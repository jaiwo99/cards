package com.jaiwo99.cards.service;

import com.jaiwo99.cards.deal.RoleDealStrategy;
import com.jaiwo99.cards.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhou - kevinzcf@gmail.com
 */
@Service
public class RoleDealServiceImpl implements RoleDealService {

    @Autowired
    private RoleDealStrategy roleDealStrategy;
    
	@Override
	public void reset() {
		roleDealStrategy.reset();		
	}

	@Override
	public Role randomPick() {
		return roleDealStrategy.randomPick();
	}


}
