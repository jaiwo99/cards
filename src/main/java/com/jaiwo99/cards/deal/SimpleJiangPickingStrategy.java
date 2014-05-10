package com.jaiwo99.cards.deal;

import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.domain.JiangPicking;
import com.jaiwo99.cards.repository.JiangPickingRepository;
import com.jaiwo99.cards.repository.JiangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Component
public class SimpleJiangPickingStrategy extends AbstractJiangPickingStrategy {

    private static final Logger logger = LoggerFactory.getLogger(SimpleJiangPickingStrategy.class);

    @Autowired
    private JiangRepository jiangRepository;

    @Autowired
    private JiangPickingRepository jiangPickingRepository;

    @Override
    public List<Jiang> choose() {
        final List<Jiang> list = list();
        if(list.size() < getJiangPickingCount()) {
            logger.info("Expecting {} Jiang cards, but only {} left", getJiangPickingCount(), list.size());
            return Collections.emptyList();
        }
        return shuffle(list).subList(0, getJiangPickingCount());
    }

    @Override
    public List<Jiang> shuffle(final List<Jiang> jiangList) {
        Collections.shuffle(jiangList);
        return jiangList;
    }

    @Override
    public List<Jiang> list() {
        final List<String> picked = jiangPickingRepository.findAll().stream().map(JiangPicking::getJiang).collect(Collectors.toList());
        return jiangRepository.findAll().stream().filter(x -> !picked.contains(x.getId())).collect(Collectors.toList());
    }
}
