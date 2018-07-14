package org.seeker.test.test20180514.filter;

// 对请求消息的抽象
public class Request {

    // 请求消息
    private String request;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

}


// 对响应消息的抽象
class Response {

    // 响应消息
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}