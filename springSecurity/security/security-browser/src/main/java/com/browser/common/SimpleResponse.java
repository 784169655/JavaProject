package com.browser.common;

/**
 * Created by  邱伟
 * 2018/5/19 10:23
 */

public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}