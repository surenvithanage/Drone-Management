package com.server.musalasoft.drone_management.bean.keyvalue;

public class KeyValueBean<T> {
    private String key;
    private T value;

    public KeyValueBean() {
    }

    public KeyValueBean(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
