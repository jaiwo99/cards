package com.jaiwo99.cards.controller;

import com.jaiwo99.cards.Application;
import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.repository.JiangPickingRepository;
import com.jaiwo99.cards.repository.JiangRepository;
import org.junit.After;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author liang - jaiwo99@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public abstract class AbstractControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(AbstractControllerTest.class);

    @Autowired
    private JiangRepository jiangRepository;

    @Autowired
    private JiangPickingRepository jiangPickingRepository;

    @Value("${host.name}")
    private String host;

    @Value("${server.port}")
    private String serverPort;

    @After
    public void cleanUp() throws Exception {
        logger.debug("Doing clean up job after test");
        jiangRepository.deleteAll();
        jiangPickingRepository.deleteAll();
    }

    protected void generateJiang(int count) {
        while(count-- > 0) {
            generateJiang();
        }
    }

    protected String urlWrapper(String suffix) {
        final String url = "http://" + host + ":" + serverPort + suffix;
        logger.debug("urlWrapper built URL: {}", url);
        return url;
    }

    protected Jiang generateJiang() {
        final String name = randomName();
        logger.debug("Generating Jiang[name:{}]", name);
        return jiangRepository.save(new Jiang(name));
    }

    protected HttpHeaders jsonHeader() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    private String randomName() {
        return "generated-jiang:" + UUID.randomUUID().toString();
    }
}
