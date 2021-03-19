package ru.example.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Application {
    private static final String TEST_PAGE = "https://flashdeals.aliexpress.com/en.htm?";

    public static void main(String[] args) {

        Document document = null;
        try {
            document = Jsoup.connect(TEST_PAGE).get();
            System.out.println(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
