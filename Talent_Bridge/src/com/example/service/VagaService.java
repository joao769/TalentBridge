package com.example.service;

import com.example.model.Vaga;
import com.example.repository.VagaRepository;
<<<<<<< HEAD
=======
import com.example.repository.EmpresaRepository; // Importa o repositório de Empresa
>>>>>>> 51da9c7e0b3b30c2a46b79eff28599efc8d7c368

import java.util.List;
import java.util.Optional;

public class VagaService {
    private VagaRepository vagaRepository = new VagaRepository();
<<<<<<< HEAD
=======
    private EmpresaRepository empresaRepository = new EmpresaRepository(); // Instância do repositório de Empresa
>>>>>>> 51da9c7e0b3b30c2a46b79eff28599efc8d7c368

    public void salvar(Vaga vaga) {
        if (vaga == null) {
            throw new IllegalArgumentException("A vaga não pode ser nula.");
        }
<<<<<<< HEAD
        vagaRepository.salvar(vaga);
=======
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
>>>>>>> 51da9c7e0b3b30c2a46b79eff28599efc8d7c368
    }

    public List<Vaga> listarVagas() {
        return vagaRepository.listarTodas();
    }
<<<<<<< HEAD

    public Optional<Vaga> buscarPorId(Long vagaId) {
        return vagaRepository.buscarPorId(vagaId);
    }

    public void atualizarVaga(Vaga vaga) {
        vagaRepository.atualizar(vaga);
    }

    public void removerVaga(Long vagaId) {
        vagaRepository.remover(vagaId);
    }
=======
>>>>>>> 51da9c7e0b3b30c2a46b79eff28599efc8d7c368
}