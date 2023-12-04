package projeto;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Pessoa> pessoas = new ArrayList<>();
    public static ArrayList<Consulta> consultas = new ArrayList<>();
    public static ArrayList<Receita> receitas = new ArrayList<>();

    public static void main(String[] args) {
        cmdMenu();
    }

    private static void cmdMenu() {

        String opcao = JOptionPane.showInputDialog(null, "Olá, qual operação deseja realizar?\n" +
                "1 - Cadastrar um novo paciente\n" +
                "2 - Cadastrar um novo médico\n" +
                "3 - Agendar uma consulta\n" +
                "4 - Consultar pessoas cadastradas\n" +
                "5 - Visualizar consultas realizadas\n" +
                "6 - Excluir um cadastro\n", "..:: MENU ::.. ", JOptionPane.INFORMATION_MESSAGE);

        if(opcao!=null){
            cmdOperacao(opcao);
        }
    }

    private static void cmdOperacao(String opcao) {
        switch (opcao){
            case "1":
                cmdCadastroPaciente();
                break;
            case "2":
                cmdCadastroMedico();
                break;
            case "3":
                cmdAgendaConsultaPaciente();
                break;
            case "4":
                cmdConsulta(pessoas);;
                break;
            case "5":
                cmdConsultasRealizadas();
                break;
            case "6":
                cmdExcluirPessoa(pessoas);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente", "..::ERRO::..", JOptionPane.ERROR_MESSAGE);
                cmdMenu();
        }
    }

    private static void cmdConsultasRealizadas() {
        String nome_Medico="";
        String nome_Paciente="";
        String remedio="";
        StringBuilder consultasrealizadas = new StringBuilder();
        try{
            for(Consulta c: consultas){
                for(Pessoa p: pessoas){
                    if(c.getIdPaciente() == p.getId()){
                        nome_Paciente = p.getNome();
                        for(Receita r: receitas){
                            if(((Paciente) p).getIdReceita() == r.getId()){
                                remedio = ", [Remedio="+r.getRemedios()+"]";
                                break;
                            }
                        }
                        break;
                    }
                }
                for(Pessoa p: pessoas){
                    if(c.getIdMedico() == p.getId()){
                        nome_Medico = p.getNome();
                        consultasrealizadas.append(c.toString(nome_Paciente, nome_Medico)).append(remedio+"\n");
                        break;
                    }
                }
            }

            JOptionPane.showMessageDialog(null, consultasrealizadas.toString());
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        } finally {
            cmdMenu();
        }
    }

    private static void cmdAgendaConsultaPaciente() {
        String paciente = JOptionPane.showInputDialog(null, "Qual o nome do paciente que irá realizar a consulta?", "..::CADASTRO::..", JOptionPane.QUESTION_MESSAGE);

        for(Pessoa p: pessoas){
            if(p.getNome().equals(paciente)){
                cmdAgendaConsultaMedico(pessoas.indexOf(p));
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Paciente não encontrado!", "..::ERRO::..", JOptionPane.ERROR_MESSAGE);
        cmdMenu();
    }

    private static void cmdAgendaConsultaMedico(int indexPaciente) {
        String medico = JOptionPane.showInputDialog(null, "Qual o nome do médico que irá realizar a consulta?", "..::CADASTRO::..", JOptionPane.QUESTION_MESSAGE);

        for(Pessoa p: pessoas){
            if(p.getNome().equals(medico)){
                cmdAgendaConsulta(indexPaciente, pessoas.indexOf(p));
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Médico não encontrado!", "..::ERRO::..", JOptionPane.ERROR_MESSAGE);
        cmdMenu();

    }

    private static void cmdAgendaConsulta(int indexPaciente, int indexMedico) {
        Consulta c = new Consulta();
        Receita r = new Receita();

        c.setIdConsulta(consultas.size()+1);
        c.setIdPaciente(pessoas.get(indexPaciente).getId());
        c.setIdMedico(pessoas.get(indexMedico).getId());

        r.setId(receitas.size()+1);
        r.setIdConsulta(c.getIdConsulta());
        r.setIdPaciente(pessoas.get(indexPaciente).getId());
        r.setIdMedico(pessoas.get(indexMedico).getId());

        pessoas.get(indexPaciente).getConsultas_marcadas().add(c);
        pessoas.get(indexMedico).getConsultas_marcadas().add(c);

        c.setTipo_Consulta(JOptionPane.showInputDialog(null, "Qual o tipo da consulta", "..::CADASTRO::..", JOptionPane.QUESTION_MESSAGE));

        r.setRemedios(JOptionPane.showInputDialog(null, "Qual foi o remédio passado pelo médico", "..::CADASTRO::..", JOptionPane.QUESTION_MESSAGE));

        ((Paciente) pessoas.get(indexPaciente)).setIdReceita(r.getId());

        receitas.add(r);
        consultas.add(c);

        JOptionPane.showMessageDialog(null, "Consulta registrada!", "..::SUCESSO::..", JOptionPane.INFORMATION_MESSAGE);

        cmdMenu();

    }

    private static void cmdExcluirPessoa(ArrayList<Pessoa> pessoas) {
        try {
            String p_excluir = JOptionPane.showInputDialog(null, "Qual deseja excluir:\n1 - Paciente\n2 - Médico", "..::MENU::..", JOptionPane.QUESTION_MESSAGE);

            switch (p_excluir){
                case "1":
                    cmdExcluir(pessoas, "Paciente");
                    break;
                case "2":
                    cmdExcluir(pessoas, "Médico");
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente", "..::ERRO::..", JOptionPane.ERROR_MESSAGE);
            }

        } finally {
            cmdMenu();
        }
    }

    private static void cmdExcluir(ArrayList<Pessoa> pessoas, String tipo) {
        String nome  = JOptionPane.showInputDialog(null, "Digite o nome da pessoa", "..::EXCLUIR::..", JOptionPane.OK_OPTION);
        boolean excluido = false;

        if(tipo.equals("Médico")){
            for (Pessoa p: pessoas){
                if(p instanceof Medico && p.getNome().equals(nome) && p.getAtivo()){
                    //pessoas.remove(pessoas.indexOf(p));
                    p.setAtivo(false);

                    JOptionPane.showMessageDialog(null, "Médico \""+nome+"\" excluido!", "..::SUCESSO::..", JOptionPane.INFORMATION_MESSAGE);
                    excluido = true;
                    break;
                }
            }
        } else{
            for (Pessoa p: pessoas) {
                if (p instanceof Paciente && p.getNome().equals(nome) && p.getAtivo()) {
                    //pessoas.remove(pessoas.indexOf(p));
                    p.setAtivo(false);

                    JOptionPane.showMessageDialog(null, "Paciente \"" + nome + "\" excluido!", "..::SUCESSO::..", JOptionPane.INFORMATION_MESSAGE);
                    excluido = true;
                    break;
                }
            }
        }

        if(!excluido){
            JOptionPane.showMessageDialog(null, tipo+" \""+nome+"\" não encontrado!", "..::AVISO::..", JOptionPane.WARNING_MESSAGE);
            cmdExcluirPessoa(pessoas);
        }
    }

    private static void cmdCadastroMedico() {
        try{
            Medico m = new Medico();

            m.setId(pessoas.size()+1);
            m.setAtivo(true);
            m.setCadastro(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

            m.setNome(JOptionPane.showInputDialog(null, "Insira o nome do médico: ", "..::CADASTRO::..", JOptionPane.QUESTION_MESSAGE));

            JOptionPane.showMessageDialog(null, "Médico \""+ m.getNome() +"\" cadastrado!", "..::SUCESSO::..", JOptionPane.INFORMATION_MESSAGE);
            pessoas.add(m);

        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Houve um erro: " + ex.getMessage());
        } finally {
            cmdMenu();
        }
    }

    private static void cmdConsulta(ArrayList<Pessoa> pessoas) {
        try {
            StringBuilder consulta = new StringBuilder();
            for (Pessoa p : pessoas) {
                if (p instanceof Paciente && p.getAtivo()) {
                    Paciente pa = (Paciente) p;

                    consulta.append(pa.toString());
                } else if (p instanceof Medico && p.getAtivo()) {
                    Medico m = (Medico) p;

                    consulta.append(m.toString());
                }
                consulta.append("\n");
            }

            JOptionPane.showMessageDialog(null, consulta.toString(), "..::CONSULTA::..", JOptionPane.INFORMATION_MESSAGE);
        }finally {
            cmdMenu();
        }
    }

    private static void cmdCadastroPaciente() {
        try{
            Paciente p = new Paciente();

            p.setId(pessoas.size()+1);
            p.setAtivo(true);
            p.setCadastro(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

            p.setNome(JOptionPane.showInputDialog(null, "Insira o nome do paciente: ", "..::CADASTRO::..", JOptionPane.QUESTION_MESSAGE));

            if(p.getNome().length()>30){
                throw new Exception("Nome grande demais zé de manga!!");
            }

            JOptionPane.showMessageDialog(null, "Paciente \""+ p.getNome() +"\" cadastrado!", "..::SUCESSO::..", JOptionPane.INFORMATION_MESSAGE);
            pessoas.add(p);

        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Houve um erro: " + ex.getMessage());
        } finally {
            cmdMenu();
        }

    }
}