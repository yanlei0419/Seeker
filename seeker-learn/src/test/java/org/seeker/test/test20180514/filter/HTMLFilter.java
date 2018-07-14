package seeker.test.test20180514.filter;

// 将请求消息中的"<>"替换成"[]"
public class HTMLFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        // process HTML Tag
        String msg = request.getRequest().replace("<", "[").replace(">", "]");
        request.setRequest(msg);

        chain.doFilter(request, response);

        response.setResponse(response.getResponse() + "--->HTMLFilter");
    }
}


//将请求消息中的"被就业"替换成"就业"
class SensitiveFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        String msg = request.getRequest().replace("被就业", "就业");
        request.setRequest(msg);

        chain.doFilter(request, response);

        response.setResponse(response.getResponse() + "--->SensitiveFilter");
    }
}

// 将请求消息中的":)"替换成"笑脸"
class FaceFilter implements Filter {

    public void doFilter(Request request, Response response, FilterChain chain) {
        String msg = request.getRequest().replace(":)", "笑脸");
        request.setRequest(msg);

        chain.doFilter(request, response);

        response.setResponse(response.getResponse() + "--->FaceFilter");
    }
}