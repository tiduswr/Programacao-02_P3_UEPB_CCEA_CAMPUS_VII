package com.uepb.hospital;

import java.io.Serializable;

public class CronometroAtendimento extends Thread implements Serializable{
    
    Paciente p;
    private int segundos;
    private int end;
    
    public CronometroAtendimento(int end, Paciente p){
        if(end >= 0 && p != null){
            this.segundos = 0;
            this.end = end;
            this.p = p;
            p.setEstado(0);
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public int getTempoDecorrido(){
        return segundos;
    }
    
    @Override
    public void run(){
        p.setEstado(1);
        try{
            while(this.segundos <= this.end){
                this.segundos++;
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){}
        p.setEstado(2);
    }
    
    public void kill(){
        this.interrupt();
    }
}
