package ru.example.parser;

import ru.example.parser.model.Product;
import ru.example.parser.serviceMethods.ServiceMethods;

import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final List<Product> PRODUCT_LIST = new ArrayList<>();

    public static void main(String[] args) {
        for (int counter = 0; counter < ServiceMethods.getCountOfProducts() / ServiceMethods.getProductsOnPage(); counter++) {
            ServiceMethods.addProductToList(PRODUCT_LIST, counter);
        }

       ServiceMethods.writeOnFile(PRODUCT_LIST);
    }
}
