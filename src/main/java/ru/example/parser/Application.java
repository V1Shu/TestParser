package ru.example.parser;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import ru.example.parser.model.Product;
import ru.example.parser.model.RespondFromPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final String PAGE_TO_PARSE = "https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery18308045810847012902_1616169506790&widget_id=5547572&platform=pc&limit=10&offset=%s&phase=2&productIds2Top=&postback=cc12216d-c90c-4072-a71b-7eddfdffae7c&_=1616169515462";

    public static void main(String[] args) {

        List<Product> productList = new ArrayList<>();

        for (int counter = 0; counter < 10; counter++) {
            HttpGet httpGet = new HttpGet(String.format(PAGE_TO_PARSE, 10 * counter));

            try(CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
                CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet)) {
                HttpEntity httpEntity = closeableHttpResponse.getEntity();
                String outputString = EntityUtils.toString(httpEntity);
                String respondFromPageSubstring = outputString.substring(outputString.indexOf('{'), outputString.lastIndexOf('}')+1);
                RespondFromPage respondFromPage = new Gson().fromJson(respondFromPageSubstring, RespondFromPage.class);
                productList.addAll(respondFromPage.getProductsList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File outPutFile = new File("products.csv");
        try (PrintWriter printWriter = new PrintWriter(outPutFile);) {

            printWriter.println(Product.printFirstLine());
            for (Product product : productList) {
                printWriter.println(product.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
}
