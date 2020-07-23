package com.example.appproductos;

import org.json.JSONObject;
import java.util.List;

public class APIResponse {
    private String status, message;
    int code;
    List<JSONObject> data;

    public APIResponse() {
    }

    public APIResponse(String status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public APIResponse(String status, int code, List<JSONObject> data) {
        this.status = status;
        this.code = code;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<JSONObject> getData() {
        return data;
    }

    public void setData(List<JSONObject> data) {
        this.data = data;
    }
}
