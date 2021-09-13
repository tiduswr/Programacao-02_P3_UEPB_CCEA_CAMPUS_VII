package uepb.sysjavou;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Banco {
    private String nomeBanco;
    private ArrayList<Cliente> clientes;
    private ArrayList<Transacao> transacoes;
    
    public Banco(String nome){
        clientes = new ArrayList();
        transacoes = new ArrayList();
        this.nomeBanco = nome;
    }
    
    //metodos getters and setters
    public String getNome(){
        return this.nomeBanco;
    }
    public Cliente getCliente(String nome){
        for(Cliente c : this.clientes){
            if(c.getNome().equals(nome)){
                return c;
            }
        }
        return null;
    }
    public Cliente getClientebyCPF(String cpf){
        for(Cliente c : this.clientes){
            if(c.getCpf().equals(cpf)){
                return c;
            }
        }
        return null;
    }
    public void addCliente(Cliente c){
        clientes.add(c);
    }
    public void removeCliente(Cliente c){
        clientes.remove(c);
    }
    public Cliente getClientebyConta(int agencia, int conta){
        for(Cliente c : this.clientes){
            if(c.getConta(agencia, conta) != null){
                return c;
            }
        }
        return null;
    }
    public void setNome(String nomeBanco){
        this.nomeBanco = nomeBanco;
    }
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    public ArrayList<Transacao> getTransacoes() {
        return transacoes;
    }
    public int getTransacoesLenght(int agencia, int conta){
        if(agencia == 0 && conta == 0){
            return (int) this.transacoes.size();
        }else{
            int i = 0;
            for(Transacao t : this.transacoes){
                if(t.getAgencia() == agencia && t.getConta() == conta){
                    i++;
                }
            }
            return i;
        }
    }
    public Object[][] getTransacoesObjectList(int agencia, int conta){
        Object objList[][] = new Object[this.getTransacoesLenght(agencia, conta)][5];
        int i = 0;
        
        if(agencia == 0 && conta == 0){
            for(Transacao t : transacoes){
                objList[i][0] = (int) t.getAgencia();
                objList[i][1] = (int) t.getConta();
                objList[i][2] = (String) t.getTipo();
                objList[i][3] = (double) t.getValMovimentado();
                objList[i][4] = (String) t.getHorario();
                i++;
            }
        }else{
            for(Transacao t : transacoes){
                if(t.getAgencia() == agencia && t.getConta() == conta){
                    objList[i][0] = (int) t.getAgencia();
                    objList[i][1] = (int) t.getConta();
                    objList[i][2] = (String) t.getTipo();
                    objList[i][3] = (double) t.getValMovimentado();
                    objList[i][4] = (String) t.getHorario();
                    i++; 
                }
            }
        }
        
        return objList;
    }
    public int getClientesLenght(String nome){
        int i = 0;
        
        for(Cliente c : this.clientes){
            if(nome.equals("*")){
                for(Conta cc : c.getContas()){
                    i++;
                }
            }else{
                if(c.getNome().equals(nome)){
                    for(Conta cc : c.getContas()){
                        i++;
                    }
                }
            }
        }
        return i;
    }
    public Object[][] getClientesObjectList(String nome){
        Object objList[][] = new Object[this.getClientesLenght(nome)][5];
        int i = 0;
        
        for(Cliente c : this.clientes){
            if(nome.equals("*")){
                for(Conta cc : c.getContas()){
                    objList[i][0] = (String) c.getCpf();
                    objList[i][1] = (String) c.getNome();
                    objList[i][2] = (int) cc.getAgencia();
                    objList[i][3] = (int) cc.getNumeroConta();
                    objList[i][4] = (double) cc.getSaldo();
                    i++;
                }
            }else{
                if(c.getNome().equals(nome)){
                    for(Conta cc : c.getContas()){
                        objList[i][0] = (String) c.getCpf();
                        objList[i][1] = (String) c.getNome();
                        objList[i][2] = (int) cc.getAgencia();
                        objList[i][3] = (int) cc.getNumeroConta();
                        objList[i][4] = (double) cc.getSaldo();
                        i++;
                    }
                }
            }
        }
        return objList;
    }
    //metodos principais
    private Conta getConta(int agencia, int conta){
        for(Cliente c : this.clientes){
            for(Conta cc : c.getContas()){
                if(cc.getAgencia() == agencia && cc.getNumeroConta() == conta){
                    return cc;
                }
            }
        }
        return null;
    }
    private String now(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return df.format(date);
    }
    private void addTransacao(int agencia, int conta, String tipoMov, double valorMov){
        transacoes.add(new Transacao(agencia, conta, tipoMov, valorMov, this.now()));
    }
    public boolean depositar(int agencia, int conta, double valor){
        Conta auxConta = getConta(agencia, conta);
        boolean state;
        
        if(auxConta != (Conta) null){
            state = auxConta.depositar(valor);
            if(state){
                this.addTransacao(agencia, conta, "Depósito", valor);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    public boolean sacar(int agencia, int conta, double valor){
        Conta auxConta = getConta(agencia, conta);
        boolean state;
        
        if(auxConta != (Conta) null){
            state = auxConta.sacar(valor);
            if(state){
                this.addTransacao(agencia, conta, "Saque", valor);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    public boolean transferir(int fromAgencia, int fromConta,
                                int toAgencia, int toConta, double valor){
        
        Conta fromCC = this.getConta(fromAgencia, fromConta);
        Conta toCC = this.getConta(toAgencia, toConta);
        
        if(fromCC != (Conta) null && toCC != (Conta) null){
            if(!fromCC.sacar(valor)){
                return false;
            }
            if(!toCC.depositar(valor)){
                fromCC.depositar(valor);
                return false;
            }
            
            this.addTransacao(fromAgencia, fromConta, "Saque-Transferência", valor);
            this.addTransacao(toAgencia, toConta, "Depósito-Transferência", valor);
            return true;
        }
        return false;
    }
}
