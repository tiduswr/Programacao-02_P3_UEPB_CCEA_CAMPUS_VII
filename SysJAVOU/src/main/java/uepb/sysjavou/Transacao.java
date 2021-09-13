package uepb.sysjavou;

public class Transacao {
    private int agencia;
    private int conta;
    private String tipo;
    private double valMovimentado;
    private String horario;

    public Transacao(int agencia, int conta, String tipo, double valMovimentado, String horario) {
        this.agencia = agencia;
        this.conta = conta;
        this.tipo = tipo;
        this.valMovimentado = valMovimentado;
        this.horario = horario;
    }
    
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public double getValMovimentado() {
        return valMovimentado;
    }
    public void setValMovimentado(double valMovimentado) {
        this.valMovimentado = valMovimentado;
    }
    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
    public int getAgencia() {
        return agencia;
    }
    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }
    public int getConta() {
        return conta;
    }
    public void setConta(int conta) {
        this.conta = conta;
    }
}
