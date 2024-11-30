package main.java.com.example.model;

public class Vaga {
	
    private int id;
    private String titulo;
    private String descricao;
    private String cargo;
    private String cargaHoraria;
    private Double salario;
    private String status;
    private int empresaId;
    private String nomeEmpresa; 

	public Vaga(int id, int empresaId, String titulo, String descricao, String cargo, String cargaHoraria, Double salario,
			String status) {
		this.id = id;
		this.empresaId = empresaId;
		this.titulo = titulo;
		this.descricao = descricao;
		this.cargo = cargo;
		this.cargaHoraria = cargaHoraria;
        setSalario(salario);  // Usando o setter para validação
        setStatus(status);    // Usando o setter para validação
	}

	public Vaga() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public String getCargaHoraria() {
		return cargaHoraria;
	}

    public void setCargaHoraria(String cargaHoraria) {

        this.cargaHoraria = cargaHoraria;
    }

	public Double getSalario() {
		return salario;
	}

    public void setSalario(Double salario) {
        if (salario == null || salario <= 0) {
            throw new IllegalArgumentException("O salário deve ser um valor positivo.");
        }
        this.salario = salario;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
	    if (!status.equalsIgnoreCase("disponivel") && 
	        !status.equalsIgnoreCase("preenchida") && 
	        !status.equalsIgnoreCase("em andamento")) {
	        throw new IllegalArgumentException("Status inválido. Valores válidos são: 'disponível', 'fechada' ou 'em andamento'.");
	    }
	    this.status = status;
	}


	public int getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(int empresaId) {
		this.empresaId = empresaId;
	}
	
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }
    
	@Override
	public String toString() {
	    return "Vaga{" +
	            "id=" + id +
	            ", titulo='" + titulo + '\'' +
	            ", descricao='" + descricao + '\'' +
	            ", cargo='" + cargo + '\'' +
	            ", cargaHoraria='" + cargaHoraria + '\'' +
	            ", salario=" + salario +
	            ", status='" + status + '\'' +
	            ", empresaId=" + empresaId +
	            ", nomeEmpresa='" + nomeEmpresa + '\'' +
	            '}';
	}
}
