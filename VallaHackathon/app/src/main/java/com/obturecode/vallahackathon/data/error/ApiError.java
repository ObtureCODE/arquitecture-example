package com.obturecode.vallahackathon.data.error;

/**
 * Created by husky on 03/03/15.
 */
public class ApiError extends Error {
    private String code;
    public ApiError(String msg,String code) {
        super(msg);
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
