package ru.example.parser;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;

public class Application {
    private static final String PAGE_TO_PARSE = "https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery18308045810847012902_1616169506790&widget_id=5547572&platform=pc&limit=12&offset=12&phase=1&productIds2Top=&postback=cc12216d-c90c-4072-a71b-7eddfdffae7c&_=1616169515462";
    private static final String TEST_PAGE = "https://flashdeals.aliexpress.com/en.htm?";

    public static void main(String[] args) {
        HttpGet httpGet =new HttpGet(PAGE_TO_PARSE);

        try(CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet)) {
            HttpEntity httpEntity = closeableHttpResponse.getEntity();
            System.out.println(EntityUtils.toString(httpEntity));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
