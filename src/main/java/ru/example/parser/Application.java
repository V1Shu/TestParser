package ru.example.parser;

import org.apache.http.client.methods.HttpGet;

public class Application {
    private static final String PAGE_TO_PARSE = "https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery18308045810847012902_1616169506790&widget_id=5547572&platform=pc&limit=12&offset=12&phase=1&productIds2Top=&postback=cc12216d-c90c-4072-a71b-7eddfdffae7c&_=1616169515462";
    private static final String TEST_PAGE = "https://flashdeals.aliexpress.com/en.htm?";

    public static void main(String[] args) {
        HttpGet httpGet =new HttpGet("PAGE_TO_PARSE");

    }
}
