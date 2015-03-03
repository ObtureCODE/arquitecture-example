package com.obturecode.vallahackathon.data.error;

/**
 * Created by husky on 03/03/15.
 */
public class ResponseError extends Error {
    private int statusCode;
    public ResponseError(int statusCode){
        this.statusCode = statusCode;
    }

    public int getStatusCode(){
        return statusCode;
    }
}
