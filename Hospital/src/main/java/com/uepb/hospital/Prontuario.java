package com.uepb.hospital;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.io.Serializable;

public class Prontuario implements Serializable{
    private int codigo;
    private String cpf;
    private String cpfProfSaude;
    private String cpfMedico;
    private LocalDateTime saida, dtClassificacao;
    private Date entrada;
    private int internado; // 0 - Nada, 1 - Alta, 2 - Internado
    private String problemaSaude;
    
    public Prontuario(int cod, String cpf, Date entrada){
        this.entrada = entrada;
        this.dtClassificacao = LocalDateTime.now();
        this.codigo = cod;
    }
    public Prontuario(){
        this.dtClassificacao = LocalDateTime.now();
    }

    public String getCpfMedico() {
        return cpfMedico;
    }
    public void setCpfMedico(String cpfMedico) {
        this.cpfMedico = cpfMedico;
    }
    public String getCpfProfSaude() {
        return cpfProfSaude;
    }
    public void setCpfProfSaude(String cpfProfSaude) {
        this.cpfProfSaude = cpfProfSaude;
    }
    public LocalDateTime getDtClassificacao() {
        return dtClassificacao;
    }
    public void setDtClassificacao(LocalDateTime dtClassificacao) {
        this.dtClassificacao = dtClassificacao;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public int getCodigo(){
        return codigo;
    }
    public Date getEntrada() {
        return entrada;
    }
    public String getEntradaFormatado() {
        if(this.entrada != null){
            SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
            return formato.format(this.entrada);
        }else{
            return "";
        }
    }
    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }
    public LocalDateTime getSaida() {
        return saida;
    }
    public String getSaidaFormatado() {
        DateTimeFormatter fDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        if(this.saida != null){
            return fDateTime.format(this.saida);
        }else{
            return "";
        }
    }
    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }
    public LocalDateTime getdtClassificacao() {
        return dtClassificacao;
    }
    public String getdtClassificacaoFormatado() {
        DateTimeFormatter fDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return fDateTime.format(this.dtClassificacao);
    }
    public void setdtClassificacao(LocalDateTime classificacao) {
        this.dtClassificacao = classificacao;
    }
    public boolean isInternado() {
        if(this.internado == 2){
            return true;
        }else{
            return false;
        }
    }
    public void setInternado(int internado) {
        this.internado = internado;
    }
    public int getInternado(){
        return internado;
    }
    public String getProblemaSaude() {
        return problemaSaude;
    }
    public void setProblemaSaude(String problemaSaude) {
        this.problemaSaude = problemaSaude;
    }
    
}
