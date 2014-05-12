package com.jaiwo99.cards.repository;

import com.jaiwo99.cards.domain.JiangPicking;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author liang - jaiwo99@gmail.com
 */
public interface JiangPickingRepository extends MongoRepository<JiangPicking, String> {

    JiangPicking findByCard(String jiang);
}
