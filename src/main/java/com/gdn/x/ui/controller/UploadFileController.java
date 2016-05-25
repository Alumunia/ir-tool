package com.gdn.x.ui.controller;

import com.gdn.x.ui.model.ContentGoldenList;
import com.gdn.x.ui.model.FileGoldenList;
import com.gdn.x.ui.service.ContentGoldenListService;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import org.springframework.web.servlet.ModelAndView;
import com.gdn.x.ui.service.FileGoldenListService;

/**
 * Handles requests for the file upload page.
 */
@Controller
public class UploadFileController extends CommonController{

    @Autowired
    private FileGoldenListService fileGoldenListService;

    @Autowired
    private ContentGoldenListService contentGoldenListService;

    public FileGoldenList document_file = new FileGoldenList();

    @RequestMapping(value = "/upload-file", method = RequestMethod.GET)
    public ModelAndView showUploadForm(HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload");
        return modelAndView;
    }

    @RequestMapping(value = "/upload-file-process", method = RequestMethod.POST)
    public ModelAndView handleFileUpload(HttpServletRequest request, HttpSession session,
            @RequestParam CommonsMultipartFile fileUpload) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        String fileName = (fileUpload.getOriginalFilename());

        saveMultiMaptoMongo(readCSVtoMultiMap(fileUpload),fileName);
        session.setAttribute("uploadSuccessMessage", "Upload File Success");
        String timeParameter = fileGoldenListService.listFileGoldenListOrderByTimeStampDesc().get(0).getTimeStamp();
        modelAndView.setViewName("redirect:query-list?timeStamp=" + timeParameter);

        return modelAndView;
    }

}
