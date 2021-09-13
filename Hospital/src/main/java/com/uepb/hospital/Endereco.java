package com.uepb.hospital;

import java.io.Serializable;

public class Endereco implements Serializable{
    private String rua, bairro, cidade, estado;
    private int numero;
    
    public Endereco(){
        
    }
    public Endereco(String rua, int numero, String bairro, String cidade, String estado){
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getEnderecoCompleto(){
        return this.getRua() + ", " + String.valueOf(this.getNumero()) + ", " + 
                this.getBairro() + ", " + this.getCidade() + ", " + this.getEstado();
    }
    
}
