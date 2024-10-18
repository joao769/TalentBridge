package com.example.service;

import com.example.model.Vaga;
import com.example.repository.VagaRepository;

import java.util.List;
import java.util.Optional;

public class VagaService {
    private VagaRepository vagaRepository = new VagaRepository();

    public void salvar(Vaga vaga) {
        if (vaga == null) {
            throw new IllegalArgumentException("A vaga não pode ser nula.");
        }
        vagaRepository.salvar(vaga);
    }

    public List<Vaga> listarVagas() {
        return vagaRepository.listarTodas();
    }

    public Optional<Vaga> buscarPorId(Long vagaId) {
        return vagaRepository.buscarPorId(vagaId);
    }

    public void atualizarVaga(Vaga vaga) {
        vagaRepository.atualizar(vaga);
    }

    public void removerVaga(Long vagaId) {
        vagaRepository.remover(vagaId);
    }
}
