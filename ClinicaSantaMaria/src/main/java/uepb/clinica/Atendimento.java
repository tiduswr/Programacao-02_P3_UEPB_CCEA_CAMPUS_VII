package uepb.clinica;

import java.util.Date;

public class Atendimento {
    int codAtend;
    String cpfPaciente, cpfProfSaude;
    Date dataAtendimento, dataPagamento;
    float valorPagamento;
    String descricao;
    
    public Atendimento(int codAtend, String cpfPaciente, String cpfProfSaude, Date dataAtendimento, Date dataPagamento,
                        float valorPagamento, String descricao){
        this.codAtend = codAtend;
        this.cpfPaciente = cpfPaciente;
        this.cpfProfSaude = cpfProfSaude;
        this.dataAtendimento = dataAtendimento;
        this.dataPagamento = dataPagamento;
        this.valorPagamento = valorPagamento;
        this.descricao = descricao;
    }

    public int getCodAtend() {
        return codAtend;
    }
    public void setCodAtend(int codAtend) {
        this.codAtend = codAtend;
    }
    public String getCpfPaciente() {
        return cpfPaciente;
    }
    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }
    public String getCpfProfSaude() {
        return cpfProfSaude;
    }
    public void setCpfProfSaude(String cpfProfSaude) {
        this.cpfProfSaude = cpfProfSaude;
    }
    public Date getDataAtendimento() {
        return dataAtendimento;
    }
    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }
    public Date getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    public float getValorPagamento() {
        return valorPagamento;
    }
    public void setValorPagamento(float valorPagamento) {
        this.valorPagamento = valorPagamento;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
