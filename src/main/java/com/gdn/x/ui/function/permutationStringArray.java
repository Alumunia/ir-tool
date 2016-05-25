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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumunia
 */
public class permutationStringArray {

    public static void main(String[] args) {

        Integer[] test = {2, 4, 6, 8, 10};
        language(3, test, "");
    }

    private static void language(final int n, final Integer[] syllables, final String currentWord) { // example of N = 3
        List<String> weight = new ArrayList<String>();

        if (n == 0) {
//            System.out.println(currentWord);
            weight.add(currentWord);
        } else {
            for (int i = 0; i < syllables.length; i++) {
                language(n - 1, syllables, currentWord + "," + syllables[i]);
            }
        }
        if (weight.size() != 0) {
            System.out.println(weight.size());
            
//            MongoClient mongoClient = new MongoClient("localhost", 27017);
//            DB db = mongoClient.getDB("tool_data");
//            DBCollection coll = db.getCollection("combination");
//            BasicDBObject doc = new BasicDBObject();
//            doc.put("weight", weight);
//            coll.insert(doc);

        }

    }

}
