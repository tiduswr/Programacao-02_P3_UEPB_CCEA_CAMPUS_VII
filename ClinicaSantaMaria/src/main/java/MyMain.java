
import uepb.clinica.Servidor;
import uepb.clinica.ui.DialogUI;
import uepb.clinica.ui.MenuActions;

public class MyMain {
    public static void main(String args[]) {
        Servidor org = new Servidor("Clinica Santa Maria");
        MenuActions menu = new MenuActions(org);
        DialogUI ask = new DialogUI(org.getNomeClinica());
        int opt;
                
        while(true){
            int auxOpt = ask.askOptionButtons("Bem vindo ao Prontuario Eletronico de Pacientes da Clinica Santa Maria!!", "??", 
                    new String[] {"Cadastrar/Editar Pessoas", "Buscar Paciente/Profissional de Saude", 
                                    "Listar Paciente/Profissional de Saude", "Registrar Atendimento"});
            switch(auxOpt){
                case 0:
                    opt = ask.askOptionButtons("Você quer Cadastrar qual tipo de Pessoa?", "??", 
                    new String[] {"Paciente", "Profissional de Saúde"});
                    
                    switch(opt){
                        case 0:
                            menu.cadastrarPaciente(null);
                            break;
                        case 1:
                            menu.cadastrarProfSaude(null);
                            break;
                        default:
                            ask.showMsg("Processo abortado!");
                            break;
                    }
                    break;
                case 1:
                    opt = ask.askOptionButtons("Você quer Buscar qual tipo de Pessoa²", "??", 
                    new String[] {"Paciente", "Profissional de Saúde"});
                    
                    switch(opt){
                        case 0:
                            menu.buscarPaciente();
                            break;
                        case 1:
                            menu.buscarProfSaude();
                            break;
                        default:
                            ask.showMsg("Processo abortado!");
                            break;
                    }            
                    break;
                case 2:
                    opt = ask.askOptionButtons("Você quer Listar qual tipo de Pessoa²", "??", 
                    new String[] {"Paciente", "Profissional de Saúde"});
                    
                    switch(opt){
                        case 0:
                            menu.listarPacientes();
                            break;
                        case 1:
                            menu.listarProfSaude();
                            break;
                        default:
                            ask.showMsg("Processo abortado!");
                            break;
                    }     
                    break;
                case 3:
                    menu.registrarAtendimento();
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
