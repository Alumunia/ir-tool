/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.service.jpa;

import com.gdn.x.ui.model.ContentGoldenList;
import com.gdn.x.ui.repository.ContentGoldenListRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gdn.x.ui.service.ContentGoldenListService;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * @author rizki.a.utomo
 */
@Service
public class ContentGoldenListServiceImpl implements ContentGoldenListService {

    @Autowired
    private ContentGoldenListRepository contentGoldenListRepository;


    @Override
    public List<ContentGoldenList> listContentGoldenList() {
        return contentGoldenListRepository.findAll();

    }

    @Override
    public List<ContentGoldenList> findContentGoldenListByTimeStamp(String timeStamp) {
        return (List<ContentGoldenList>) contentGoldenListRepository.findContentGoldenListByTimeStamp(timeStamp);
    }

    @Override
    public ContentGoldenList insert(ContentGoldenList document) {
        return contentGoldenListRepository.insert(document);
    }

    @Override
    public List<ContentGoldenList> findDistinctQueryByTimeStamp(String timeStamp) {

        return contentGoldenListRepository.findDistinctQueryByTimeStamp(timeStamp);
    }

    @Override
    public List<ContentGoldenList> findContentGoldenListByQueryAndTimeStamp(String query, String timeStamp) {

        return contentGoldenListRepository.findContentGoldenListByQueryAndTimeStamp(query, timeStamp);
    }


}
