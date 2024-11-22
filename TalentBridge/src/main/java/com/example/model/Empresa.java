package main.java.com.example.model;

public class Empresa {
    private int id;
    private String nome;
    private String endereco;
    private String cnpj;
    private String telefone;
    private String email;

    public Empresa(int id, String nome, String endereco, String cnpj, String telefone, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.email = email;
	}

    public Empresa() {
    	
    }
    
	// Getters e Setters
    public String getNome() {
        return nome;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", cnpj=" + cnpj + ", telefone="
				+ telefone + ", email=" + email + "]";
	}
}