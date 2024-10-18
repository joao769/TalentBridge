package com.example.repository;

import com.example.model.Vaga;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VagaRepository {
    private List<Vaga> vagas = new ArrayList<>();
    private long nextId = 1; // Contador para gerar IDs

    public void salvar(Vaga vaga) {
        if (vaga == null) {
            throw new IllegalArgumentException("A vaga não pode ser nula.");
        }
        vaga.setId(nextId++); // Atribui um novo ID antes de salvar
        vagas.add(vaga);
    }

    public List<Vaga> listarTodas() {
        return new ArrayList<>(vagas); // Retorna uma cópia para evitar modificações externas
    }

    public Optional<Vaga> buscarPorId(Long id) {
        return vagas.stream().filter(vaga -> vaga.getId().equals(id)).findFirst();
    }

    public void atualizar(Vaga vagaAtualizada) {
        if (vagaAtualizada == null) {
            throw new IllegalArgumentException("A vaga a ser atualizada não pode ser nula.");
        }

        Optional<Vaga> vagaExistenteOpt = buscarPorId(vagaAtualizada.getId());
        if (vagaExistenteOpt.isPresent()) {
            int index = vagas.indexOf(vagaExistenteOpt.get());
            vagas.set(index, vagaAtualizada);
        } else {
            throw new IllegalArgumentException("Vaga com ID " + vagaAtualizada.getId() + " não encontrada.");
        }
    }

    public void remover(Long vagaId) {
        boolean removed = vagas.removeIf(vaga -> vaga.getId().equals(vagaId));
        if (!removed) {
            throw new IllegalArgumentException("Vaga com ID " + vagaId + " não encontrada.");
        }
    }

    public long getLastVagaId() {
        return nextId - 1; // Retorna o último ID gerado
    }
}
