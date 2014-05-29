package com.jaiwo99.cards.controller;

import com.jaiwo99.cards.AbstractControllerTest;
import com.jaiwo99.cards.util.EntityGenerator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class RoleDealControllerTest extends AbstractControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private EntityGenerator entityGenerator;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(wac).alwaysDo(print()).build();
        entityGenerator.generateRoles(6);
    }

    @Test
    public void root_should_return_role_view() throws Exception {
        mockMvc.perform(get("/role/")).
                andExpect(model().attribute("roleView", hasProperty("role"))).
                andExpect(status().is(200)).
                andExpect(view().name("role/view"));
    }
    
    
    @Test
    public void randomPick_should_pick_role_and_put_it_to_card_holder() throws Exception {
        mockMvc.perform(post("/role/pick")).
                andExpect(status().is(200)).
                andExpect(request().sessionAttribute("scopedTarget.simpleJiangHolder", hasProperty("role", hasProperty("id")))).
                andExpect(view().name("role/view"));
    }

}