package com.jaiwo99.cards.controller;

import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.domain.JiangPicking;
import com.jaiwo99.cards.service.JiangPickingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.*;

/**
 * @author liang - jaiwo99@gmail.com
 */
@RestController
@RequestMapping("/jiang")
public class JiangPickingController {

    @Autowired
    private JiangPickingService jiangPickingService;

    @RequestMapping(value = "/reset", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Jiang> reset() {
        jiangPickingService.reset();
        return listRest();
    }

    @RequestMapping(value = "/listRest", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody List<Jiang> listRest() {
        return jiangPickingService.listRest();
    }

    @RequestMapping(value = "/listPicked", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody List<JiangPicking> listPicked() {
        return jiangPickingService.listPicked();
    }

    @RequestMapping(value = "/choose", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody List<Jiang> choose() {
        return jiangPickingService.choose();
    }

    @RequestMapping(value = "/pick/{id}", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody Jiang pick(@PathVariable String id) {
        return jiangPickingService.pick(id);
    }
}
