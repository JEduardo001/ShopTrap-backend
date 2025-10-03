package com.shoptrap_ecommerce_backend.demo.dto;

public class DtoApiResponse<T> {

    private Integer codeStatus;
    private String message;
    private T data;

    public DtoApiResponse(Integer codeStatus,String message, T data){
        this.codeStatus = codeStatus;
        this.message = message;
        this.data = data;
    }

    public DtoApiResponse(Integer codeStatus,String message){
        this.codeStatus = codeStatus;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCodeStatus() {
        return codeStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCodeStatus(Integer codeStatus) {
        this.codeStatus = codeStatus;
    }

    public void setData(T data) {
        this.data = data;
    }
}
