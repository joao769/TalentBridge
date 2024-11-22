package main.java.com.example.model;

public class Vaga {
	
    private int id;
    private String descricao;
    private String cargo;
    private int empresaId;
    
	public Vaga(int id, String descricao, String cargo, int empresaId) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.cargo = cargo;
		this.empresaId = empresaId;
	}
	
	public Vaga() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public int getEmpresaId() {
		return empresaId;
	}
	public void setEmpresaId(int empresaId) {
		this.empresaId = empresaId;
	}

	@Override
	public String toString() {
		return "Vaga [id=" + id + ", descricao=" + descricao + ", cargo=" + cargo + ", empresaId=" + empresaId + "]";
	}
    
}
