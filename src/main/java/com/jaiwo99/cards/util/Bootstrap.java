package com.jaiwo99.cards.util;

import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.repository.CardDealRepository;
import com.jaiwo99.cards.repository.JiangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Component
public class Bootstrap {

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    @Autowired
    private JiangRepository jiangRepository;
    @Autowired
    private CardDealRepository cardDealRepository;

    private static List<Jiang> list = new ArrayList<>();

    static {
        for(int i = 0; i < 100; i++) {
            list.add(new Jiang("jiang" + i));
        }
    }

    public void reboot() {
        start();
    }

    // temporal solution
    @PostConstruct
    private void start() {
        logger.debug("Bootstrapping...");
        logger.debug("Cleaning up DB...");
        jiangRepository.deleteAll();
        cardDealRepository.deleteAll();
        logger.debug("Clean up DB DONE...");
        jiangRepository.save(list);
        logger.debug("added {} Jiangs in DB", list.size());
    }

    @PreDestroy
    private void stop() {
        logger.debug("Shutting down");
        logger.debug("Cleaning up DB...");
        jiangRepository.deleteAll();
        cardDealRepository.deleteAll();
        logger.debug("Clean up DB DONE...");
    }
}
