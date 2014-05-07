package com.jaiwo99.cards.controller;

import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.service.JiangAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author liang - jaiwo99@gmail.com
 */
@RestController
@RequestMapping("/admin/jiang")
public class JiangAdminController {

    @Autowired
    private JiangAdminService jiangAdminService;

    @RequestMapping(value = "/listAll", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Jiang> listAll() {
        return jiangAdminService.listAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    Jiang save(Jiang jiang) {
        return jiangAdminService.save(jiang);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Jiang> remove(@PathVariable String id) {
        jiangAdminService.remove(id);
        return listAll();
    }
}
