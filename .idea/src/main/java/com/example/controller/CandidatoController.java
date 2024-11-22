package com.example.controller;

import com.example.model.Candidato;
import com.example.repository.CandidatoRepository;

public class CandidatoController {
    private CandidatoRepository repository = new CandidatoRepository();

    public void adicionarCandidato(Candidato candidato) {
        repository.salvar(candidato);
    }
}
