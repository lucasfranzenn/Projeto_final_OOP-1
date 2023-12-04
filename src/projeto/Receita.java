package projeto;

public class Receita {
    private int id;
    private int idConsulta;
    private int idPaciente;
    private int idMedico;
    private String remedios;

    public Receita(int id, int idConsulta, int idPaciente, int idMedico, String remedios) {
        this.id = id;
        this.idConsulta = idConsulta;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.remedios = remedios;
    }

    public Receita() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getRemedios() {
        return remedios;
    }

    public void setRemedios(String remedios) {
        this.remedios = remedios;
    }
}
