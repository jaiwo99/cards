package com.jaiwo99.cards.repository;

import com.jaiwo99.cards.domain.Role;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author zhou - kevinzcf@gmail.com
 */
public interface RoleRepository extends MongoRepository<Role, String> {

}
