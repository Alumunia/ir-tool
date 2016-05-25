/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.repository;

import com.gdn.x.ui.model.ContentGoldenList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rizki.a.utomo
 */
@Repository
public interface ContentGoldenListRepository extends MongoRepository<ContentGoldenList, String> {

    List<ContentGoldenList> findContentGoldenListByTimeStamp(String timeStamp);

    List<ContentGoldenList> findDistinctQueryByTimeStamp(String timeStamp);

    List<ContentGoldenList> findExpectedIdByQuery(String Query);

    List<ContentGoldenList> findContentGoldenListByQueryAndTimeStamp(String query);

   
    List<ContentGoldenList> findContentGoldenListByQueryAndTimeStamp(String query, String timeStamp);

}
