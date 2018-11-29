package com.tads.jorge.mutantesdb;

import java.util.List;

public class Mutante {

    private int id;
    private String mutanteName;
    private List<Skill> skills;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMutanteName() {
        return mutanteName;
    }

    public void setMutanteName(String mutanteName) {
        this.mutanteName = mutanteName;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

}

