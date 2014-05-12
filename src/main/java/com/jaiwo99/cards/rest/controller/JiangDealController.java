package com.jaiwo99.cards.rest.controller;

import com.jaiwo99.cards.service.JiangDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.jaiwo99.cards.rest.controller.ResponseWrapper.getInstance;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author liang - jaiwo99@gmail.com
 */
@RestController
@RequestMapping("/jiang")
public class JiangDealController {

    @Autowired
    private JiangDealService jiangDealService;

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public @ResponseBody
    ResponseWrapper reset() {
        jiangDealService.reset();
        return listRest();
    }

    @RequestMapping(value = "/listRest", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseWrapper listRest() {
        return getInstance(HttpStatus.OK.value(), jiangDealService.listRest());
    }

    @RequestMapping(value = "/listPicked", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseWrapper listPicked() {
        return getInstance(HttpStatus.OK.value(), jiangDealService.listPicked());
    }

    @RequestMapping(value = "/choose", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseWrapper choose() {
        return getInstance(HttpStatus.OK.value(), jiangDealService.choose());
    }

    @RequestMapping(value = "/pick/{id}", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseWrapper pick(@PathVariable String id) {
        return getInstance(HttpStatus.OK.value(), jiangDealService.pick(id));
    }
}
