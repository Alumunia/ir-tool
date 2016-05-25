package com.gdn.x.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gdn.x.ui.service.ContentGoldenListService;
import com.gdn.x.ui.service.FileGoldenListService;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HistoryController {

    @Autowired
    private FileGoldenListService fileGoldenListService;

    @Autowired
    private ContentGoldenListService contentGoldenListService;

    @RequestMapping(value = {"/","/upload-history"}, method = RequestMethod.GET)
    public ModelAndView getPersonList() {
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.addObject("FileGoldenList", fileGoldenListService.listFileGoldenListOrderByTimeStampDesc());
       modelAndView.setViewName("history");
        return modelAndView;
    }

 

}
