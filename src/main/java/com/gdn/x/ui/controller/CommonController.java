/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.controller;

import com.gdn.x.ui.model.ContentGoldenList;
import com.gdn.x.ui.model.FileGoldenList;
import com.gdn.x.ui.service.ContentGoldenListService;
import com.gdn.x.ui.service.FileGoldenListService;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author rizki.a.utomo
 */
public class CommonController {

    @Autowired
    private FileGoldenListService fileGoldenListService;

    @Autowired
    private ContentGoldenListService contentGoldenListService;

    private FileGoldenList document_file = new FileGoldenList();
	// port solr
    private String solrUrlSearch = "http://127.0.0.1:8983/solr/collection3";
    //Path to save file uploaded
    private String filepath = "C://";

    protected List searchByQuery(String queryParameter) throws MalformedURLException, SolrServerException {
        List actualResult = new ArrayList<String>();
        try {
//            for solr 5
            HttpSolrClient solr = new HttpSolrClient(solrUrlSearch);
            //for Solr 4
//            HttpSolrServer solr = new HttpSolrServer("");

            SolrQuery query = new SolrQuery();

            query.setQuery(queryParameter);
            query.setFields("id");
            query.setRequestHandler("/browse");
            query.setStart(0);
            query.setRows(40);
            QueryResponse response = solr.query(query);
            SolrDocumentList results = response.getResults();

            int j = 0;
            for (int i = 0; i < results.size(); ++i, j++) {
                actualResult.add(results.get(i).get("id"));
            }
        } catch (IOException ex) {
            Logger.getLogger(RunGoldenListController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return actualResult;
    }

    //greenId = expectedId in same or better position on actualId
    protected List<String> getGreenResultIdList(List<String> expectedResultId, List<String> actualResultId) {
        List<String> ids = new ArrayList<String>();
        for (String id : expectedResultId) {
            if ((actualResultId.contains(id)) && (actualResultId.indexOf(id) <= expectedResultId.indexOf(id))) {
                ids.add(id);
            }
        }
        return ids;
    }

    //yellowId = expectedId in worse position on actualId but still in page one
    protected List<String> getYellowResultIdList(List<String> expectedResultId, List<String> actualResultId) {
        List<String> ids = new ArrayList<String>();
        for (String id : expectedResultId) {
            if ((actualResultId.contains(id)) && (actualResultId.indexOf(id) > expectedResultId.indexOf(id)) && (actualResultId.indexOf(id) < expectedResultId.size())) {
                ids.add(id);
            }
        }
        return ids;
    }

    //orangeId = expectedId in worse position on actualId and not in page one
    protected List<String> getOrangeResultIdList(List<String> expectedResultId, List<String> actualResultId) {
        List<String> ids = new ArrayList<String>();
        for (String id : expectedResultId) {
            if (((actualResultId.contains(id)) && (actualResultId.indexOf(id) > expectedResultId.indexOf(id)) && (actualResultId.indexOf(id) >= expectedResultId.size())) || (actualResultId.indexOf(id) >= 24)) {
                ids.add(id);
            }
        }
        return ids;
    }
    
    //brownId = expectedId that not appear on actualId
    protected List<String> getBrownResultIdList(List<String> expectedResultId, List<String> actualResultId) {
        List<String> ids = new ArrayList<String>();
        for (String id : expectedResultId) {
            if (actualResultId.contains(id)) {
                ids.add(id);
            }
        }
        return ids;
    }

    protected Map<String, List<String>> saveMultiMaptoMongo(Multimap<String, String> fileUpload, String fileName) {
        Map<String, List<String>> goldenListParameter = new HashMap<String, List<String>>();
        DBObject eachObject = new BasicDBObject();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        for (Map.Entry<String, Collection<String>> entry : fileUpload.asMap().entrySet()) {
            List<String> value = new ArrayList<String>();
            String key = "";
            if (!entry.getKey().equalsIgnoreCase("QUERY")) {
                ContentGoldenList document = new ContentGoldenList();
                key = entry.getKey();
                value.addAll(entry.getValue());
                document.setQuery(entry.getKey());
                document.setExpectedResult((List<String>) entry.getValue());
                document.setTimeStamp(timeStamp);
                document_file.setFileName(fileName);
                document_file.setTimeStamp(timeStamp);
                contentGoldenListService.insert(document);

            }
            goldenListParameter.put(key, value);
        }
        fileGoldenListService.insert(document_file);

        return goldenListParameter;
    }

    protected Multimap<String, String> readCSVtoMultiMap(CommonsMultipartFile fileUpload) {
        String line = "";
        String query = "";
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        Multimap<String, String> queryMap = ArrayListMultimap.create();
        BufferedReader br = null;

        try {
//            makes every file's name differet with timestamp
//            File file = new File(fileUpload.getOriginalFilename().replaceAll(fileUpload.getOriginalFilename().toString(), timeStamp));
//            fileUpload.transferTo(file);
//            //Store file to specific path just in case, if you at home
            File file = new File(filepath + fileUpload.getOriginalFilename().replaceAll(fileUpload.getOriginalFilename().toString(), timeStamp));
            fileUpload.transferTo(file);
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                for (int i = 0; i < split.length; i++) {
                    if (i == 0) {
                        query = split[i];
                    } else {
                        queryMap.put(query, split[i]);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadFileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UploadFileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return queryMap;
    }

}
