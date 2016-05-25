/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.function;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 *
 * @author alumunia
 */
public class Permute {

    static void permute(java.util.List<Integer> arr, int k) {
        for (int i = k; i < arr.size(); i++) {
            java.util.Collections.swap(arr, i, k);
            permute(arr, k + 1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("tool_data");
            DBCollection coll = db.getCollection("combination");
            BasicDBObject doc = new BasicDBObject();
            doc.put("weight", java.util.Arrays.toString(arr.toArray()));
            coll.insert(doc);
            System.out.println(java.util.Arrays.toString(arr.toArray()));
        }
    }

    public static void main(String[] args) {
        Permute.permute(java.util.Arrays.asList(2, 4, 6, 8, 10), 3);
    
    }
}
