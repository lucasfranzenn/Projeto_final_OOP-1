package projeto;

public class Medico extends Pessoa{

    public Medico() {
    }

    @Override
    public String toString() {
        return ("[id="+ this.getId() + "], [Médico], [nome="+ this.getNome() +"], [Consultas realizadas=" + getConsultas_marcadas().size()+"], [Horário cadastro="+this.getCadastro()+"]");
    }
}
