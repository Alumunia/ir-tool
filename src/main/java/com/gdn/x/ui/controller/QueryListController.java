package com.gdn.x.ui.controller;

import com.gdn.x.ui.service.ContentGoldenListService;
import com.gdn.x.ui.service.FileGoldenListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rizki.a.utomo
 */
@Controller
public class QueryListController {

    @Autowired
    private FileGoldenListService fileGoldenListService;

    @Autowired
    private ContentGoldenListService contentGoldenListService;

    @RequestMapping(value = {"query-list"}, method = RequestMethod.GET)
    public ModelAndView historyProcess(
            @RequestParam(value = "timeStamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String timeStamp
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ContentGoldenList", contentGoldenListService.findContentGoldenListByTimeStamp(timeStamp));
        modelAndView.addObject("FileGoldenList", fileGoldenListService.findFileGoldenListBytimeStampOrderByTimeStampDesc(timeStamp));
        modelAndView.setViewName("query-list");

        return modelAndView;
    }
}
