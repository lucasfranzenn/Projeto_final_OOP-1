package projeto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Paciente extends Pessoa{
    private int idReceita;

    public int getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(int idReceita) {
        this.idReceita = idReceita;
    }

    public Paciente() {
    }

    @Override
    public String toString() {
        return ("[id="+ this.getId() + "], [Paciente], [nome="+ this.getNome() +"], [Consultas realizadas=" + getConsultas_marcadas().size()+"], [Horário cadastro="+this.getCadastro()+"]");
    }
}
