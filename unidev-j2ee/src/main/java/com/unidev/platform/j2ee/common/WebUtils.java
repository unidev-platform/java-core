package com.unidev.platform.j2ee.common;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Common J2EE related stuff
 */
public class WebUtils {

    static final List<String> CLIENT_IP_KEYS = new ArrayList<String>() {
        {
            add("CF-Connecting-IP");
            add("HTTP_CF_CONNECTING_IP");
            add("HTTP_X_FORWARDED_FOR");
            add("X-Forwarded-For");
            add("HTTP_CLIENT_IP");
            add("x-real-ip");
            add("Proxy-Client-IP");
            add("WL-Proxy-Client-IP");
        }
    };
    /**
     * Get client IP from http request, it is checked x-real-ip or remote address header
     * @param request HTTP request
     * @return client ip or null value
     */
    public String getClientIp(HttpServletRequest request) {
        for(String ipKey : CLIENT_IP_KEYS) {
            String ip = request.getHeader(ipKey);
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                continue;
            }
            return ip;
        }
        return request.getRemoteAddr();
    }
    /**
     * Extract user agent from http request
     * @param request
     * @return
     */
    public String getUserAgent(HttpServletRequest request) {
        return request.getHeader("user-agent");
    }
    /**
     * Extract referer from http request
     * @param request
     * @return
     */
    public String getReferer(HttpServletRequest request) {
        return request.getHeader("referer") + "";
    }
    /**
     * Get domain from request
     * @param request
     * @return
     */
    public String getDomain(HttpServletRequest request) {
        String domain = null;
        try {
            domain = new URL(request.getRequestURL().toString()).getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return domain;
    }
    /**
     * Get starting part of url, schema and domain
     * @param request
     * @return http://google.com or https://google.com
     */
    public String getUrlStart(HttpServletRequest request) {
        String scheme = request.getScheme();
        String domain = getDomain(request);
        try {
            URL url = new URL(request.getRequestURL().toString());
            int port = url.getPort();
            return url.getProtocol() + "://" + url.getHost() +  (port != -1  ?  (":" + url.getPort()) : "");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * List all headers from request
     * @param request
     * @return
     */
    public List<Map.Entry<String, Object>> listAllHeaders(HttpServletRequest request) {
        List<Map.Entry<String, Object>> headers = new ArrayList<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            final  String headerName = headerNames.nextElement();
            final String headerValue = request.getHeader(headerName);
            headers.add(new Map.Entry<String, Object>() {
                @Override
                public String getKey() {
                    return headerName;
                }
                @Override
                public Object getValue() {
                    return headerValue;
                }
                @Override
                public Object setValue(Object value) {
                    return null;
                }
            });
        }
        return headers;
    }
    /**
     * Remove WWW from domain, if have
     * @param domain Not null domain
     * @return
     */
    public String removeWWW(String domain) {
        if (domain.toLowerCase().contains("www.")) {
            return domain.substring(4);
        } else {
            return domain;
        }
    }

}
