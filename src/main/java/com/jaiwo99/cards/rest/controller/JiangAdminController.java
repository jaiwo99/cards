package com.jaiwo99.cards.rest.controller;

import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.service.JiangAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.jaiwo99.cards.rest.controller.ResponseWrapper.getInstance;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author liang - jaiwo99@gmail.com
 */
@RestController
@RequestMapping("/admin/jiang")
public class JiangAdminController {

    @Autowired
    private JiangAdminService jiangAdminService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseWrapper list() {
        return getInstance(HttpStatus.OK.value(), jiangAdminService.list());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    ResponseWrapper save(@RequestBody Jiang jiang) {
        return getInstance(HttpStatus.OK.value(), jiangAdminService.save(jiang));
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseWrapper remove(@PathVariable String id) {
        jiangAdminService.remove(id);
        return list();
    }
}
