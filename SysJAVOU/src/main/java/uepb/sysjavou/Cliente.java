package uepb.sysjavou;

import java.util.ArrayList;

public class Cliente extends Pessoa{
    private ArrayList<Conta> contas;
    
    public Cliente(){
        contas = new ArrayList();
    }
    public Cliente(String nome, String cpf, int idade, char sexo, Endereco endereco, Contato contato){
        super(nome, cpf, idade, sexo, endereco, contato);
    }

    public void setContas(ArrayList<Conta> contas) {
        this.contas = contas;
    }
    
    public ArrayList<Conta> getContas(){
        return this.contas;
    }
    public boolean addConta(Conta conta){
        try{
           contas.add(conta); 
           return true;
        }catch(Exception e){
            return false;
        }
        
    }
    public boolean removeConta(Conta conta){
        for(Conta c : contas){
            if(c.getAgencia() == conta.getAgencia() && 
                    c.getNumeroConta() == conta.getNumeroConta()){
                
                contas.remove(c);
                return true;
            }
        }
        return false;
    }
    public Conta getConta(int agencia, int conta){
        for(Conta c : contas){
            if(c.getAgencia() == agencia && c.getNumeroConta() == conta){
                return c;
            }
        }
        return (Conta) null;
    }
}
