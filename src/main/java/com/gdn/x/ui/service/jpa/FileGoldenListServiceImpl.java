/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.service.jpa;

import com.gdn.x.ui.model.FileGoldenList;
import com.gdn.x.ui.repository.FileGoldenListRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gdn.x.ui.service.FileGoldenListService;
import org.joda.time.DateTime;
import org.springframework.data.domain.Sort;

/**
 *
 * @author rizki.a.utomo
 */
@Service
public class FileGoldenListServiceImpl implements FileGoldenListService {

    @Autowired
    private FileGoldenListRepository filecontentGoldenListRepository;

    @Override
    public List<FileGoldenList> listFileGoldenListOrderByTimeStampDesc() {
        return filecontentGoldenListRepository.findAll((new Sort(Sort.Direction.DESC, "timeStamp")));
    }

    @Override
    public FileGoldenList insert(FileGoldenList document) {
        return filecontentGoldenListRepository.insert(document);

    }

    @Override
    public List<FileGoldenList> findFileGoldenListBytimeStampOrderByTimeStampDesc(String timeStamp) {
        return filecontentGoldenListRepository.findFileGoldenListBytimeStampOrderByTimeStampDesc(timeStamp);
    }

}
