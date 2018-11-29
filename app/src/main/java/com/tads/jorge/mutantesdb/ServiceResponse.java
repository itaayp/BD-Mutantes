package com.tads.jorge.mutantesdb;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class ServiceResponse implements Response.Listener, Response.ErrorListener {


    public static boolean received = false;
    public static boolean sucess;
    private String msg;
    private Object response;

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

//    @Override
//    public void onErrorResponse(VolleyError error) {
//        Log.v("Logging",error.getMessage());
//    }
//
//    @Override
//    public void onResponse(Object response) {
//        Log.v("Logging","Response is : " + response);
//    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.v("Logging",error.getMessage());
        received = true;
        sucess = false;
        msg = error.getMessage();
    }

    @Override
    public void onResponse(Object response) {
        Log.v("Logging","Response is : " + response);
        received = true;
        sucess = true;
        this.response = response;
    }
}
