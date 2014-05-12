package com.jaiwo99.cards.controller;

import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.domain.JiangPicking;
import com.jaiwo99.cards.repository.JiangPickingRepository;
import com.jaiwo99.cards.repository.JiangRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class JiangPickingControllerTest extends AbstractControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JiangPickingRepository jiangPickingRepository;

    @Autowired
    private JiangRepository jiangRepository;

    @Value("${jiang.picking.count}")
    private String chooseCount;

    @Test
    public void reset_should_remove_all_jiang_in_jiangPicking_repo() throws Exception {
        final Jiang jiang = generateJiang();

        jiangPickingRepository.save(new JiangPicking(jiang.getId()));

        assertThat(jiangPickingRepository.findAll().size(), is(1));

        restTemplate.postForEntity(urlWrapper("/jiang/reset"), new HttpEntity<Void>(null, jsonHeader()), String.class);

        assertThat(jiangPickingRepository.findAll().size(), is(0));
    }

    @Test
    public void listRest_should_only_list_available_jiang() throws Exception {
        generateJiang(10);
        final Jiang jiang = generateJiang();

        assertThat(jiangRepository.findAll().size(), is(11));

        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlWrapper("/jiang/listRest"), String.class);

        final List<Jiang> jiangList = JsonPath.read(responseEntity.getBody(), "$.payload[*]");

        assertThat(jiangList.size(), is(11));

        jiangPickingRepository.save(new JiangPicking(jiang.getId()));

        final ResponseEntity<String> responseEntityAfterPicking = restTemplate.getForEntity(urlWrapper("/jiang/listRest"), String.class);

        final List<Jiang> jiangListAfterPicking = JsonPath.read(responseEntityAfterPicking.getBody(), "$.payload[*]");

        assertThat(jiangListAfterPicking.size(), is(10));
    }

    @Test
    public void listPicked_should_list_all_picked_jiang() throws Exception {
        final Jiang jiang = generateJiang();

        assertThat(jiangRepository.findAll().size(), is(1));
        assertThat(jiangPickingRepository.findAll().size(), is(0));

        jiangPickingRepository.save(new JiangPicking(jiang.getId()));

        final ResponseEntity<String> responseEntityAfterPicking = restTemplate.getForEntity(urlWrapper("/jiang/listPicked"), String.class);

        final List<Jiang> jiangListAfterPicking = JsonPath.read(responseEntityAfterPicking.getBody(), "$.payload[*]");

        assertThat(jiangListAfterPicking.size(), is(1));
    }

    @Test
    public void choose_should_return_defined_count_of_jiang() throws Exception {
        generateJiang(10);

        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlWrapper("/jiang/choose"), String.class);

        final List<Jiang> jiangListAfterPicking = JsonPath.read(responseEntity.getBody(), "$.payload[*]");

        assertThat(jiangListAfterPicking.size(), is(Integer.valueOf(chooseCount)));
    }

    @Test
    public void pick_should_create_entity_in_jiang_picking_repo() throws Exception {
        generateJiang(10);
        final Jiang jiang = generateJiang();

        assertThat(jiangPickingRepository.findAll().size(), is(0));

        restTemplate.postForEntity(urlWrapper("/jiang/pick/" + jiang.getId()), new HttpEntity<Void>(null, jsonHeader()), String.class);

        assertThat(jiangPickingRepository.findAll().size(), is(1));
        assertThat(jiangPickingRepository.findByJiang(jiang.getId()), is(notNullValue()));
    }
}