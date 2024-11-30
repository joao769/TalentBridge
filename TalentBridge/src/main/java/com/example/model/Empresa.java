package main.java.com.example.model;

public class Empresa {
    private int id;
    private String nome;
    private String endereco;
    private String cnpj;
    private String telefone;
    private String email;
    private String senha;

    public Empresa(int id, String nome, String cnpj, String endereco , String telefone, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
	}

    public Empresa() {
    	
    }
    
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Empresa{id=" + id + ", nome='" + nome + "', cnpj='" + cnpj + "'}";
	}
}