package com.jaiwo99.cards.rest.api;

import com.jaiwo99.cards.service.JiangDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.jaiwo99.cards.rest.api.ResponseWrapper.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author liang - jaiwo99@gmail.com
 */
@RestController
@RequestMapping("/rest/jiang")
public class JiangDealRestController {

    @Autowired
    private JiangDealService jiangDealService;

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public @ResponseBody
    ResponseWrapper reset() {
        jiangDealService.reset();
        return listNew();
    }

    @RequestMapping(value = "/listNew", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseWrapper listNew() {
        return getInstance(HttpStatus.OK.value(), jiangDealService.listNew());
    }

    @RequestMapping(value = "/listChosen", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseWrapper listChosen() {
        return getInstance(HttpStatus.OK.value(), jiangDealService.listChosen());
    }

    @RequestMapping(value = "/listPicked", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseWrapper listPicked() {
        return getInstance(HttpStatus.OK.value(), jiangDealService.listPicked());
    }

    @RequestMapping(value = "/choose", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseWrapper choose() {
        return getInstance(HttpStatus.OK.value(), jiangDealService.choose());
    }

    @RequestMapping(value = "/pick/{id}", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseWrapper pick(@PathVariable String id) {
        return getInstance(HttpStatus.OK.value(), jiangDealService.pick(id));
    }
}
