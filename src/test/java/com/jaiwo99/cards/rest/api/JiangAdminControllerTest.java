package com.jaiwo99.cards.rest.api;

import com.google.gson.Gson;
import com.jaiwo99.cards.AbstractControllerTest;
import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.repository.JiangRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author liang - jaiwo99@gmail.com
 */
public class JiangAdminControllerTest extends AbstractControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(JiangAdminControllerTest.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    @Autowired
    private JiangRepository jiangRepository;

    @Test
    public void list_should_list_all_jiang() throws Exception {
        generateJiang(10);
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlWrapper("/admin/jiang/list"), String.class);

        List<String> payload = JsonPath.read(responseEntity.getBody(), "$.payload[*]");

        logger.debug("HTTP Status of response: {}", responseEntity.getStatusCode());

        assertThat(responseEntity.getStatusCode().value(), is(HttpStatus.OK.value()));
        assertThat(payload.size(), is(10));
    }

    @Test
    public void save_should_be_able_to_add_jiang() throws Exception {
        final String jsonString = gson.toJson(new Jiang("addedJiang"));

        logger.debug("Json string of request: {}", jsonString);

        final ResponseEntity<String> responseEntity = restTemplate.exchange(urlWrapper("/admin/jiang/save"), HttpMethod.POST, new HttpEntity<>(jsonString, jsonHeader()), String.class);

        logger.debug("Response Body: {}", responseEntity.getBody());

        final String id = JsonPath.read(responseEntity.getBody(), "$.payload.id");
        final String name = JsonPath.read(responseEntity.getBody(), "$.payload.name");

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(id, notNullValue());
        assertThat(name, is("addedJiang"));

        assertThat(jiangRepository.findOne(id).getName(), is(name));
    }

    @Test
    public void save_should_be_able_to_update_jiang() throws Exception {
        final Jiang jiang = generateJiang();

        jiang.setName("newJiangName");

        final String jsonString = gson.toJson(jiang);

        logger.debug("Json string of request: {}", jsonString);

        final ResponseEntity<String> responseEntity = restTemplate.exchange(urlWrapper("/admin/jiang/save"), HttpMethod.POST, new HttpEntity<>(jsonString, jsonHeader()), String.class);

        logger.debug("Response Body: {}", responseEntity.getBody());

        final String id = JsonPath.read(responseEntity.getBody(), "$.payload.id");
        final String name = JsonPath.read(responseEntity.getBody(), "$.payload.name");

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(id, notNullValue());
        assertThat(name, is("newJiangName"));

        assertThat(jiangRepository.findOne(id).getName(), is(name));
    }

    @Test
    public void remove_should_be_able_to_remove_jiang() throws Exception {
        generateJiang(10);
        final Jiang jiang = generateJiang();

        assertThat(jiangRepository.findAll().size(), is(11));

        restTemplate.postForEntity(urlWrapper("/admin/jiang/remove/"+jiang.getId()), new HttpEntity<Void>(null, jsonHeader()), String.class);

        assertThat(jiangRepository.findAll().size(), is(10));
        assertThat(jiangRepository.findOne(jiang.getId()), is(nullValue()));
    }

}