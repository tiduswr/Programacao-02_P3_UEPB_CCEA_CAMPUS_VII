package uepb.zologico;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//Arraylist ja é por padrão uma classe que implementa a interface Serializable

public class DataBase {
    private ArrayList<Galinha> galinhas;
    
    public DataBase(){
        this.galinhas = new ArrayList();
    }
    
    public boolean addGalinha(Galinha arg){
        return galinhas.add(arg);
    }
    public Galinha getGalinha(String nome){
        for(Galinha g : this.galinhas){
            if(g.getNome().equalsIgnoreCase(nome)){
                return g;
            }
        }
        return null;
    }
    public boolean removeGalinha(String nome){
        Galinha arg = this.getGalinha(nome);
        
        if(arg != null){
            galinhas.remove(arg);
            return true;
        }else{
            return false;
        }
    }
    public boolean galinhaContains(String nome){
        Galinha arg = this.getGalinha(nome);
        return arg != null;
    }
    
    //Arquivos
    //A Classe Animal e Localização precisam ser Serializable para Funcionar
    //A Classe ArrayList<>() ja é por padrão Serializable
    public boolean saveData(){
        try{
            String dir = "/home/tiduswr/NetBeansProjects/Zologico/src/main/java/uepb/zologico";
            FileOutputStream arq = new FileOutputStream(dir + "/DataBase.db");
            ObjectOutputStream writer = new ObjectOutputStream(arq);
            
            writer.writeObject(this.galinhas);
            
            writer.close();
            arq.close();
            return true;
        }catch(IOException e){
            System.out.println("Erro em: " + e.getMessage());
            return false;
        }
    }
    public boolean readData(){
        try{
            String dir = "/home/tiduswr/NetBeansProjects/Zologico/src/main/java/uepb/zologico";
            FileInputStream arq = new FileInputStream(dir + "/DataBase.db");
            ObjectInputStream reader = new ObjectInputStream(arq);
            this.galinhas = (ArrayList<Galinha>) reader.readObject();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Erro em: " + e.getMessage());
            return false;
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Erro em: " + e.getMessage());
            return false;
        }
    }
    public Object[][] getGalinhasObjectList(){
        if(this.galinhas.size() > 0){
            Object[][] src = new Object[this.galinhas.size()][5];
            int i = 0;
            
            for(Galinha g : this.galinhas){
                src[i][0] = g.getNome();
                src[i][1] = g.getEspecie();
                src[i][2] = g.getPeso();
                src[i][3] = String.valueOf(g.getIdadeMeses()) + " Meses";
                src[i][4] = g.getGenero();
                i++;
            }
            return src;
        }else{
            return null;
        }
    }
    public String[] getGalinhasObjectListHeader(){
        return new String[] {"Nome", "Especie", "Peso", "Idade em Meses", "Genero"};
    }
}
