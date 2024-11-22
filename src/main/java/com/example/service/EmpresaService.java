package main.java.com.example.service;

import main.java.com.example.model.Empresa;
import main.java.com.example.model.Vaga;
import main.java.com.example.repository.EmpresaRepository;

import java.sql.SQLException;
import java.util.List;

public class EmpresaService {
	
    private EmpresaRepository empresaRepository;

    public EmpresaService() {
        this.empresaRepository = new EmpresaRepository();
    }

    public void addEmpresa(Empresa empresa) {
        try {
            empresaRepository.criarEmpresa(empresa);
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    
    public void deleteEmpresa(int empresaId) {
        try {
            empresaRepository.deletarEmpresa(empresaId);
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    
    public void editarEmpresaById(int id, String novoNome, String novoEndereco, String novoTelefone, String novoEmail) {
        try {
            empresaRepository.atualizarEmpresa(id, novoNome, novoEndereco, novoTelefone, novoEmail);
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

    public List<Empresa> listEmpresa() {
        try {
            return empresaRepository.listarEmpresas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Método para buscar um candidato pelo id
    public Empresa getEmpresaById(int id) {
        try {
            return empresaRepository.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }    
}
