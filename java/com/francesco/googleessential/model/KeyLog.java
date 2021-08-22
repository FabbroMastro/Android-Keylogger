package com.francesco.googleessential.model;

import java.util.Date;


public class KeyLog {

    private Date keyLogDate;
    private String accessibilityEvent;
    private String msg;

    public Date getKeyLogDate() {
        return keyLogDate;
    }

    public void setKeyLogDate(Date keyLogDate) {
        this.keyLogDate = keyLogDate;
    }

    public String getAccessibilityEvent() {
        return accessibilityEvent;
    }

    public void setAccessibilityEvent(String accessibilityEvent) {
        this.accessibilityEvent = accessibilityEvent;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
