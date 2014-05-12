package com.jaiwo99.cards.rest.controller;

import com.jaiwo99.cards.AbstractControllerTest;
import com.jaiwo99.cards.domain.CardDeal;
import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.repository.CardDealRepository;
import com.jaiwo99.cards.repository.JiangRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.jaiwo99.cards.deal.CardStatus.PICKED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class JiangDealControllerTest extends AbstractControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CardDealRepository cardDealRepository;

    @Autowired
    private JiangRepository jiangRepository;

    @Value("${jiang.picking.count}")
    private String chooseCount;

    @Test
    public void reset_should_remove_all_jiang_in_jiangPicking_repo() throws Exception {
        final Jiang jiang = generateJiang();

        cardDealRepository.save(new CardDeal(jiang.getId(), PICKED));

        assertThat(cardDealRepository.findAll().size(), is(1));

        restTemplate.postForEntity(urlWrapper("/jiang/reset"), new HttpEntity<Void>(null, jsonHeader()), String.class);

        assertThat(cardDealRepository.findAll().size(), is(0));
    }

    @Test
    public void listNew_should_only_list_available_jiang() throws Exception {
        generateJiang(10);
        final Jiang jiang = generateJiang();

        assertThat(jiangRepository.findAll().size(), is(11));

        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlWrapper("/jiang/listNew"), String.class);

        final List<Jiang> jiangList = JsonPath.read(responseEntity.getBody(), "$.payload[*]");

        assertThat(jiangList.size(), is(11));

        cardDealRepository.save(new CardDeal(jiang.getId(), PICKED));

        final ResponseEntity<String> responseEntityAfterPicking = restTemplate.getForEntity(urlWrapper("/jiang/listNew"), String.class);

        final List<Jiang> jiangListAfterPicking = JsonPath.read(responseEntityAfterPicking.getBody(), "$.payload[*]");

        assertThat(jiangListAfterPicking.size(), is(10));
    }

    @Test
    public void listPicked_should_list_all_picked_jiang() throws Exception {
        final Jiang jiang = generateJiang();

        assertThat(jiangRepository.findAll().size(), is(1));
        assertThat(cardDealRepository.findAll().size(), is(0));

        cardDealRepository.save(new CardDeal(jiang.getId(), PICKED));

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

        assertThat(cardDealRepository.findAll().size(), is(0));

        restTemplate.postForEntity(urlWrapper("/jiang/pick/" + jiang.getId()), new HttpEntity<Void>(null, jsonHeader()), String.class);

        assertThat(cardDealRepository.findAll().size(), is(1));
        assertThat(cardDealRepository.findByCard(jiang.getId()), is(notNullValue()));
    }
}