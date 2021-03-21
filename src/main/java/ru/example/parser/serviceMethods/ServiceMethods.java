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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Methods for taking data from page, convert if and to write at file
 */
public abstract class ServiceMethods {
    /**
     * Contains address for taking data.
     *
     */
    private static final String PAGE_TO_PARSE = "https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery18308045810847012902_1616169506790&widget_id=5547572&platform=pc&limit=%s&offset=%s&phase=2&productIds2Top=&postback=cc12216d-c90c-4072-a71b-7eddfdffae7c&_=1616169515462";
    /**
     * Count of products needed to write
     */
    private static final int COUNT_OF_PRODUCTS = 100;
    /**
     * Limit of products on page (can be <= 0 and > 50)
     */
    private static final int PRODUCTS_ON_PAGE = 25;
    /**
     * Name for output file
     */
    private static final String fileName = "products.csv";

    public static int getCountOfProducts() {
        return COUNT_OF_PRODUCTS;
    }

    public static int getProductsOnPage() {
        return PRODUCTS_ON_PAGE;
    }

    /**
     * Take respond from page and input it to result list
     * @param productList result list
     * @param pageCounter number of data page
     */
    public static void addProductToList(List<Product> productList, int pageCounter) {

        HttpGet httpGet = new HttpGet(String.format(PAGE_TO_PARSE, PRODUCTS_ON_PAGE, pageCounter * PRODUCTS_ON_PAGE));

        //convert respond from json to Respond class
        RespondFromPage respondFromPage = new Gson().fromJson(getRespondFromPage(httpGet), RespondFromPage.class);

        productList.addAll(respondFromPage.getProductsList());
    }

    /**
     * Connected to page and get entity
     * @param httpGet
     * @return respond string
     */
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

    /**
     * Write products to file
     * @param productList
     */
    public static void writeOnFile(List<Product> productList) {
        if(productList.isEmpty()) {
            System.out.println("No products to write");
        }

        File outPutFile = new File(fileName);
        try (PrintWriter printWriter = new PrintWriter(outPutFile);) {

            printWriter.println(Product.printFirstLine());
            for (Product product : productList) {
                printWriter.println(product.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't write on file");
        }
    }
}
