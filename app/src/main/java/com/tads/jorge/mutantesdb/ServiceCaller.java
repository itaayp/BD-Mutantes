package com.tads.jorge.mutantesdb;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;
public class ServiceCaller implements Response.Listener, Response.ErrorListener {
    public static RequestQueue mQueue;

    public void ServiceCaller(Context context){
        mQueue = CustomVolleyRequestQueue.getInstance(context).getRequestQueue();

    }

    @Override
    public void onErrorResponse(VolleyError error){

    }

    @Override
    public void onResponse(Object response) {

    }


    public List pesquisarNome(String s) {
        return null;
    }

    public List buscarPorHabilidade(String s) {
        return null;
    }

    public List getAllMutantes() {
        return null;
    }

    public ServiceResponse editMutante(Mutante m) {
        ServiceResponse serviceResponse = new ServiceResponse();
        return serviceResponse;
    }

    public Mutante getMutante(int mutanteId) {
        return null;
    }

    public boolean deleteMutante(int mutanteId) {

        return false;
    }

    public ServiceResponse addMutante(Mutante m) {
        ServiceResponse serviceResponse = new ServiceResponse();
        return serviceResponse;
    }

    public ServiceResponse addUser(User u) {
        ServiceResponse serviceResponse = new ServiceResponse();
        return serviceResponse;
    }

    public ServiceResponse getUser(User u) {
        // verificar na service se o usuario e senha estão corretos
        // retornar response.setSucess true se estiverem corretos
        // retornar response.setSucess false se tiver campos errados com msg
        // retornar response null se ocorreu erro na chamada de service
        ServiceResponse serviceResponse = new ServiceResponse();





        return serviceResponse;
    }

}
