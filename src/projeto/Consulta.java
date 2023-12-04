package projeto;

public class Consulta {
    private int idConsulta;
    private int idPaciente;
    private int idMedico;
    private String tipo_Consulta;

    public Consulta() {
    }

    public String toString(String paciente, String medico){
        return ("[id="+this.getIdConsulta()+"], [Paciente="+paciente+"], [Médico="+medico+"], [Tipo Consulta="+this.tipo_Consulta+"]");
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

    public String getTipo_Consulta() {
        return tipo_Consulta;
    }

    public void setTipo_Consulta(String tipo_Consulta) {
        this.tipo_Consulta = tipo_Consulta;
    }
}
