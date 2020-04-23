package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    int total = 0;
    public static void main(String[] args) {
        String strStockInfo="亚盛集团(600108)";
        Pattern stockCodePattern = Pattern.compile("(?<=\\()[^\\)]+");
        String stockNamePattern="[\\[][^\\[\\]]+[\\]]|[\\(][^\\(\\)]+[\\)]";
        Matcher m = stockCodePattern.matcher(strStockInfo);
        String stockCode = m.find() ? m.group() : null;

        String stockName = strStockInfo.replaceAll(stockNamePattern, "");
        System.out.println(stockCode);

    }
    public int add(int a, int b){
        total = a+b;
        System.out.println("inner total="+total);
        return total;

    }

}
