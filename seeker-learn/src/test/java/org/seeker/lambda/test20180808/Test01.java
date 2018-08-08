package org.seeker.lambda.test20180808;

import java.util.Arrays;
import java.util.List;

public class Test01 {
    public static void main(String[] args) {
        // 为每个订单加上12%的税

// 老方法：

//        List costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
//
//        double total = 0;
//
//        for(Integer cost : costBeforeTax) {
//
//            double
//                    price = cost + .12 * cost;
//
//            total = total + price;
//
//        }
//
//        System.out.println("Total : " + total);


// 新方法：

        List<Double> costBeforeTax = Arrays.asList(100d, 200d, 300d, 400d, 500d);

        double bill = costBeforeTax.stream().map((cost) -> cost + 0.12 * cost).reduce((sum, cost) -> sum + cost).get();

        System.out.println("Total : " + bill);
    }
}
