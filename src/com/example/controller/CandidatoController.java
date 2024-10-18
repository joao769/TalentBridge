package com.example.controller;

import com.example.model.Candidato;
import com.example.model.Vaga;

import java.util.ArrayList;
import java.util.List;

public class CandidatoController {
    private List<Candidato> candidatos = new ArrayList<>(); // Para armazenar candidatos

    public void adicionarCandidato(Candidato candidato) {
        candidatos.add(candidato);
    }

    public List<Vaga> listarVagasCandidatas(String cpf) {
        List<Vaga> vagasCandidatas = new ArrayList<>();
        for (Candidato candidato : candidatos) {
            if (candidato.getCpf().equals(cpf)) {
                // Aqui você deve adicionar a lógica para buscar as vagas às quais o candidato se candidatou
                vagasCandidatas.addAll(candidato.getVagasCandidatas());
            }
        }
        return vagasCandidatas;
    }
}
