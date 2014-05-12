package com.jaiwo99.cards.rest.api;

import com.jaiwo99.cards.AbstractControllerTest;
import com.jaiwo99.cards.domain.CardDeal;
import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.repository.CardDealRepository;
import com.jaiwo99.cards.repository.JiangRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.jaiwo99.cards.domain.CardStatus.PICKED;
import static com.jayway.jsonpath.JsonPath.read;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class JiangDealRestControllerTest extends AbstractControllerTest {

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

        restTemplate.postForEntity(urlWrapper("/rest/jiang/reset"), new HttpEntity<Void>(null, jsonHeader()), String.class);

        assertThat(cardDealRepository.findAll().size(), is(0));
    }

    @Test
    public void listNew_should_only_list_available_jiang() throws Exception {
        generateJiang(10);
        final Jiang jiang = generateJiang();

        assertThat(jiangRepository.findAll().size(), is(11));

        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlWrapper("/rest/jiang/listNew"), String.class);

        final List<Jiang> jiangList = read(responseEntity.getBody(), "$.payload[*]");

        assertThat(jiangList.size(), is(11));

        cardDealRepository.save(new CardDeal(jiang.getId(), PICKED));

        final ResponseEntity<String> responseEntityAfterPicking = restTemplate.getForEntity(urlWrapper("/rest/jiang/listNew"), String.class);

        final List<Jiang> jiangListAfterPicking = read(responseEntityAfterPicking.getBody(), "$.payload[*]");

        assertThat(jiangListAfterPicking.size(), is(10));
    }

    @Test
    public void listChosen_should_not_list_picked_jiang() throws Exception {
        final Jiang jiangToBePicked = generateJiang();

        assertThat(jiangRepository.findAll().size(), is(1));

        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlWrapper("/rest/jiang/listChosen"), String.class);

        final List<Jiang> jiangList = read(responseEntity.getBody(), "$.payload[*]");

        assertThat(jiangList.size(), is(0));

        restTemplate.postForEntity(urlWrapper("/rest/jiang/pick/"+ jiangToBePicked.getId()), new HttpEntity<Void>(null, jsonHeader()), String.class);

        final ResponseEntity<String> responseEntityAfterPick = restTemplate.getForEntity(urlWrapper("/rest/jiang/listChosen"), String.class);

        final List<Jiang> jiangListAfterPick = read(responseEntityAfterPick.getBody(), "$.payload[*]");

        assertThat(jiangListAfterPick.size(), is(0));
    }

    @Test
    public void listChosen_should_list_chosen_jiang() throws Exception {
        generateJiang(10);

        final ResponseEntity<String> chooseEntity = restTemplate.postForEntity(urlWrapper("/rest/jiang/choose"), new HttpEntity<Void>(null, jsonHeader()), String.class);

        final List<Jiang> chosenList = read(chooseEntity.getBody(), "$.payload[*]");

        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlWrapper("/rest/jiang/listChosen"), String.class);

        final List<Jiang> jiangList = read(responseEntity.getBody(), "$.payload[*]");

        assertThat(jiangList.size(), is(Integer.valueOf(chooseCount)));

        assertThat(jiangList.size(), equalTo(chosenList.size()));

        assertThat(jiangList.containsAll(chosenList), is(true));
    }

    @Test
    public void listPicked_should_list_all_picked_jiang() throws Exception {
        final Jiang jiang = generateJiang();

        assertThat(jiangRepository.findAll().size(), is(1));
        assertThat(cardDealRepository.findAll().size(), is(0));

        cardDealRepository.save(new CardDeal(jiang.getId(), PICKED));

        final ResponseEntity<String> responseEntityAfterPicking = restTemplate.getForEntity(urlWrapper("/rest/jiang/listPicked"), String.class);

        final List<Jiang> jiangListAfterPicking = read(responseEntityAfterPicking.getBody(), "$.payload[*]");

        assertThat(jiangListAfterPicking.size(), is(1));
    }

    @Test
    public void choose_should_return_defined_count_of_jiang() throws Exception {
        generateJiang(10);

        final ResponseEntity<String> responseEntity = restTemplate.postForEntity(urlWrapper("/rest/jiang/choose"), new HttpEntity<Void>(null, jsonHeader()), String.class);

        final List<Jiang> jiangListAfterPicking = read(responseEntity.getBody(), "$.payload[*]");

        assertThat(jiangListAfterPicking.size(), is(Integer.valueOf(chooseCount)));
    }

    @Test
    public void pick_should_create_entity_in_jiang_picking_repo() throws Exception {
        generateJiang(10);
        final Jiang jiang = generateJiang();

        assertThat(cardDealRepository.findAll().size(), is(0));

        restTemplate.postForEntity(urlWrapper("/rest/jiang/pick/" + jiang.getId()), new HttpEntity<Void>(null, jsonHeader()), String.class);

        assertThat(cardDealRepository.findAll().size(), is(1));
        assertThat(cardDealRepository.findByCard(jiang.getId()), is(notNullValue()));
    }
}