package uepb.sysjavou;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conta {
    private int agencia;
    private int numeroConta;
    private double saldo;
    
    public Conta(){
        
    }
    public Conta(int agencia, int numeroConta){
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.saldo = 0;
    }
    
    //Metodos getters and setters
    public int getAgencia() {
        return agencia;
    }
    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }
    public int getNumeroConta() {
        return numeroConta;
    }
    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    //Métodos principais da clase
    private String now(){
        DateFormat dF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date data = new Date();
        return dF.format(data);
    }
    public boolean depositar(double valor){
        try{
            this.saldo = Double.sum(this.saldo, valor);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public boolean sacar(double valor){
        if(valor <= this.getSaldo()){
            this.saldo = Double.sum(this.saldo, valor*(-1));
            return true;
        }else{
            return false;
        }
    }
}
