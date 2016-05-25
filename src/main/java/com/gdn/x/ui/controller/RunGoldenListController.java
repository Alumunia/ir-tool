/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.controller;

import com.gdn.x.ui.service.ContentGoldenListService;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author alumunia
 */
@Controller
public class RunGoldenListController extends CommonController {

    @Autowired
    ContentGoldenListService contentGoldenListService;

    @RequestMapping(value = {"/run-all"}, method = RequestMethod.GET)
    public ModelAndView runAllGoldenList(
            @RequestParam(value = "query[]") String[] query,
            @RequestParam(value = "timeStamp") String timeStamp
    ) {
        List actualResultId = new ArrayList<String>();
        List expectedResultId = new ArrayList<String>();
        ModelAndView modelAndView = new ModelAndView();
        for (String parameter : query) {

            try {
                actualResultId = searchByQuery(parameter);
            } catch (MalformedURLException ex) {
                Logger.getLogger(RunGoldenListController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SolrServerException ex) {
                Logger.getLogger(RunGoldenListController.class.getName()).log(Level.SEVERE, null, ex);
            }

            expectedResultId = contentGoldenListService.findContentGoldenListByQueryAndTimeStamp(parameter, timeStamp).get(0).getExpectedResult();

            //Method To Compare
//            System.out.println(actualResultId);
            System.out.println("This is green" + getGreenResultIdList(expectedResultId, actualResultId));
            System.out.println("This is yellow" + getYellowResultIdList(expectedResultId, actualResultId));
            System.out.println("This is Orange" + getOrangeResultIdList(expectedResultId, actualResultId));
            System.out.println("This is brown" + getBrownResultIdList(expectedResultId, actualResultId));

        }

        modelAndView.setViewName("example");
        return modelAndView;

    }

    @RequestMapping(value = {"run-one"}, method = RequestMethod.GET)
    public ModelAndView runOneGoldenList(
            @RequestParam(value = "query") String query,
            @RequestParam(value = "timeStamp") String timeStamp
    ) {
        List actualResultId = new ArrayList<String>();
        List expectedResultId = new ArrayList<String>();
        ModelAndView modelAndView = new ModelAndView();
        try {
            actualResultId = searchByQuery(query);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RunGoldenListController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SolrServerException ex) {
            Logger.getLogger(RunGoldenListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        expectedResultId = contentGoldenListService.findContentGoldenListByQueryAndTimeStamp(query, timeStamp).get(0).getExpectedResult();

        //Method To Compare
//            System.out.println(actualResultId);
        System.out.println("This is green " + getGreenResultIdList(expectedResultId, actualResultId));
        System.out.println("This is yellow " + getYellowResultIdList(expectedResultId, actualResultId));
        System.out.println("This is orange " + getOrangeResultIdList(expectedResultId, actualResultId));
        System.out.println("This is brown " + getBrownResultIdList(expectedResultId, actualResultId));

        modelAndView.setViewName("query-list");

        return modelAndView;

    }

}
