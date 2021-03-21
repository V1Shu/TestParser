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
import ru.example.parser.serviceMethods.ServiceMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final int COUNT_OF_PRODUCTS = 100;
    private static final int PRODUCTS_ON_PAGE = 25;
    private static final List<Product> PRODUCT_LIST = new ArrayList<>();

    public static void main(String[] args) {
        for (int counter = 0; counter < COUNT_OF_PRODUCTS / PRODUCTS_ON_PAGE; counter++) {
            ServiceMethods.addProductToList(PRODUCT_LIST, counter);
        }

        File outPutFile = new File("products.csv");
        try (PrintWriter printWriter = new PrintWriter(outPutFile);) {

            printWriter.println(Product.printFirstLine());
            for (Product product : PRODUCT_LIST) {
                printWriter.println(product.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
