package com.example.android.bdmutantes;

import java.io.Serializable;

public class Mutante implements Serializable{
    private static final long serialVersionUID = 1633833011084400384L;
    String nome;
    private String[] skill;

    public Mutante(){
        super();
    }

    public Mutante(String name, String[] skill) {
        this.nome = name;
        this.skill = skill;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String[] getSkill() {
        return skill;
    }

    public void setSkill(String[] skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return nome;
    }
}
