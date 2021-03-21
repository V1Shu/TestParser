package ru.example.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Application {
    private static final String PAGE_TO_PARSE = "https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery18308045810847012902_1616169506790&widget_id=5547572&platform=pc&limit=12&offset=1&phase=1&productIds2Top=&postback=cc12216d-c90c-4072-a71b-7eddfdffae7c&_=1616169515462";

    public static void main(String[] args) {
        HttpGet httpGet =new HttpGet(PAGE_TO_PARSE);

        try(CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet)) {
            HttpEntity httpEntity = closeableHttpResponse.getEntity();
            String outputString = EntityUtils.toString(httpEntity);
            String productString = outputString.substring(outputString.indexOf('['), outputString.indexOf(']')+1);
            System.out.println(productString);
            JsonObject jsonObject = JsonParser.parseString(productString).getAsJsonObject();
            for (Object object : jsonObject.entrySet()) {
                System.out.println(object);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
