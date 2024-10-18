package com.example.service;

import com.example.model.Vaga;
import com.example.repository.VagaRepository;
import com.example.repository.EmpresaRepository; // Importa o repositório de Empresa

import java.util.List;
import java.util.Optional;

public class VagaService {
    private VagaRepository vagaRepository = new VagaRepository();
    private EmpresaRepository empresaRepository = new EmpresaRepository(); // Instância do repositório de Empresa

    public void salvar(Vaga vaga) {
        if (vaga == null) {
            throw new IllegalArgumentException("A vaga não pode ser nula.");
        }
        vagaRepository.salvar(vaga); // Atribui o ID dentro do repositório
    }

    public boolean associarEmpresa(Long vagaId, Long empresaId) {
        Optional<Vaga> vaga = vagaRepository.buscarPorId(vagaId);
        if (vaga.isEmpty()) {
            throw new IllegalArgumentException("Vaga com ID " + vagaId + " não encontrada.");
        }

        // Verifica se a empresa existe
        if (empresaRepository.buscarPorId(empresaId) == null) {
            throw new IllegalArgumentException("Empresa com ID " + empresaId + " não encontrada.");
        }

        // Aqui você deve adicionar a lógica para associar a vaga à empresa, por exemplo:
        vaga.get().setEmpresaId(empresaId); // Supondo que Vaga tem um método setEmpresaId()
        return true; // Retorna true se a associação foi feita com sucesso
    }

    public List<Vaga> listarVagas() {
        return vagaRepository.listarTodas();
    }
}
