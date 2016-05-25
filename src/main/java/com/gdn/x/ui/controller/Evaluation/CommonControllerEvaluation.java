/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.controller.Evaluation;

import com.gdn.x.ui.controller.RunGoldenListController;
import com.gdn.x.ui.model.evaluation.CoordinateEvaluation;
import com.gdn.x.ui.model.evaluation.ParameterWeight;
import com.gdn.x.ui.service.ContentGoldenListService;
import com.gdn.x.ui.service.Evaluation.CoordinateEvaluationService;
import com.gdn.x.ui.service.Evaluation.ParameterWeightService;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.CoreAdminParams.CoreAdminAction;
import org.apache.solr.common.util.NamedList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author rizki.a.utomo
 */
public class CommonControllerEvaluation {

    @Autowired
    CoordinateEvaluationService coordinateEvaluationService;

    @Autowired
    ParameterWeightService parameterWeightService;

    @Autowired
    ContentGoldenListService contentGoldenListService;
	// ur solr config
    private String xmlFilePath = "C:\\UAT1\\solr-5.4.0\\server\\solr\\collection3\\conf\\solrconfig.xml";

	//ur solr port
    private String SolrUrlJson = "http://172.17.130.9:8983/solr/collection3/schema/fields/?wt=json";


	// ur solr port
    private String solrUrlSearch = "http://127.0.0.1:8983/solr/collection3";
	// ur solr port
    private String solrUrlReload = "http://127.0.0.1:8983/solr/admin/cores?action=RELOAD&core=collection3";

    protected List<String> findRelevant(List<String> actualId, List<String> expectedId) {

        List<String> map = new ArrayList<String>();
        List<String> relevantId = new ArrayList<String>();

        for (String a : actualId) {
            map.add(a);
        }
        for (String b : expectedId) {
            if (map.contains(b)) {
                relevantId.add(b);
            }
        }
        return relevantId;
    }

    protected void SaveCoordinateToMongo(List<Double> coordinateEvaluation) {
        CoordinateEvaluation document = new CoordinateEvaluation();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        document.setTimeStamp(timeStamp);
        document.setParameter(getParameter());
        document.setCoordinateEvaluation(coordinateEvaluation);
        coordinateEvaluationService.insert(document);

    }

    protected static Map<List<Double>, List<Double>> getRecallandPrecision(List<String> expectedId, List<String> actualId) {
        List<Boolean> relevancy = new ArrayList<Boolean>();
        //precision = number of relevant from retrieved)
        List<Double> precision = new ArrayList<Double>();
        //recall => (retrieved|relevant)
        Map<List<Double>, List<Double>> listPrecisionAndRecall = new HashMap<List<Double>, List<Double>>();
        List<Double> recall = new ArrayList<Double>();
        List<Double> precisionInterpolated = new ArrayList<Double>();
        double elevenRecallLevels[] = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
        double totalRelevant = expectedId.size();

        if (actualId.size() <= 24) {
            for (String id : actualId) {
                if (expectedId.contains(id)) {
                    relevancy.add(Boolean.TRUE);
                } else {
                    relevancy.add(Boolean.FALSE);
                }
            }
        } else {
            for (int i = 0; i < 24; i++) {
                if (expectedId.contains(actualId.get(i))) {
                    relevancy.add(Boolean.TRUE);
                } else {
                    relevancy.add(Boolean.FALSE);
                }
            }
        }

        double numRelevant = 0;
        double devider = 1;
        for (Boolean bool : relevancy) {
            if (bool) {
                numRelevant++;
            }
            recall.add(numRelevant / totalRelevant);
            precision.add(numRelevant / devider);
            devider++;
        }

        listPrecisionAndRecall.put(precision, recall);
        return listPrecisionAndRecall;
    }

    protected static List<Double> interpolatePrecision(List<String> expectedId, List<String> actualId) {
        List<Boolean> relevancy = new ArrayList<Boolean>();
        //precision = number of relevant from retrieved)
        List<Double> precision = new ArrayList<Double>();
        //recall => (retrieved|relevant)
        List<Double> recall = new ArrayList<Double>();
        List<Double> precisionInterpolated = new ArrayList<Double>();
        double elevenRecallLevels[] = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
        double totalRelevant = expectedId.size();

        if (actualId.size() <= 24) {
            for (String id : actualId) {
                if (expectedId.contains(id)) {
                    relevancy.add(Boolean.TRUE);
                } else {
                    relevancy.add(Boolean.FALSE);
                }
            }
        } else {
            for (int i = 0; i < 24; i++) {
                if (expectedId.contains(actualId.get(i))) {
                    relevancy.add(Boolean.TRUE);
                } else {
                    relevancy.add(Boolean.FALSE);
                }
            }
        }

        double numRelevant = 0;
        double devider = 1;
        for (Boolean bool : relevancy) {
            if (bool) {
                numRelevant++;
            }
            if (totalRelevant <= 24) {
                recall.add(numRelevant / totalRelevant);

            } else {
                recall.add(numRelevant / 24);
            }
            precision.add(numRelevant / devider);
            devider++;
        }

        for (double r : elevenRecallLevels) {
            List<Integer> reTemp = new ArrayList<Integer>();
            for (int i = 0; i < recall.size(); i++) {
                if (recall.get(i) >= r) {
                    reTemp.add(i);
                }
            }
            double max = 0;
            for (Integer i : reTemp) {
                if (precision.get(i) > max) {
                    max = precision.get(i);
                }
            }
            precisionInterpolated.add(max);
        }

        return precisionInterpolated;
    }

    protected List<List<Double>> listInterpolatedPrecision(List<List<String>> expectedId, List<List<String>> actualId) {
        List<List<Double>> listInterpolatedPrecision = new ArrayList<List<Double>>();
        for (int i = 0; i < expectedId.size(); i++) {
            listInterpolatedPrecision.add(interpolatePrecision(expectedId.get(i), actualId.get(i)));
        }
        return listInterpolatedPrecision;
    }

    protected List<Double> interpolateAveragePrecision(List<List<String>> expectedId, List<List<String>> actualId) {
        List<Double> averageInterpolatedPrecision = null;

        if (expectedId.size() == actualId.size()) {
            Double[] averageInterpolatedPrecisionTemp = {0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00};

            for (int i = 0; i < expectedId.size(); i++) {
                List<Double> interpolatedPrecisions = new ArrayList<Double>();
                interpolatedPrecisions.addAll(interpolatePrecision(expectedId.get(i), actualId.get(i)));
                for (int j = 0; j < averageInterpolatedPrecisionTemp.length; j++) {
                    averageInterpolatedPrecisionTemp[j] += interpolatedPrecisions.get(j);
                    if (i == expectedId.size() - 1) {
                        averageInterpolatedPrecisionTemp[j] /= expectedId.size();
                    }
                }
            }
            averageInterpolatedPrecision = Arrays.asList(averageInterpolatedPrecisionTemp);

        }
        return averageInterpolatedPrecision;
    }

    protected Double finalScoreInterpolatedPrecision(List<List<String>> expectedId, List<List<String>> actualId) {

        List<Double> score = interpolateAveragePrecision(expectedId, actualId);
        Double averageScore = 0.0;
        Double finalAverageScore12 = 0.0;
        for (int i = 0; i < score.size(); i++) {
            averageScore += score.get(i);
        }

        finalAverageScore12 = averageScore / score.size();
//        String finalAverageScore = String.valueOf(finalAverageScore12);

        return finalAverageScore12;

    }

    protected void SaveParameterWeightToMongo(List<List<String>> expectedId, List<List<String>> actualId) {

        ParameterWeight document = new ParameterWeight();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        document.setWeightParameter(getParameter());
        document.setFinalScore(finalScoreInterpolatedPrecision(expectedId, actualId));
        document.setTimeStamp(timeStamp);
        document.setAveragePrecision(interpolateAveragePrecision(expectedId, actualId));
        parameterWeightService.insert(document);

    }

    //method to Set Parameter to Solr Config
    protected void setParameter(String var_parameter) {

        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(xmlFilePath);

            Node requestHandler = document.getElementsByTagName("requestHandler").item(3);
            // Node lst = document.getElementsByTagName("lst").item(0);
            Element lstElement = (Element) requestHandler;
            Node lst = lstElement.getElementsByTagName("lst").item(0);

            NodeList list = lst.getChildNodes();

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);
                if ("str".equals(node.getNodeName()) && ("qf".equals(node.getAttributes().getNamedItem("name").getNodeValue()) || "mlt.qf".equals(node.getAttributes().getNamedItem("name").getNodeValue()))) {
                    node.setTextContent(var_parameter);
                }
            }
            // write the DOM object to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }
        //reload the core
        callURL();
//        System.out.println("BATMAN");
    }

    protected void callURL() {

        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(solrUrlReload);
            urlConn = url.openConnection();
            if (urlConn != null) {
                urlConn.setReadTimeout(60 * 1000);
            }
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:" + solrUrlReload, e);
        }

    }

    //    Method to Search By Query
    protected List searchByQueryEvaluation(String queryParameter) throws MalformedURLException, SolrServerException {
        List actualResult = new ArrayList<String>();
        try {
//            for solr 5
            HttpSolrClient solr = new HttpSolrClient(solrUrlSearch);
            //for Solr 4
//            HttpSolrServer solr = new HttpSolrServer("http://172.17.132.9:8983/solr/collection3");

            SolrQuery query = new SolrQuery();

            query.setQuery(queryParameter);
            query.setFields("id");
            query.setRequestHandler("/browse");
            query.setStart(0);
            query.setRows(24);
            QueryResponse response = solr.query(query);
            SolrDocumentList results = response.getResults();

            int j = 0;
            for (int i = 0; i < results.size(); ++i, j++) {
                actualResult.add(results.get(i).get("id"));
            }
//                System.out.println(results.getNumFound());
//            System.out.println(actualResult);

        } catch (IOException ex) {
            Logger.getLogger(RunGoldenListController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return actualResult;
    }

    //Method to Get Field from solr Config
    protected Map<String, String> getParameter() {

        Map<String, String> finalParameter = new HashMap<String, String>();

        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFilePath);

            Node requestHandler = document.getElementsByTagName("requestHandler").item(3);
            // Node lst = document.getElementsByTagName("lst").item(0);
            Element lstElement = (Element) requestHandler;
            Node lst = lstElement.getElementsByTagName("lst").item(0);

            NodeList list = lst.getChildNodes();
            String word = null;

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if ("str".equals(node.getNodeName()) && ("qf".equals(node.getAttributes().getNamedItem("name").getNodeValue()) || "mlt.qf".equals(node.getAttributes().getNamedItem("name").getNodeValue()))) {
                    word = node.getTextContent();

                }

            }

            String[] str = word.split(" ");
            List<String> word2 = Arrays.asList(str);

            for (String retval : word2) {
                String[] word4 = retval.split("\\^");
                finalParameter.put(word4[0], word4[1]);
            }

            // write the DOM object to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }

        return finalParameter;
    }

    protected void combineFieldListAndValueList(String[] fieldList, String[] valueList) {

        int len = fieldList.length;
        String var_parameter = "";
        for (int i = 0; i < len; i++) {

            var_parameter += fieldList[i] + "^" + valueList[i] + " ";

        };
        setParameter(var_parameter);
    }

    protected void fetchParameter(String[] fieldList) {
        String[] num = {"2", "4", "6", "8", "10"};
        int w = 0;
        int x = 0;
        int y = 0;
        int z = 0;

        while (w < 5) {
            List<String> num1 = new ArrayList<>();
            num1.add(num[w]);
            num1.add(num[x]);
            num1.add(num[y]);
            num1.add(num[z]);

            z++;
            if (z == 5) {
                y++;
                z = 0;
            }
            if (y == 5) {
                x++;
                y = 0;
            }
            if (x == 5) {
                w++;
                x = 0;
            }

            String var_parameter = "";
            for (int i = 0; i < num1.size(); i++) {

                int len = fieldList.length;

                var_parameter += fieldList[i] + "^" + num1.get(i) + " ";

            }
            System.out.println(var_parameter);

            setParameter(var_parameter);

            List<List<String>> listActualId = new ArrayList<List<String>>();
            List<List<String>> listExpectedId = new ArrayList<List<String>>();
            List<String> RecallCoordinate = Arrays.asList("0.0", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "1.0");
            Multimap<String, List<Double>> listQueryAndPrecision = ArrayListMultimap.create();

            for (int i = 0; i < 20; i++) {
                String parameter = contentGoldenListService.listContentGoldenList().get(i).getQuery();
                String timeStamp = contentGoldenListService.listContentGoldenList().get(i).getTimeStamp();
                try {
                    listActualId.add(searchByQueryEvaluation(parameter));
                } catch (MalformedURLException ex) {
                    Logger.getLogger(CommonControllerEvaluation.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SolrServerException ex) {
                    Logger.getLogger(CommonControllerEvaluation.class.getName()).log(Level.SEVERE, null, ex);
                }
                listExpectedId.add(contentGoldenListService.findContentGoldenListByQueryAndTimeStamp(parameter, timeStamp).get(0).getExpectedResult());
                listQueryAndPrecision.put(parameter, listInterpolatedPrecision(listExpectedId, listActualId).get(i));
            }

//SaveParameterWeightToMongo(listExpectedId, listActualId);
//method to save to mongo
            ParameterWeight document = new ParameterWeight();
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            document.setWeightParameter(getParameter());
            document.setFinalScore(finalScoreInterpolatedPrecision(listExpectedId, listActualId));
            document.setTimeStamp(timeStamp);
            document.setAveragePrecision(interpolateAveragePrecision(listExpectedId, listActualId));
            parameterWeightService.insert(document);
            System.out.println("saved");
        }

    }

}
