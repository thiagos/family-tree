package com.thiagos.familytree.model.response;

public class FamilyTreeResponse {
    private Long resultCode;
    private String data;

    public Long getResultCode() {
        return resultCode;
    }

    public void setResultCode(Long resultCode) {
        this.resultCode = resultCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
