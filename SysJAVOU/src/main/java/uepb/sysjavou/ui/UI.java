package uepb.sysjavou.ui;

import javax.swing.JOptionPane;

public class UI{
    private String title;

    public UI(String t){
        this.title = t;
    }
    public void showMsg(String message){
        JOptionPane.showConfirmDialog(null, message, this.title, JOptionPane.DEFAULT_OPTION);
    }
    public String askText(String normalQuestion, String errorInformation){
        String auxStr;
        do{
            auxStr = JOptionPane.showInputDialog(null, normalQuestion, this.title, 
                                                    JOptionPane.QUESTION_MESSAGE);
            if(auxStr == null || auxStr.isBlank()){
                JOptionPane.showInternalMessageDialog(null, errorInformation, this.title, 
                                                        JOptionPane.ERROR_MESSAGE);
            }
        }while(auxStr == null || auxStr.isBlank());
        return auxStr;
    }
    public String askTextOnlyNumbers(String normalQuestion, String errorInformation){
        String auxStr;
        do{
            auxStr = JOptionPane.showInputDialog(null, normalQuestion, 
                                                    this.title, JOptionPane.QUESTION_MESSAGE);
            if(auxStr != null){
                if(!auxStr.matches("[0-9]+")){
                    JOptionPane.showInternalMessageDialog(null, "Por favor, digite somente numeros!", this.title, 
                                                                JOptionPane.ERROR_MESSAGE);
                    auxStr = null;
                }
            }else{
                JOptionPane.showInternalMessageDialog(null, errorInformation, this.title, JOptionPane.ERROR_MESSAGE);
            }
        }while(auxStr == null);
        return auxStr;
    }
    public float askFloatValue(String normalQuestion, String errorInformation){
        String auxStr;
        boolean auxValid = false;
        float auxFloat = 0f;
        
        do{
            auxStr = JOptionPane.showInputDialog(null, normalQuestion, this.title, JOptionPane.QUESTION_MESSAGE);
            try{
                   auxFloat = Float.parseFloat(auxStr);
                   auxValid = true;
            } catch(NumberFormatException e){
                JOptionPane.showInternalMessageDialog(null, errorInformation, this.title, 
                                                        JOptionPane.ERROR_MESSAGE);
                auxValid = false;
            }
        }while(!auxValid);
        return auxFloat;
    }
    public int askOptionButtons(String normalQuestion, String errorInformation, String[] options){

            return JOptionPane.showOptionDialog(null, normalQuestion, this.title, JOptionPane.YES_NO_OPTION, 
                                                    JOptionPane.QUESTION_MESSAGE, null, options, "Masculino");

    }
    
}
