package main.java.com.example.model;

public class Curriculo {
    private int id;
    private String experienciaProfissional;
    private String formacaoAcademica;
    private String habilidades;

    public Curriculo(int id,String experienciaProfissional, String formacaoAcademica, String habilidades) {
    	this.id = id;
        this.experienciaProfissional = experienciaProfissional;
        this.formacaoAcademica = formacaoAcademica;
        this.habilidades = habilidades;
    }
    
    public Curriculo() {
    	
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExperienciaProfissional() {
        return experienciaProfissional;
    }

    public void setExperienciaProfissional(String experienciaProfissional) {
        this.experienciaProfissional = experienciaProfissional;
    }

    public String getFormacaoAcademica() {
        return formacaoAcademica;
    }

    public void setFormacaoAcademica(String formacaoAcademica) {
        this.formacaoAcademica = formacaoAcademica;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    @Override
    public String toString() {
        return "  - Experiência Profissional = " + experienciaProfissional + "\n" +
                "  - Formação Académica = " + formacaoAcademica + "\n" +
                "  - Habilidades = " + habilidades + "\n";
    }
}
