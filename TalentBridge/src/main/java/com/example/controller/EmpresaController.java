package main.java.com.example.controller;

import java.sql.SQLException;
import java.util.List;
import main.java.com.example.model.Empresa;
import main.java.com.example.service.EmpresaService;

public class EmpresaController {

    private EmpresaService empresaService;

    public EmpresaController() {
        this.empresaService = new EmpresaService();
    }

    public void adicionarEmpresa(String nome, String cnpj, String endereco, String telefone, String email, String senha) {
        Empresa empresa = new Empresa(0, nome, cnpj, email, telefone, email, senha); 
        empresaService.adicionarEmpresa(empresa);
    }

    public int fazerLogin(String email, String senha) throws SQLException {
        return empresaService.fazerLogin(email, senha);  
    }

    public void editarEmpresa(int empresaLogadaId, String novoNome, String novoEndereco, String novoTelefone, String novoEmail) {
        empresaService.editarEmpresa(empresaLogadaId,  novoNome, novoEndereco, novoTelefone, novoEmail);
    }
    
    public void deleteEmpresa(int empresaId) {
        empresaService.deleteEmpresa(empresaId);    
        System.out.println("Empresa deletada com sucesso!");
    }
    
    public List<Empresa> listEmpresa() {
        return empresaService.listEmpresa();
    }
    
    public Empresa getEmpresaById(int id) {
        return empresaService.getEmpresaById(id);
    }
}
