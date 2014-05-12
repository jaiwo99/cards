package com.jaiwo99.cards.repository;

import com.jaiwo99.cards.domain.JiangChoosing;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author liang - jaiwo99@gmail.com
 */
public interface JiangChoosingRepository extends MongoRepository<JiangChoosing, String> {
    JiangChoosing findByCard(String jiang);
}
