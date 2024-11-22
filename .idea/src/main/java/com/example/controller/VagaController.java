package com.example.controller;

import com.example.model.Vaga;
import com.example.repository.VagaRepository;

public class VagaController {
    private VagaRepository repository = new VagaRepository();

    public void adicionarVaga(Vaga vaga) {
        repository.salvar(vaga);
    }
}
