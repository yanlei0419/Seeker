package org.seeker.test.test20180514.filter;

import java.util.ArrayList;
import java.util.List;

// 对过滤链的抽象（横切关注点），是多个过滤器的聚集，本质上，FilterChain 也可以看作是一个大的Filter
public class FilterChain {

    List<Filter> filters = new ArrayList<Filter>();
    int index = 0;

    // 链式编程
    public FilterChain addFilter(Filter filter){
        filters.add(filter);
        return this;    // 返回自身
    }

    public void doFilter(Request request, Response response) {
        if(index == filters.size()) return;
        Filter filter = filters.get(index);
        index++;
        filter.doFilter(request, response, this);
    }
}