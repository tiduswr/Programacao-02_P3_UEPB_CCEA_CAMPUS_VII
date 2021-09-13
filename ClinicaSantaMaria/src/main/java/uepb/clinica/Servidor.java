package uepb.clinica;

import java.util.ArrayList;

public class Servidor {
    private ArrayList<Paciente> pacientes;
    private ArrayList<ProfissionalSaude> profissionais;
    private String nomeClinica;
    private int codAtendGerados;
    
    public Servidor(String nomeClinica){
        this.nomeClinica = nomeClinica;
        this.pacientes = new ArrayList();
        this.profissionais = new ArrayList();
        this.codAtendGerados = 0;
    }
    
    //Getters e Setters
    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }
    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    public ArrayList<ProfissionalSaude> getProfissionais() {
        return profissionais;
    }
    public void setProfissionais(ArrayList<ProfissionalSaude> profissionais) {
        this.profissionais = profissionais;
    }
    public String getNomeClinica() {
        return nomeClinica;
    }
    public void setNomeClinica(String nomeClinica) {
        this.nomeClinica = nomeClinica;
    }
    public int gerarCodAtend(){
        this.codAtendGerados++;
        return this.codAtendGerados;
    }
    
    //Cadastros de Usuários
    public boolean addPaciente(Paciente paciente){
        if(this.getPaciente(paciente.cpf) == null){
            return this.pacientes.add(paciente);
        }else{
            return false;
        }
    }
    public boolean addProfSaude(ProfissionalSaude profSaude){
        if(this.getProfSaude(profSaude.getCpf()) == null){
            return this.profissionais.add(profSaude);
        }else{
            return false;
        }
    }
    
    //Remoção de Usuários
    public boolean removePaciente(String cpf){
        for(Paciente p : this.pacientes){
            if(p.getCpf().equalsIgnoreCase(cpf)){
                return this.pacientes.remove(p);
            }
        }
        return false;
    }
    public boolean removeProfSaude(String cpf){
        for(ProfissionalSaude p : this.profissionais){
           if(p.getCpf().equalsIgnoreCase(cpf)){
               return this.profissionais.remove(p);
           }
        }
        return false;
    }
    
    //Busca de Usuário
    public Paciente getPaciente(String cpf){
        for(Paciente p : this.pacientes){
            if(p.getCpf().equalsIgnoreCase(cpf)){
                return p;
            }
        }
        return null;
    }
    public ProfissionalSaude getProfSaude(String cpf){
        for(ProfissionalSaude p : this.profissionais){
            if(p.getCpf().equalsIgnoreCase(cpf)){
                return p;
            }
        }
        return null;
    }
    
    //Object Arrays para Interface grafica
    public Object[][] getObjectArrayOfPacientes(){
        int count = this.getPacientes().size();
        
        if(count != 0){
            Object[][] aux = new Object[count][5];

            for(int i = 0; i < count; i++){
                aux[i][0] = this.getPacientes().get(i).getCpf();
                aux[i][1] = this.getPacientes().get(i).getNome();
                aux[i][2] = this.getPacientes().get(i).getIdade();
                aux[i][3] = this.getPacientes().get(i).getAtendimentos().size();
                aux[i][4] = this.getPacientes().get(i).getImc().getFaixaIMC();
            }

            return aux;
        }else{
            return null;
        }
    }
    public Object[][] getObjectArrayOfProfSaude(){
        int count = this.getProfissionais().size();
        
        if(count != 0){
            Object[][] aux = new Object[count][5];

            for(int i = 0; i < count; i++){
                aux[i][0] = this.getProfissionais().get(i).getCpf();
                aux[i][1] = this.getProfissionais().get(i).getNome();
                aux[i][2] = this.getProfissionais().get(i).getIdade();
                aux[i][3] = this.getProfissionais().get(i).getEndereco().getEnderecoCompleto();
                aux[i][4] = this.getProfissionais().get(i).getTipoProfissional();
            }

            return aux;
        }else{
            return null;
        }
    }
}
