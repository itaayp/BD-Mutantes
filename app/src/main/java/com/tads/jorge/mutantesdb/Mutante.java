package com.tads.jorge.mutantesdb;

import java.io.Serializable;

public class Mutante implements Serializable{

    private int id;
    private String name;
    private String[] skill;

    public Mutante(){
        super();
    }

    public Mutante(String name, String[] skill) {
        this.name = name;
        this.skill = skill;
    }

    public Mutante(int id, String name, String[] skill) {
        this.id = id;
        this.name = name;
        this.skill = skill;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSkill() {
        return skill;
    }

    public void setSkill(String[] skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return name;
    }
}

