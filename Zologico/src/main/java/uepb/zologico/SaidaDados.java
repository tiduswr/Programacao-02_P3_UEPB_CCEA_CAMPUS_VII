package uepb.zologico;

import uepb.zologico.ui.DialogUI;
import uepb.zologico.ui.frmListar;

public class SaidaDados{
    public void mostraGato(Gato gato){
        System.out.println(gato.getRaca());
    }
    
    //Galinhas
    public void mostrarGalinhas(DataBase db){
        frmListar listar = new frmListar(db.getGalinhasObjectList(), db.getGalinhasObjectListHeader(), 
                "Listagem de Galinhas Cadastradas", "Galinhas Cadastradas");
        listar.showForm();
    }
    public void mostrarGalinha(Galinha g){
        DialogUI ask = new DialogUI("Dados da Galinha");
        String dadosGalinha;
        
        dadosGalinha =  "Dados da Galinha:" +
                        "\n\nNome: " + g.getNome() +
                        "\nIdade: " + String.valueOf(g.getIdadeMeses()) + "Meses " + 
                        "\nPeso: " + String.valueOf(g.getPeso()) +
                        "\nPlumagem: " + g.getPlumagem() + 
                        "\nClasse: " + g.getClasse() + 
                        "\nEspécie: " + g.getEspecie() + 
                        "\nFamilia: " + g.getFamilia() +
                        "\nFilo: " + g.getFilo() + 
                        "\nGenero: " + g.getGenero() +
                        "\nOrdem: " + g.getOrdem() +
                        "\nReino: " + g.getReino();
        
        if(g.isVoa()){
            dadosGalinha = dadosGalinha + "\nCapacidade de Voar: Possui";
        }else{
            dadosGalinha = dadosGalinha + "\nCapacidade de Voar: Não Possui";
        }
        dadosGalinha = dadosGalinha + "\nLocalização: Latitude(" + String.valueOf(g.getLocalizacao().getLatitude()) + ") e "
                + "Longitude(" + String.valueOf(g.getLocalizacao().getLongitude()) + ")";
        ask.showMsg(dadosGalinha);
    }
    public void detalharGalinha(DataBase db){
        frmListar listar = new frmListar(db.getGalinhasObjectList(), db.getGalinhasObjectListHeader(), 
                "Listagem de Galinhas Cadastradas", "Galinhas Cadastradas", 0);
        DialogUI ask = new DialogUI("Atenção!");
        
        listar.showForm();
        String nomeGalinha = listar.getRetorno();
        
        if(db.galinhaContains(nomeGalinha)){
            this.mostrarGalinha(db.getGalinha(nomeGalinha));
        }else{
            ask.showMsg("Galinha não encontrada!");
        }
    }
}
