package com.jaiwo99.cards.controller;

import com.jaiwo99.cards.command.JiangPickingCommand;
import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.service.JiangDealService;
import com.jaiwo99.cards.service.RoleDealService;
import com.jaiwo99.cards.session.JiangHolder;
import com.jaiwo99.cards.view.ChooseView;
import com.jaiwo99.cards.view.JiangView;
import com.jaiwo99.cards.view.RoleView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.jaiwo99.cards.session.JiangHolderUtil.updateJiang;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author zhou - kevinzcf@gmail.com
 */
@Controller
@RequestMapping("/role")
public class RoleDealController {

    @Autowired
    private RoleDealService roleDealService;

    @Autowired
    private JiangHolder jiangHolder;

    @RequestMapping(value = "/", method = GET)
    public String view(Model model) {
        model.addAttribute("RoleView", new RoleView(jiangHolder.getRole()));
        return "role/view";
    }
    
    @RequestMapping(value = "/pick", method = POST)
    public String pick() {
        jiangHolder.setRole(roleDealService.pick());
        return "role/view";
    }
}
