package com.shoptrap_ecommerce_backend.demo.dto;

public class DtoApiResponseWithToken {
    private Integer codeStatus;
    private String token;

    public DtoApiResponseWithToken(Integer codeStatus,String token){
        this.codeStatus = codeStatus;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Integer getCodeStatus() {
        return codeStatus;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCodeStatus(Integer codeStatus) {
        this.codeStatus = codeStatus;
    }

}
