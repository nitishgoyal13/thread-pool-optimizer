package com.optimizer.hystrixthreadpool;

import com.optimizer.util.OptimizerUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 Created by mudit.g on Feb, 2019
 ***/
public class Service {
    private static final Logger logger = LoggerFactory.getLogger(Service.class.getSimpleName());
    private static final String SERVICE_LIST = "SHOW MEASUREMENTS with measurement = /phonepe.prod.*.jvm.threads.count/";
    private static final String SERVICE_LIST_PATTERN = "phonepe\\.prod\\.(.*)\\.jvm\\.threads\\.count";

    private HttpClient client;

    public Service(HttpClient client) {
        this.client = client;
    }

    public List<String> getAllServices() throws Exception {
        List<String> services = new ArrayList<>();
        String query = String.format("%s;", SERVICE_LIST);
        HttpResponse response;
        try {
            response = OptimizerUtils.executeGetRequest(client, query);
            int status = response.getStatusLine().getStatusCode();
            if (status < 200 || status >= 300) {
                logger.error("Error in Http get, Status Code: " + response.getStatusLine().getStatusCode() + " received Response: " + response);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error in Http get: " + e);
            return null;
        }

        String data = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(data);
        JSONArray metricsJSONArray =
                ((JSONArray)
                        ((JSONObject)
                                ((JSONArray)
                                        ((JSONObject)
                                                ((JSONArray) jsonObject.get("results")
                                                ).get(0)
                                        ).get("series")
                                ).get(0)
                        ).get("values"));
        Pattern pattern = Pattern.compile(SERVICE_LIST_PATTERN);
        for(int i = 0; i < metricsJSONArray.length(); i++) {
            String metrics = ((JSONArray) metricsJSONArray.get(0)).get(0).toString();
            Matcher matcher = pattern.matcher(metrics);
        }
        return null;
    }
}
