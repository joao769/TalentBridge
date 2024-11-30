package main.java.com.example.service;

import main.java.com.example.model.Empresa;
import main.java.com.example.repository.EmpresaRepository;

import java.sql.SQLException;
import java.util.List;

public class EmpresaService {
    
    private EmpresaRepository empresaRepository;

    public EmpresaService() {
        this.empresaRepository = new EmpresaRepository();
    }
    
    public void adicionarEmpresa(Empresa empresa) {
    	try {
    		empresaRepository.adicionarEmpresa(empresa);
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar empresa no repositório: " + e.getMessage());
        }
    }

    public int fazerLogin(String email, String senha) {
        try {
        	Empresa empresa = empresaRepository.buscarPorEmail(email);
        	if (empresa != null && senha.equals(empresa.getSenha())) { 
        		return empresa.getId();  
        	}
        } catch (SQLException e) {
        	System.err.println("Erro ao fazer login: " + e.getMessage());
        }
        return -1;
    }

    public void editarEmpresa(int empresaLogadaId, String novoNome, String novoEndereco, String novoTelefone, String novoEmail) {
    	try {
    		empresaRepository.atualizarDados(empresaLogadaId, novoNome, novoEndereco, novoTelefone, novoEmail);
    		System.out.println("Empresa atualizada com sucesso!");
    	} catch (SQLException e) {
    		System.err.println("Erro ao atualizar empresa: " + e.getMessage());
    	}
    } 
    
    public void deleteEmpresa(int empresaId) {
        try {
            empresaRepository.deletarEmpresa(empresaId);
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
    
    public Empresa getEmpresaById(int id) {
        try {
            return empresaRepository.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }    
}
