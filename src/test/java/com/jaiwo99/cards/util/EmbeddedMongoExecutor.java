package com.jaiwo99.cards.util;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Component
public class EmbeddedMongoExecutor {

    private static final Logger logger = LoggerFactory.getLogger(EmbeddedMongoExecutor.class);

    private MongodExecutable mongodExecutable;

    private MongodStarter mongodStarter = MongodStarter.getDefaultInstance();

    @Value("${mongo.embedded.port}")
    private String mongodbPort;

    @PostConstruct
    protected void start() throws IOException {
        logger.debug("Trying to start embedded mongo on port:{}", mongodbPort);
        final IMongodConfig mongodConfig = new MongodConfigBuilder().
                version(Version.Main.PRODUCTION).
                net(new Net(Integer.valueOf(mongodbPort), Network.localhostIsIPv6())).
                build();

        mongodExecutable = mongodStarter.prepare(mongodConfig);
        mongodExecutable.start();
        logger.debug("Embedded mongo successfully started on port:{}", mongodbPort);
    }

    @PreDestroy
    protected void stop() {
        logger.debug("Trying to stop embedded mongo on port:{}", mongodbPort);
        mongodExecutable.stop();
        logger.debug("Embedded mongo successfully stopped on port:{}", mongodbPort);
    }

}
