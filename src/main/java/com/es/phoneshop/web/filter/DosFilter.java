package com.es.phoneshop.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DosFilter implements Filter {
    private static int maxCount = 10;
    private static int intervalSeconds = 5;
    private Map<String, AtomicInteger> requestCountMap = Collections.synchronizedMap(new HashMap<>());
    private Map<String, AtomicLong> requestTimerMap = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String address = servletRequest.getRemoteAddr();
        AtomicInteger count = requestCountMap.get(address);
        if(count == null){
            requestCountMap.put(address, new AtomicInteger(1));
            requestTimerMap.put(address, new AtomicLong(System.currentTimeMillis()));
        }
        else if (count.intValue() > maxCount){
            Long difference = System.currentTimeMillis() - requestTimerMap.get(address).longValue();
            AtomicLong value = new AtomicLong(difference);
            if(value.longValue() / intervalSeconds < 1000){
                ((HttpServletResponse)servletResponse).sendError(429);
                return;
            }
            else {
                requestCountMap.put(address, new AtomicInteger(1));
                requestTimerMap.put(address, new AtomicLong(System.currentTimeMillis()));
            }
        } else {
            count.incrementAndGet();
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
