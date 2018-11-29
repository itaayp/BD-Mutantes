package com.tads.jorge.mutantesdb;


public class Skill {

    private int id;
    private String skillName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public boolean isValid() {
        return !(skillName == null || skillName.isEmpty());
    }



}