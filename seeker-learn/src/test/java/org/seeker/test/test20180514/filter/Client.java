package org.seeker.test.test20180514.filter;

public class Client {
    public static void main(String[] args) {
        // 待处理消息
        String msg = "大家好 :),<script>,敏感,被就业,网络授课没感觉...";

        // 设置请求消息
        Request request = new Request();
        request.setRequest(msg);

        // 设置响应消息
        Response response = new Response();
        response.setResponse("Response");

        // 设置处理链
        FilterChain chain = new FilterChain();
        chain.addFilter(new HTMLFilter()).addFilter(new SensitiveFilter())
                .addFilter(new FaceFilter());

        // 开始处理
        chain.doFilter(request, response);

        // 消息的预处理结果
        System.out.println(request.getRequest());

        // 消息的后处理结果
        System.out.println(response.getResponse());
    }
}/* Output(完全一致): 
        大家好 笑脸,[script],敏感,就业,网络授课没感觉...
        Response--->FaceFilter--->SensitiveFilter--->HTMLFilter
 *///:~