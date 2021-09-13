package uepb.clinica;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Paciente extends Pessoa {
    private String problemaSaude;
    private IMC imc;
    private Date dataUltimoPagamento;
    private ArrayList<Atendimento> atendimentos;
    
    public Paciente(String nome, String cpf, int idade, char sexo, Endereco endereco, Contato contato,
                    String problemaSaude, IMC imc){
        super(nome, cpf, idade, sexo, endereco, contato);
        this.problemaSaude = problemaSaude;
        this.imc = imc;
        this.atendimentos = new ArrayList();
    }
    public Paciente(){
        this.atendimentos = new ArrayList();
    }
    
    public String getProblemaSaude() {
        return problemaSaude;
    }
    public void setProblemaSaude(String problemaSaude) {
        this.problemaSaude = problemaSaude;
    }
    public Date getDataUltimoPagamento() {
        return dataUltimoPagamento;
    }
    public void setDataUltimoPagamento(Date dataUltimoPagamento) {
        this.dataUltimoPagamento = dataUltimoPagamento;
    }
    public IMC getImc() {
        return imc;
    }
    public void setIMC(float peso, float altura){
        this.imc = new IMC(peso, altura);
    }
    public ArrayList<Atendimento> getAtendimentos() {
        return atendimentos;
    }
    public boolean addAtendimento(Atendimento atendimento){
        return this.atendimentos.add(atendimento);
    }
    public boolean removeAtendimento(int codAtend){
        for(Atendimento e : this.atendimentos){
            if(e.getCodAtend() == codAtend){
                this.atendimentos.remove(e);
                return true;
            }
        }
        return false;
    }
    public Atendimento getAtendimento(int codAtend){
        for(Atendimento e : this.atendimentos){
            if(e.getCodAtend() == codAtend){
                return e;
            }
        }
        return null;
    }
    public Object[][] getAtendimentosObjectArray(){
        if(this.getAtendimentos() != null){
            int count = this.getAtendimentos().size();
            Object[][] aux = null;

            if(count > 0){
                aux = new Object[count][6];
                Atendimento a;
                DateFormat df = new SimpleDateFormat("dd-mm-yyyy");

                for(int i = 0; i < count; i++){
                    a = this.getAtendimentos().get(i);  

                    aux[i][0] = a.getCodAtend();
                    aux[i][1] = a.getCpfProfSaude();
                    aux[i][2] = df.format(a.getDataAtendimento());
                    aux[i][3] = a.getValorPagamento();
                    aux[i][4] = df.format(a.getDataPagamento());
                    aux[i][5] = a.getDescricao();
                }
            }
            return aux;
        }
        return null;
    }
}
