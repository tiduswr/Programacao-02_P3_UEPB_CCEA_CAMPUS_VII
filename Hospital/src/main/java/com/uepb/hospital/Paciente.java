package com.uepb.hospital;

import java.util.Date;

public class Paciente extends Pessoa implements Comparable<Paciente>{
    
    private Integer prioridade; // 1 - Vermelho, 2 - Amarelo, 3 - Verde, 4 - Azul
    private final int tempoPrioridades[] = {0, 3600, 7200, 14400}; //Tempo conforme o valor do campo prioridade
    private int estado; // 0 - Nada, 1 - Espera, 2 - Atrasado, 3 - Atendido
    private CronometroAtendimento c;
    
    public Paciente(String nome, String cpf, Date dtNasc, char sexo, Endereco endereco, Contato contato,
                    int prioridade){
        super(nome, cpf, dtNasc, sexo, endereco, contato);
        this.prioridade = (Integer) prioridade;
        this.estado = 0;
    }
    public Paciente(){
        this.prioridade = (Integer) 0;
    }
    
    public String getPrioridadeFormatada(){
        switch(this.prioridade){
            case 1 -> {
                return "Vermelho";
            }
            case 2 -> {
                return "Amarelo";
            }
            case 3 -> {
                return "Verde";
            }
            case 4 -> {
                return "Azul";
            }
            default -> {
                return "Outro";
            }
        }
    }
    public String getTempoFormatado(){
        return this.formataSegundos(this.getContador().getTempoDecorrido());
    }
    public void iniciaContador(){
        this.c = new CronometroAtendimento(this.tempoPrioridades[(int) this.getPrioridade()-1], this);
        this.c.start();
    }
    public CronometroAtendimento getContador(){
        return c;
    }
    public String getTempoTotalEspera(){
        return this.formataSegundos(this.tempoPrioridades[(int) this.getPrioridade()-1]);
    }
    public Integer getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Paciente{" + "prioridade=" + prioridade + ", nome=" + this.getNome() + '}';
    }
    
    //Utilizado para ordenação na Priority Queue
    @Override
    public int compareTo(Paciente o) {
        if(this.getPrioridade() < o.getPrioridade()){
            return -1;
        }else if(this.getPrioridade() > o.getPrioridade()){
            return 1;
        }
        return 0;
    }
    
    private String formataSegundos(int arg){
        final int TEMPO = 60;
        int tempoDecorrido = arg;
            
        //Calcula minutos
        float auxMinutos = ((float) tempoDecorrido) / TEMPO;

        //Calcula segundos  
        float segundos = (auxMinutos - (int) auxMinutos) * TEMPO;

        //Calcular Hora
        int hora = (int) auxMinutos / TEMPO;
        int minutos = (int) auxMinutos - (hora * TEMPO);

        return (String) String.valueOf(hora) + "h" + String.valueOf((int) minutos) + "m" + String.valueOf((int) segundos) + "s";
    }
}
