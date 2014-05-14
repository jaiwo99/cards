package com.jaiwo99.cards;

import com.jaiwo99.cards.repository.CardDealRepository;
import com.jaiwo99.cards.repository.JiangRepository;
import org.junit.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

/**
 * @author liang - jaiwo99@gmail.com
 */
@WebAppConfiguration
@IntegrationTest
public abstract class AbstractControllerTest extends AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(AbstractControllerTest.class);

    @Autowired
    private JiangRepository jiangRepository;

    @Autowired
    private CardDealRepository cardDealRepository;

    @Value("${host.name}")
    private String host;

    @Value("${server.port}")
    private String serverPort;

    @After
    public void cleanUp() throws Exception {
        logger.debug("Doing clean up job after test");
        jiangRepository.deleteAll();
        cardDealRepository.deleteAll();
    }

    protected String urlWrapper(String suffix) {
        final String url = "http://" + host + ":" + serverPort + suffix;
        logger.debug("urlWrapper built URL: {}", url);
        return url;
    }

    protected HttpHeaders jsonHeader() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
