package main.java.com.example.controller;

import java.sql.SQLException;

import main.java.com.example.model.Empresa;
import main.java.com.example.model.Vaga;
import main.java.com.example.repository.VagaRepository;
import main.java.com.example.service.VagaService;

public class VagaController {

    private VagaService vagaService;

    public VagaController() {
        this.vagaService = new VagaService();
    }
	
    public void adicionarVaga(Vaga vaga, int empresa_id) {
    	vagaService.adicionarVaga(vaga, empresa_id);   
    }
    
    public void removerVaga(int vagaId) {
    	vagaService.removerVaga(vagaId); 
    }
    
}
