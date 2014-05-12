package com.jaiwo99.cards.controller;

import com.jaiwo99.cards.service.JiangPickingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.jaiwo99.cards.controller.ResponseWrapper.getInstance;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author liang - jaiwo99@gmail.com
 */
@RestController
@RequestMapping("/jiang")
public class JiangPickingController {

    @Autowired
    private JiangPickingService jiangPickingService;

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public @ResponseBody
    ResponseWrapper reset() {
        jiangPickingService.reset();
        return listRest();
    }

    @RequestMapping(value = "/listRest", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseWrapper listRest() {
        return getInstance(HttpStatus.OK.value(), jiangPickingService.listRest());
    }

    @RequestMapping(value = "/listPicked", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseWrapper listPicked() {
        return getInstance(HttpStatus.OK.value(), jiangPickingService.listPicked());
    }

    @RequestMapping(value = "/choose", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseWrapper choose() {
        return getInstance(HttpStatus.OK.value(), jiangPickingService.choose());
    }

    @RequestMapping(value = "/pick/{id}", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseWrapper pick(@PathVariable String id) {
        return getInstance(HttpStatus.OK.value(), jiangPickingService.pick(id));
    }
}
