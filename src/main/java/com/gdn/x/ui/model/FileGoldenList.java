/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.model;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rizki.a.utomo
 */
@Document(collection = "tool_timestamp")
public class FileGoldenList {

    private String fileName;

    private String timeStamp;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
