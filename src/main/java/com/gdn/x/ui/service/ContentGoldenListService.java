/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.service;

import com.gdn.x.ui.model.ContentGoldenList;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author rizki.a.utomo
 */
public interface ContentGoldenListService {

    public List<ContentGoldenList> listContentGoldenList();

    public ContentGoldenList insert(ContentGoldenList document);
    

    public List<ContentGoldenList> findContentGoldenListByTimeStamp(String timeStamp);

    public List<ContentGoldenList> findDistinctQueryByTimeStamp(String timeStamp);

    public List<ContentGoldenList> findContentGoldenListByQueryAndTimeStamp(String query, String timeStamp);
    
}
