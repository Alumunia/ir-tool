/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.service;

import com.gdn.x.ui.model.FileGoldenList;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author rizki.a.utomo
 */
public interface FileGoldenListService {

    public List<FileGoldenList> listFileGoldenListOrderByTimeStampDesc();

    public FileGoldenList insert(FileGoldenList document);

    public List<FileGoldenList> findFileGoldenListBytimeStampOrderByTimeStampDesc(String timeStamp);
}
