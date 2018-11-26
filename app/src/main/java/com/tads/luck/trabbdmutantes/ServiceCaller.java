package com.tads.luck.trabbdmutantes;

import java.util.List;

public class ServiceCaller {


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
