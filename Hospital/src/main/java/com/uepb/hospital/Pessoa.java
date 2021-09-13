package com.uepb.hospital;

import java.io.Serializable;
import java.util.Date;

public abstract class Pessoa implements Serializable{
    protected String nome, cpf;
    protected Date dtNasc;
    protected char sexo;
    protected Endereco endereco;
    protected Contato contato;
    
    public Pessoa(){
        
    }
    public Pessoa(String nome, String cpf, Date dtNasc, char sexo, Endereco endereco, Contato contato){
        this.nome = nome;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.sexo = sexo;
        this.endereco = endereco;
        this.contato = contato;
    }

    public Date getDtNasc() {
        return dtNasc;
    }
    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public char getSexo() {
        return sexo;
    }
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public Contato getContato() {
        return contato;
    }
    public void setContato(Contato contato) {
        this.contato = contato;
    }
    
    
}
