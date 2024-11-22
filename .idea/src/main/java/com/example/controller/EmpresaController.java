package com.example.controller;

import com.example.model.Empresa;
import com.example.repository.EmpresaRepository;

public class EmpresaController {
    private EmpresaRepository repository = new EmpresaRepository();

    public void adicionarEmpresa(Empresa empresa) {
        repository.salvar(empresa);
    }
}
