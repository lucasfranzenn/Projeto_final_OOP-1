package projeto;

import java.util.ArrayList;

public abstract class Pessoa {
    private int id;
    private String nome;
    private String cadastro;
    private Boolean ativo;
    private ArrayList<Consulta> consultas_marcadas;

    public abstract String toString();


    public ArrayList<Consulta> getConsultas_marcadas() {
        return consultas_marcadas;
    }

    public void setConsultas_marcadas(ArrayList<Consulta> consultas_marcadas) {
        this.consultas_marcadas = consultas_marcadas;
    }



    public Pessoa() {
        consultas_marcadas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCadastro() {
        return cadastro;
    }

    public void setCadastro(String cadastro) {
        this.cadastro = cadastro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
