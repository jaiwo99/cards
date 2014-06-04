package com.jaiwo99.cards.controller;

import com.jaiwo99.cards.command.JiangPickingCommand;
import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.service.JiangDealService;
import com.jaiwo99.cards.session.JiangHolder;
import com.jaiwo99.cards.session.JiangHolderAdapter;
import com.jaiwo99.cards.view.ChooseView;
import com.jaiwo99.cards.view.JiangView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Controller
@RequestMapping("/jiang")
public class JiangDealController {

    @Inject
    private JiangDealService jiangDealService;

    @Inject
    private JiangHolder jiangHolder;

    @Inject
    private JiangHolderAdapter jiangHolderAdapter;

    @RequestMapping(value = {"/", "/view"}, method = GET)
    public String view(Model model) {
        model.addAttribute("jiangView", new JiangView(jiangHolder.getMajor(), jiangHolder.getMinor()));
        return "jiang/view";
    }

    @RequestMapping(value = "/choose", method = GET)
    public String chooseView(Model model) {
        if(!model.containsAttribute("chooseView")) {
            model.addAttribute("chooseView", new ChooseView(jiangHolder.getSelection()));
        }
        return "jiang/choose";
    }

    @RequestMapping(value = "/choose", method = POST)
    public String choose() {
        jiangHolder.setSelection(jiangDealService.choose());
        return "redirect:/jiang/choose";
    }

    @RequestMapping(value = "/pick", method = POST)
    public String pick(@Valid JiangPickingCommand jiangPickingCommand, BindingResult result, Model model) {

        if(result.hasErrors()) {
            return chooseView(model);
        }

        final Jiang jiang = jiangDealService.pick(jiangPickingCommand.getId());
        jiangHolderAdapter.updateJiang(jiang, jiangPickingCommand.getJiangType());

        return "redirect:/jiang/choose";
    }
}
