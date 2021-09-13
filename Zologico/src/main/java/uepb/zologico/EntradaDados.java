package uepb.zologico;

import javax.swing.JOptionPane;
import uepb.zologico.ui.DialogUI;
import uepb.zologico.ui.frmListar;

//import javax.swing.*;

public class EntradaDados {
    DialogUI ask = new DialogUI("Solicitação de Dados");
    
    public Gato pedirGatoUsuario(){
        Gato gato = new Gato();

        String racaGato = JOptionPane.showInputDialog(null, "Qual a raça do gato?");
        gato.setRaca(racaGato);            
        gato.setLocalizacao(pedeLocalizacao());

        return gato;
    }

    public Cachorro pedirCachorroUsuario(){
        Cachorro cachorro = new Cachorro();

        String racaCao = JOptionPane.showInputDialog(null, "Qual a raça do Cão?");
        cachorro.setRaca(racaCao);
        cachorro.setLocalizacao(pedeLocalizacao());
        return cachorro;
    }

    private Localizacao pedeLocalizacao(){
        final String ERROR_MESSAGE = "Por favor, não deixe esse campo em branco!";
        
        Localizacao localizacao = new Localizacao(
                (double) ask.askFloatValue("Informe a Latitude:", ERROR_MESSAGE),
                (double) ask.askFloatValue("Informe a Longitude:", ERROR_MESSAGE));        

        return localizacao;
    }
    
    //Galinha
    public void pedeDadosGalinhas(DataBase db){
        Galinha dados = new Galinha();
        final String ERROR_MESSAGE = "Por favor, não deixe esse campo em branco!";
        
        dados.setNome(ask.askText("Digite o nome da Galinha:", ERROR_MESSAGE));
        if(!db.galinhaContains(dados.getNome())){
            dados.setEspecie(ask.askText("Por favor digite qual a Espécie da Galinha:", ERROR_MESSAGE));
            dados.setGenero(ask.askText("Digite o Genero da Galinha:", ERROR_MESSAGE));
            dados.setPlumagem(ask.askText("Qual a cor da Plumagem?", ERROR_MESSAGE));
            dados.setIdadeMeses(Integer.parseInt(ask.askTextOnlyNumbers("Por favor digite a idade em meses da Galinha", ERROR_MESSAGE)));
            dados.setPeso(ask.askFloatValue("Por favor, digite o peso da Galinha:", ERROR_MESSAGE));
            dados.setLocalizacao(this.pedeLocalizacao());
            
            db.addGalinha(dados);
        }else{
            ask.showMsg("Uma galinha com esse nome ja esta cadastrada!");
        }
    }
    public void excluiGalinha(DataBase db){
        frmListar listar = new frmListar(db.getGalinhasObjectList(), db.getGalinhasObjectListHeader(), 
                "Selecione uma galinha para Excluir e aperte em OK!", "Galinhas Cadastradas", 0);
        listar.showForm();
        String nomeGalinha = listar.getRetorno();
        
        if(db.galinhaContains(nomeGalinha)){
            db.removeGalinha(nomeGalinha);
            ask.showMsg("Galinha excluida!");
        }else{
            ask.showMsg("Galinha não encontrada!");
        }
    }
}
