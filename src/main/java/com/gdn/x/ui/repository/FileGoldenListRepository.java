/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.repository;

import com.gdn.x.ui.model.ContentGoldenList;
import com.gdn.x.ui.model.FileGoldenList;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author rizki.a.utomo
 */
public interface FileGoldenListRepository extends MongoRepository<FileGoldenList, String> {
    
    List<FileGoldenList> findFileGoldenListBytimeStampOrderByTimeStampDesc(String timeStamp);
}
