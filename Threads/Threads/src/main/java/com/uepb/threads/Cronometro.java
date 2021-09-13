package com.uepb.threads;

public class Cronometro extends Thread{
    
    private int segundos;
    private int end;
    private int status; // 0 = Iniciado, 1 = Executando, 2 = Morto
    
    public Cronometro(int end){
        if(end >= 1){
            this.segundos = 0;
            this.end = end;
            this.status = 0;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public int getStatus(){
        return status;
    }
    public int getTempoDecorrido(){
        return segundos;
    }
    
    @Override
    public void run(){
        this.status = 1;
        try{
            while(this.segundos < this.end){
                this.segundos++;
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){}
        this.status = 2;
    }
}
