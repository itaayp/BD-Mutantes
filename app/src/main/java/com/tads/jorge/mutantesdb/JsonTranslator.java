package com.tads.jorge.mutantesdb;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonTranslator {

    public static Mutante getMuttante(Object response){
        return getMutante((JSONObject)response);
    }
    public static List<Mutante> getListMuttante(Object response){
        return getListMutante((JSONArray) response);
    }


    private static Mutante getMutante(JSONObject json){
        //JSONObject json = ((JSONObject) object);
        Mutante m = new Mutante();
        try {
            m.setId(json.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            m.setMutanteName(json.getString("mutanteName"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            m.setSkills(getListSkill(json.getJSONArray("skills")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return m;

    }

    private static Skill getSkill(JSONObject object){
        Skill skill = new Skill();
        try {
            skill.setId(object.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            skill.setSkillName(object.getString("skillName"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return skill;

    }

    private static List<Skill> getListSkill(JSONArray jsonArray){
        List<Skill> listaSkills = new ArrayList<Skill>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                listaSkills.add(getSkill(obj));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaSkills;
    }
    private static List<Mutante> getListMutante(JSONArray jsonArray){
        List<Mutante> listaMutante= new ArrayList<Mutante>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                listaMutante.add(getMutante(obj));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaMutante;
    }

}
