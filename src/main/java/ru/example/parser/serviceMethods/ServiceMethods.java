package ru.example.parser.serviceMethods;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import ru.example.parser.model.Product;
import ru.example.parser.model.RespondFromPage;

import java.io.IOException;
import java.util.List;

public class ServiceMethods {
    private static final String PAGE_TO_PARSE = "https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery18308045810847012902_1616169506790&widget_id=5547572&platform=pc&limit=25&offset=%s&phase=2&productIds2Top=&postback=cc12216d-c90c-4072-a71b-7eddfdffae7c&_=1616169515462";

    public static void addProductToList(List<Product> productList, int counter) {
        HttpGet httpGet = new HttpGet(String.format(PAGE_TO_PARSE, counter * 25));

        RespondFromPage respondFromPage = new Gson().fromJson(getRespondFromPage(httpGet), RespondFromPage.class);
        productList.addAll(respondFromPage.getProductsList());
    }

    private static String getRespondFromPage(HttpGet httpGet) {
        String respondFromPageSubstring = "";

        try (CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
                CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet)) {

            HttpEntity httpEntity = closeableHttpResponse.getEntity();
            String outputString = EntityUtils.toString(httpEntity);
            respondFromPageSubstring = outputString.substring(outputString.indexOf('{'), outputString.lastIndexOf('}') + 1);

        } catch (ClientProtocolException e) {
            System.out.println("Connection problem");
        } catch (IOException e) {
            System.out.println("Can't read server response");
        }

        return respondFromPageSubstring;
    }
}
