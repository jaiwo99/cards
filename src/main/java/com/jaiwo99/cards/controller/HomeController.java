package com.jaiwo99.cards.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liang shi
 * @date 2014/May/27
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }
}
