package main.java.com.example.model;

public class Candidato {
    private int id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    private String senha;
    private Curriculo curriculo; 

	public Candidato(int id, String nome, String cpf, String endereco, String telefone, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }
	
	@Override
	public String toString() {
		return "\nid = " + id + ", nome = " + nome + ", CPF = " + cpf + ", endereço = " + endereco + ", telefone = "
				+ telefone + ", email = " + email;
	}
}
