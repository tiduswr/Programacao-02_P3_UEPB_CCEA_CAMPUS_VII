import uepb.zologico.DataBase;
import uepb.zologico.EntradaDados;
import uepb.zologico.SaidaDados;
import uepb.zologico.ui.DialogUI;

public class Principal {
    
    public static void main(String []args){
        
        DataBase db = new DataBase();
        DialogUI ask = new DialogUI("Cadastro de Galinhas - ZOOLOGICO JAVA");
        EntradaDados input = new EntradaDados();
        SaidaDados output = new SaidaDados();
        db.readData();
        
        while(true){
            int opt = ask.askOptionButtons("Bem vindo ao galinheiro do Zologico Java!\n\nSelecione uma opção para Continuar!", 
                    "Selecione uma opção Correta!", new String[] {"Cadastrar Galinhas", "Excluir Galinhas", "Listar Galinhas", 
                        "Detalhar Galinha","Sair"});
        
            switch(opt){
                case 0 -> {
                    int continuar = 0;
                    
                    while(continuar == 0){
                        input.pedeDadosGalinhas(db);
                        continuar = ask.askOptionButtons("Deseja cadastrar outra galinha?", "Selecione uma opção valida!",
                                new String[] {"Sim", "Não"});
                    }
                    db.saveData();
                }
                case 1 -> {
                    input.excluiGalinha(db);
                    db.saveData();
                }
                case 2 -> output.mostrarGalinhas(db);
                case 3 -> output.detalharGalinha(db);
                case 4 -> System.exit(0);
                default -> {
                }
            }
        }
        
    }
    
}
