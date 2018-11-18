package com.example.android.bdmutantes;

import java.io.Serializable;

public class Mutante implements Serializable{
    private static final long serialVersionUID = 1633833011084400384L;
    String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
