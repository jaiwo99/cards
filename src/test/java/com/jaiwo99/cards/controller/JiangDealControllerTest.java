package com.jaiwo99.cards.controller;

import com.jaiwo99.cards.AbstractControllerTest;
import com.jaiwo99.cards.util.EntityGenerator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class JiangDealControllerTest extends AbstractControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private EntityGenerator entityGenerator;

    @Value("${jiang.picking.count}")
    private String jiangPickingCount;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(wac).alwaysDo(print()).build();
        entityGenerator.generateJiang(10);
    }

    @Test
    public void root_should_return_jiang_view() throws Exception {
        mockMvc.perform(get("/jiang/")).
                andExpect(model().attribute("jiangView", hasProperty("major"))).
                andExpect(model().attribute("jiangView", hasProperty("minor"))).
                andExpect(status().is(200)).
                andExpect(view().name("jiang/view"));
    }

    @Test
    public void choose_should_put_chooseView_in_model() throws Exception {
        mockMvc.perform(get("/jiang/choose")).
                andExpect(model().attribute("chooseView", hasProperty("selection", notNullValue()))).
                andExpect(status().is(200)).
                andExpect(view().name("jiang/choose"));
    }

    @Test
    public void choose_should_put_jiang_in_holder() throws Exception {
        mockMvc.perform(post("/jiang/choose")).
                andExpect(request().sessionAttribute("scopedTarget.simpleJiangHolder", hasProperty("selection", hasSize(Integer.valueOf(jiangPickingCount))))).
                andExpect(status().is(302)).
                andExpect(redirectedUrl("jiang/choose"));
    }

}