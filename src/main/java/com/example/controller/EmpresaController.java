package main.java.com.example.controller;

import java.util.List;

import main.java.com.example.model.Empresa;
import main.java.com.example.model.Vaga;
import main.java.com.example.service.EmpresaService;

public class EmpresaController {

    private EmpresaService empresaService;

    public EmpresaController() {
        this.empresaService = new EmpresaService();
    }
    
    public void addEmpresa(Empresa empresa) {
    	empresaService.addEmpresa(empresa);
    }
    
    public void deleteEmpresa(int empresaId) {
    	empresaService.deleteEmpresa(empresaId);    
    }
    
    public void editarEmpresaById(int id, String novoNome, String novoEndereco, String novoTelefone, String novoEmail) {
    	empresaService.editarEmpresaById(id, novoNome, novoEndereco, novoTelefone, novoEmail);
    }
    
    public List<Empresa> listEmpresa() {
    	return empresaService.listEmpresa();
    }
	
    public Empresa getEmpresaById(int id) {
    	return empresaService.getEmpresaById(id);
    }
}
