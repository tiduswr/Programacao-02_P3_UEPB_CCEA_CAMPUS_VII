package uepb.clinica.ui;

import uepb.clinica.Clinica;
import uepb.clinica.Paciente;
import uepb.clinica.ProfissionalSaude;
import uepb.clinica.Servidor;

public class MenuActions implements Clinica{
    
    private Servidor org;
    private DialogUI ask;
    
    public MenuActions(Servidor org){
        this.org = org;
        ask = new DialogUI(org.getNomeClinica());
    }
    
    @Override
    public void cadastrarPaciente(Paciente paciente) {
        frmCadPaciente cadPaciente = new frmCadPaciente(paciente, this.org);
        cadPaciente.showForm();
        
        if(cadPaciente.getRetorno() == 1){
            //Caso o paciente esteja sendo editado, o método abaixo irá falhar pois não permite duplicatas de mesmo CPF
            if(org.addPaciente(cadPaciente.getPaciente())){
                ask.showMsg("Os dados de " + cadPaciente.getPaciente().getNome() + " foram cadastrados!");
            }else{
                ask.showMsg("Os dados de " + cadPaciente.getPaciente().getNome() + " foram Atualizados!");
            }
        }else if(cadPaciente.getRetorno() == 2){
            org.removePaciente(cadPaciente.getPaciente().getCpf());
        }
        cadPaciente.dispose();
    }

    @Override
    public void cadastrarProfSaude(ProfissionalSaude profissional) {
        frmCadProfSaude cadProfSaude = new frmCadProfSaude(profissional, this.org);
        cadProfSaude.showForm();
        
        if(cadProfSaude.getRetorno() == 1){
            if(org.addProfSaude(cadProfSaude.getProfSaude())){
                ask.showMsg("Os dados de " + cadProfSaude.getProfSaude().getNome() + " foram cadastrados!");
            }else{
                ask.showMsg("Os dados de " + cadProfSaude.getProfSaude().getNome() + " foram Atualizados!");
            }
        }else if(cadProfSaude.getRetorno() == 2){
            org.removeProfSaude(cadProfSaude.getProfSaude().getCpf());
        }
        
        cadProfSaude.dispose();
    }

    @Override
    public void buscarPaciente() {
        Paciente p = org.getPaciente(this.askCPF());
        
        if(p != null){
            this.cadastrarPaciente(p);
        }else{
            ask.showMsg("Paciente não encontrado!");
        }
        
    }

    @Override
    public void buscarProfSaude() {
        ProfissionalSaude p = org.getProfSaude(this.askCPF());
        
        if(p != null){
            this.cadastrarProfSaude(p);
        }else{
            ask.showMsg("Paciente não encontrado!");
        }
    }

    @Override
    public void listarPacientes() {
        frmListarPessoas lista = new frmListarPessoas(org.getObjectArrayOfPacientes(),
                                                        new String[] {"CPF", "NOME", "IDADE", "Qtd.Atendimentos", "Faixa do IMC"}, 
                                                        "Pacientes", "Lista de Pacientes");
        lista.showForm();
        lista.dispose();
    }

    @Override
    public void listarProfSaude() {
        frmListarPessoas lista = new frmListarPessoas(org.getObjectArrayOfProfSaude(),
                                                        new String[] {"CPF", "NOME", "IDADE", "ENDERECO COMPLETO", "CARGO"}, 
                                                        "Profissionais de Saude", "Lista de Profissionais de Saude");
        lista.showForm();
        lista.dispose();
    }

    @Override
    public void registrarAtendimento() {
        Paciente paciente = org.getPaciente(this.askCPF());
        
        if(paciente != null){
            frmRegAtend atend = new frmRegAtend(paciente, this.org);

            atend.showForm();
            atend.dispose();
        }else{
            ask.showMsg("Paciente não encontrado!");
        }
    }
    
    private String askCPF(){
        return ask.askText("Digite o CPF para busca:", "Por favor, não deixei em branco");
    }
    
}
