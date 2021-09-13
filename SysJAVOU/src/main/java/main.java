
import uepb.sysjavou.Banco;
import uepb.sysjavou.Cliente;
import uepb.sysjavou.ui.UI;
import uepb.sysjavou.ui.frmCadastro;
import uepb.sysjavou.ui.frmContas;
import uepb.sysjavou.ui.frmListarTransacoes;

public class main {

    public static void main(String args[]) {
        Banco org = new Banco("SYSJAVOU");
        Cliente cliente, cliente2;
        frmContas viewClientes;
        frmListarTransacoes viewTransacoes;
        int auxOpt;
        UI ask = new UI("SYSJAVOU v1.0");
        frmCadastro cadastro;
        String auxStr;
        int agencia, agencia2;
        int conta, conta2;
        double value;
        
        while(true){
            auxOpt = ask.askOptionButtons("Bem vindo ao sistema do banco SYSJAVOU!", "??", 
                    new String[] {"Cadastrar/Editar Cliente", "Mostrar Cliente/Clientes", "Mostrar Transações",
                                    "Sacar", "Depositar", "Transferir"});
            switch(auxOpt){
                case 0:
                    cadastro = new frmCadastro(org);
                    
                    cliente = org.getCliente(ask.askText("Digite o nome do Cliente, caso ele não exista, será solicitado um cadastro!", 
                                            "Não deixe esse campo em branco!"));
                    cadastro.showForm(cliente);
                    switch(cadastro.getAction()){
                        case 0:
                            org.removeCliente(cadastro.getCliente());
                            break;
                        case 1:
                            //Atualizado no objeto cadastro
                            break;
                        case 2:
                            org.addCliente(cadastro.getCliente());
                            break;
                    }
                    cliente = null;
                    cadastro = null;
                    break;
                case 1:
                    auxStr = ask.askText("Digite o nome do Cliente, caso queira ver toda a lista, digite *", 
                                            "Não deixe esse campo em branco!");
                    cliente = org.getCliente(auxStr);
                    viewClientes = new frmContas(cliente, org.getClientesObjectList(auxStr), new String[] {"CPF", "NOME", "AGENCIA", "CONTA", "SALDO"});
                    
                    cliente = null;
                    viewClientes = null;
                    break;
                case 2:
                    if(ask.askOptionButtons("Você quer filtrar por uma conta expecifica?", 
                            "Escolha uma opção valida!", new String[]{"Sim", "Não"}) == 0){
                        agencia = Integer.parseInt(ask.askTextOnlyNumbers("Digite o numero da Agencia!", "Digite apenas numeros!"));
                        conta = Integer.parseInt(ask.askTextOnlyNumbers("Digite o numero da Conta!", "Digite apenas numeros!"));
                        cliente = org.getClientebyConta(agencia, conta);
                    }else{
                        agencia = 0;
                        conta = 0;
                        cliente = null;
                    }
                    
                    if(cliente == null){
                        cliente = new Cliente();
                        cliente.setNome("*");
                        cliente.setCpf("*");
                    }
                    
                    viewTransacoes = new frmListarTransacoes(org.getTransacoesObjectList(agencia, conta),
                                            new String[] {"Agencia", "Conta", "Tipo Movimento", "Valor movimentado", "Data e Hora"},
                                            agencia, conta, cliente.getNome(), cliente.getCpf());
                    
                    cliente = null;
                    viewTransacoes = null;
                    break;
                case 3:
                    agencia = Integer.parseInt(ask.askTextOnlyNumbers("Digite o numero da Agencia!", "Digite apenas numeros!"));
                    conta = Integer.parseInt(ask.askTextOnlyNumbers("Digite o numero da Conta!", "Digite apenas numeros!"));
                    
                    cliente = org.getClientebyConta(agencia, conta);
                    if(cliente != null){
                        value = ask.askFloatValue("Quanto você deseja sacar da conta de " + cliente.getNome() + "?", "Por favor digite um valor!");
                        if(org.sacar(agencia, conta, value)){
                            ask.showMsg("O valor foi sacado!");
                        }else{
                            ask.showMsg("Não foi possivel sacar o valor, por favor, verifique se você tem saldo suficiente e tente novamente!");
                        }
                    
                    }else{
                        ask.showMsg("Uma conta para a agencia e numero digitada não foi enconrada!");
                    }
                        
                    cliente = null;
                    viewClientes = null;
                    break;
                case 4:
                    agencia = Integer.parseInt(ask.askTextOnlyNumbers("Digite o numero da Agencia!", "Digite apenas numeros!"));
                    conta = Integer.parseInt(ask.askTextOnlyNumbers("Digite o numero da Conta!", "Digite apenas numeros!"));
                    
                    cliente = org.getClientebyConta(agencia, conta);
                    if(cliente != null){
                        value = ask.askFloatValue("Quanto você deseja depositar na conta de " + cliente.getNome() + "?", "Por favor digite um valor!");
                        if(org.depositar(agencia, conta, value)){
                            ask.showMsg("O valor foi depositado!");
                        }else{
                            ask.showMsg("Não foi possivel depositar o valor, por favor, verifique se você tem saldo suficiente e tente novamente!");
                        }
                    
                    }else{
                        ask.showMsg("Uma conta para a agencia e numero digitada não foi enconrada!");
                    }
                        
                    cliente = null;
                    viewClientes = null;
                    break;
                case 5:
                    ask.showMsg("Primeiro digite a conta para a qual você quer retirar o valor!");
                    agencia = Integer.parseInt(ask.askTextOnlyNumbers("Digite o numero da Agencia!", "Digite apenas numeros!"));
                    conta = Integer.parseInt(ask.askTextOnlyNumbers("Digite o numero da Conta!", "Digite apenas numeros!"));
                    cliente = org.getClientebyConta(agencia, conta);
                    if(cliente != null){
                        ask.showMsg("Agora digite a conta para a qual você quer enviar o valor!");
                        agencia2 = Integer.parseInt(ask.askTextOnlyNumbers("Digite o numero da Agencia!", "Digite apenas numeros!"));
                        conta2 = Integer.parseInt(ask.askTextOnlyNumbers("Digite o numero da Conta!", "Digite apenas numeros!"));
                        cliente2 = org.getClientebyConta(agencia2, conta2);
                        if(cliente2 != null){
                            value = ask.askFloatValue("Por favor agora digite o vaor da transferenca!", "Digite um valor válido!");
                            if(org.transferir(agencia, conta, agencia2, conta2, value)){
                                ask.showMsg("A transferência de " + cliente.getNome() + " foi feita para " + cliente2.getNome() + " !");
                            }else{
                                ask.showMsg("A transferência de não foi realizada, verifique se você possui saldo suficiente");
                            }
                        }
                    }else{
                        ask.showMsg("A conta digitada não foi encontrada!");
                    }
                    break;
                case -1:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
        
    }
}
