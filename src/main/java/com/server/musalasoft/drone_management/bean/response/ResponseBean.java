package com.server.musalasoft.drone_management.bean.response;

import com.server.musalasoft.drone_management.bean.keyvalue.KeyValueBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseBean implements Serializable {
    private static final long serialVersionUID = -8064824073358277119L;

    private boolean requestOk;
    private String messageType; //S -> successMessage ,W -> warningMessage ,E -> errorMessage
    private String message;
    private List<KeyValueBean> data = new ArrayList<KeyValueBean>();

    public ResponseBean() {
    }

    public ResponseBean(boolean requestOk) {
        this.requestOk = requestOk;
    }

    public ResponseBean(boolean requestOk, String message) {
        this.requestOk = requestOk;
        this.message = message;
    }

    public boolean isRequestOk() {
        return requestOk;
    }

    public void setRequestOk(boolean requestOk) {
        this.requestOk = requestOk;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<KeyValueBean> getData() {
        return data;
    }

    public void setData(List<KeyValueBean> data) {
        this.data = data;
    }

    public void setData(KeyValueBean... kv) {
        for (KeyValueBean o : kv) {
            data.add(o);
        }
    }
}
