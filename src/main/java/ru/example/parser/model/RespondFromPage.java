package ru.example.parser.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Contains respond from page with data
 */
public class RespondFromPage {

    private String contextId;
    private Boolean success;
    private Long code;
    @SerializedName("results")
    private List<Product> productsList;
    private Boolean finished;
    private Long page;
    private Long pageSize;
    private String postback;
    private String pin;

    @Override
    public String toString() {
        return "RespondFromPage{" +
                "contextId='" + contextId + '\'' +
                ", success=" + success +
                ", code=" + code +
                ", productsList=" + productsList +
                ", finished=" + finished +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", postback='" + postback + '\'' +
                ", pin='" + pin + '\'' +
                '}';
    }

    public List<Product> getProductsList() {
        return productsList;
    }
}
