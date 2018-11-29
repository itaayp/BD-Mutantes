package com.tads.jorge.mutantesdb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceCaller {
    public static RequestQueue mQueue;

    public ServiceCaller(){

    }

    public ServiceCaller(Context context){
        mQueue = CustomVolleyRequestQueue.getInstance(context).getRequestQueue();

    }

    public ServiceResponse pesquisarNome(String s,PesquisarActivity tela) {
        ServiceResponse serviceResponse = new ServiceResponse();
        String url = "http://192.168.100.56:8084/trabWSMutantes/webresources/mutantes/nome/" + s ;

        final CustomJsonObjectRequest jsonRequest = new CustomJsonObjectRequest(Request.Method.GET,url,new JSONObject(),tela,tela);
        jsonRequest.setTag("UserAutentication");

        mQueue.add(jsonRequest);
        return serviceResponse;
    }

    public ServiceResponse buscarPorHabilidade(String s,PesquisarActivity tela) {
        ServiceResponse serviceResponse = new ServiceResponse();
        String url = "http://192.168.100.56:8084/trabWSMutantes/webresources/mutantes/skills/" + s ;

        final CustomJsonObjectRequest jsonRequest = new CustomJsonObjectRequest(Request.Method.GET,url,new JSONObject(),tela,tela);
        jsonRequest.setTag("UserAutentication");

        mQueue.add(jsonRequest);

        return serviceResponse;
    }

    public ServiceResponse getAllMutantes(ListarActivity tela) {
        ServiceResponse serviceResponse = new ServiceResponse();
        String url = "http://192.168.100.56:8084/trabWSMutantes/webresources/mutantes/mutantes/";
        final CustomJsonObjectRequest jsonRequest = new CustomJsonObjectRequest(Request.Method.GET,url,new JSONObject(),tela,tela);
        jsonRequest.setTag("UserAutentication");

        mQueue.add(jsonRequest);
        return serviceResponse;
    }

    public ServiceResponse editMutante(Mutante m) {
        ServiceResponse serviceResponse = new ServiceResponse();
        return serviceResponse;
    }

    public ServiceResponse getMutante(int mutanteId ) {
        ServiceResponse serviceResponse = new ServiceResponse();
//        String url = "http://192.168.100.56:8084/trabWSMutantes/webresources/mutantes/skills/" + mutanteId ;
//
//        final CustomJsonObjectRequest jsonRequest = new CustomJsonObjectRequest(Request.Method.GET,url,new JSONObject(),tela,tela);
//        jsonRequest.setTag("UserAutentication");
//
//        mQueue.add(jsonRequest);
        return serviceResponse;
    }

    public ServiceResponse deleteMutante(int mutanteId) {
        ServiceResponse serviceResponse = new ServiceResponse();
        return serviceResponse;
    }

    public ServiceResponse addMutante(Mutante m, CadastrarActivity tela) {
        ServiceResponse serviceResponse = new ServiceResponse();
        String url = "http://192.168.100.56:8084/trabWSMutantes/webresources/mutantes/mutante";
        Map<String, Object> postParam= new HashMap<String, Object>();
        //postParam.put("Content-Type", "application/json; charset=utf-8");
        postParam.put("id",0);
        postParam.put("mutanteName",m.getMutanteName());
        postParam.put("skills",m.getSkills());

        final CustomJsonObjectRequest jsonRequest = new CustomJsonObjectRequest(Request.Method.POST,url,new JSONObject(postParam),tela,tela);
        jsonRequest.setTag("UserAutentication");

        mQueue.add(jsonRequest);
        return serviceResponse;
    }

    public ServiceResponse addUser(User u, CadastrarUsuarioActivity tela) {
        ServiceResponse serviceResponse = new ServiceResponse();
        String url = "http://192.168.100.56:8084/trabWSMutantes/webresources/mutantes/user";
        Map<String, Object> postParam= new HashMap<String, Object>();
        //postParam.put("Content-Type", "application/json; charset=utf-8");
        postParam.put("id",0);
        postParam.put("username",u.getUsername());
        postParam.put("password",u.getPassword());

        final CustomJsonObjectRequest jsonRequest = new CustomJsonObjectRequest(Request.Method.POST,url,new JSONObject(postParam),tela,tela);
        jsonRequest.setTag("UserAutentication");

        mQueue.add(jsonRequest);

        return serviceResponse;
    }

    public ServiceResponse getUser(User u,LoginActivity login) {
        ServiceResponse serviceResponse = new ServiceResponse();
        String url = "http://192.168.100.56:8084/trabWSMutantes/webresources/mutantes/login";
        Map<String, Object> postParam= new HashMap<String, Object>();
        //postParam.put("Content-Type", "application/json; charset=utf-8");
        postParam.put("id",0);
            postParam.put("username",u.getUsername());
            postParam.put("password",u.getPassword());

        final CustomJsonObjectRequest jsonRequest = new CustomJsonObjectRequest(Request.Method.POST,url,new JSONObject(postParam),login,login);
        jsonRequest.setTag("UserAutentication");

        mQueue.add(jsonRequest);

        return serviceResponse;
    }

}
