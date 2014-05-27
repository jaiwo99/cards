package com.jaiwo99.cards.controller;

import com.jaiwo99.cards.AbstractControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author liang shi
 * @date 2014/May/27
 */
public class HomeControllerTest extends AbstractControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(wac).alwaysDo(print()).build();
    }

    @Test
    public void home_should_return_index() throws Exception {
        mockMvc.perform(get("/")).
                andExpect(status().is(200)).
                andExpect(view().name("index"));
    }
}
