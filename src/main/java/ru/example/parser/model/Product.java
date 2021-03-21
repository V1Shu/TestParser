package ru.example.parser.model;

public class Product {
    private Long productId;
    private Long sellerId;
    private String oriMinPrice;
    private String oriMaxPrice;
    private Long promotionId;
    private Long startTime;
    private Long endTime;
    private Long phase;
    private String productTitle;
    private String minPrice;
    private String maxPrice;
    private String discount;
    private String totalStock;
    private String stock;
    private String orders;
    private Boolean soldout;
    private String productImage;
    private String productDetailUrl;
    private String trace;
    private String totalTranpro3;
    private String productPositiveRate;
    private String productAverageStar;
    private Long itemEvalTotalNum;
    private Long gmtCreate;

    public static String printFirstLine() {
        return "productId" +
                ", sellerId" +
                ", oriMinPrice" +
                ", oriMaxPrice" +
                ", promotionId" +
                ", startTime" +
                ", endTime" +
                ", phase" +
                ", productTitle" +
                ", minPrice" +
                ", maxPrice" +
                ", discount" +
                ", totalStock" +
                ", stock" +
                ", orders" +
                ", soldout" +
                ", productImage" +
                ", productDetailUrl" +
                ", trace" +
                ", totalTranpro3" +
                ", productPositiveRate" +
                ", productAverageStar" +
                ", itemEvalTotalNum" +
                ", gmtCreate";
    }

    @Override
    public String toString() {
        return productId + "," +
                sellerId + "," +
                oriMinPrice + "," +
                oriMaxPrice + "," +
                promotionId + "," +
                startTime + "," +
                endTime + "," +
                phase + "," +
                productTitle + "," +
                minPrice + "," +
                maxPrice + "," +
                discount + "," +
                totalStock + "," +
                stock + "," +
                orders + "," +
                soldout + "," +
                productImage + "," +
                productDetailUrl + "," +
                trace + "," +
                totalTranpro3 + "," +
                productPositiveRate + "," +
                productAverageStar + "," +
                itemEvalTotalNum + "," +
                gmtCreate;
    }
}
