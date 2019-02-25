package com.thiagos.familytree.model.response;

public class GenericResponse {
    private Long resultCode;
    private String resultMessage;

    public GenericResponse() {
    }

    public GenericResponse(Long resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public Long getResultCode() {
        return resultCode;
    }

    public void setResultCode(Long resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
