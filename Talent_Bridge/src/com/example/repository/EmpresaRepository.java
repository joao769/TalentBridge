package com.example.repository;

import com.example.model.Candidato;
import com.example.model.Empresa;
import com.example.model.Vaga;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpresaRepository {
    private List<Empresa> empresas = new ArrayList<>();

    public void salvar(Empresa empresa) {
        if (empresa == null) {
            throw new IllegalArgumentException("A empresa não pode ser nula.");
        }
        if (empresa.getId() != null && buscarPorId(empresa.getId()).isPresent()) {
            throw new IllegalArgumentException("Uma empresa com este ID já está cadastrada.");
        }
        empresas.add(empresa);
    }

    public List<Empresa> listarTodas() {
        return new ArrayList<>(empresas); // Retorna uma cópia para evitar modificações externas
    }

    public Optional<Empresa> buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }
        return empresas.stream()
                .filter(empresa -> empresa.getId().equals(id))
                .findFirst();
    }

    public void atualizarVaga(Long empresaId, Long vagaId, Vaga vagaAtualizada) {
        if (vagaAtualizada == null) {
            throw new IllegalArgumentException("A vaga a ser atualizada não pode ser nula.");
        }

        Empresa empresa = buscarPorId(empresaId).orElseThrow(() ->
                new IllegalArgumentException("Empresa com ID " + empresaId + " não encontrada."));

        Vaga vagaExistente = empresa.getListaDeVagas().stream()
                .filter(vaga -> vaga.getId().equals(vagaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Vaga com ID " + vagaId + " não encontrada."));

        vagaAtualizada.setId(vagaId); // Mantenha o mesmo ID
        int index = empresa.getListaDeVagas().indexOf(vagaExistente);
        empresa.getListaDeVagas().set(index, vagaAtualizada); // Atualiza a vaga na lista
    }

    public void removerVaga(Long empresaId, Long vagaId) {
        Empresa empresa = buscarPorId(empresaId).orElseThrow(() ->
                new IllegalArgumentException("Empresa com ID " + empresaId + " não encontrada."));

        boolean removed = empresa.getListaDeVagas().removeIf(vaga -> vaga.getId().equals(vagaId));
        if (!removed) {
            throw new IllegalArgumentException("Vaga com ID " + vagaId + " não encontrada.");
        }
    }

    public List<Candidato> visualizarCandidatos(Long empresaId) {
        Empresa empresa = buscarPorId(empresaId).orElseThrow(() ->
                new IllegalArgumentException("Empresa com ID " + empresaId + " não encontrada."));
        return empresa.getListaDeCandidatos(); // Retorna a lista correta
    }

    public void adicionarCandidatoAVaga(Long empresaId, Long vagaId, Candidato candidato) {
        if (candidato == null) {
            throw new IllegalArgumentException("Candidato não pode ser nulo.");
        }

        Empresa empresa = buscarPorId(empresaId).orElseThrow(() ->
                new IllegalArgumentException("Empresa com ID " + empresaId + " não encontrada."));

        Vaga vaga = empresa.getListaDeVagas().stream()
                .filter(v -> v.getId().equals(vagaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Vaga com ID " + vagaId + " não encontrada."));

        // Verifica se o candidato já se inscreveu na vaga
        if (vaga.getCandidatos().contains(candidato)) {
            throw new IllegalArgumentException("Candidato já se inscreveu nesta vaga.");
        }

        vaga.adicionarCandidato(candidato);
        if (!empresa.getListaDeCandidatos().contains(candidato)) {
            empresa.getListaDeCandidatos().add(candidato); // Adiciona o candidato à lista da empresa, se ainda não estiver
        }
    }

    public void removerCandidatoDeVaga(Long empresaId, Long vagaId, Candidato candidato) {
        Empresa empresa = buscarPorId(empresaId).orElseThrow(() ->
                new IllegalArgumentException("Empresa com ID " + empresaId + " não encontrada."));

        Vaga vaga = empresa.getListaDeVagas().stream()
                .filter(v -> v.getId().equals(vagaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Vaga com ID " + vagaId + " não encontrada."));

        vaga.removerCandidato(candidato);
        empresa.getListaDeCandidatos().remove(candidato); // Remove o candidato da lista da empresa
    }
}
