package main.java.com.example.model;

import main.java.com.example.model.Vaga;

public class Candidato {
    private int id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    private String curriculo;
    private String experienciaProfissional;

	public Candidato(int id, String nome, String cpf, String endereco, String telefone, String email, String curriculo,
			String experienciaProfissional) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.curriculo = curriculo;
		this.experienciaProfissional = experienciaProfissional;
	}

	public Candidato() {
		
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

    public String getExperienciaProfissional() {
        return experienciaProfissional;
    }

    public void setExperienciaProfissional(String experienciaProfissional) {
        this.experienciaProfissional = experienciaProfissional;
    }
    
	@Override
	public String toString() {
		return "\nid = " + id + ", nome = " + nome + ", CPF = " + cpf + ", endereço = " + endereco + ", telefone = "
				+ telefone + ", email = " + email + ", currículo = " + curriculo + ", Experiência Profissional = "
				+ experienciaProfissional;
	}
}
