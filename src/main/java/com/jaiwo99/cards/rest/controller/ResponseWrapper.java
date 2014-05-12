package com.jaiwo99.cards.controller;

/**
 * @author liang - jaiwo99@gmail.com
 */
public class ResponseWrapper<T> {

    private final int httpStatus;

    private final T payload;

    public static <T> ResponseWrapper getInstance(int httpStatus, T payload) {
        return new ResponseWrapper<>(httpStatus, payload);
    }

    private ResponseWrapper(int httpStatus, T payload) {
        this.httpStatus = httpStatus;
        this.payload = payload;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public T getPayload() {
        return payload;
    }
}
