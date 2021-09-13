package uepb.zologico.ui;

import java.util.concurrent.CountDownLatch;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

// SwingUtilities.invokeLater() envia a chamada do Formulario Swing para a Fila de Processamento do Swing, isso é necessário
// pois todos os formulários do Swing não são Thread Safe, e se não for chamado dessa forma, dependendo da quantidade de chamadas
// que houver de formularios swing, poderá ocorrer erros inesperados!

//CountDownLatch serve para pausar o método atual até que a thread do Swing seja terminada

public class DialogUI{
    private final String title;

    public DialogUI(String t){
        this.title = t;
    }
    
    public void showMsg(String message){
        final String titulo = this.title;
        CountDownLatch latch = new CountDownLatch(1);
        
        SwingUtilities.invokeLater(
        new Runnable() {
            public void run() {
                JOptionPane.showMessageDialog(null, message, titulo, JOptionPane.DEFAULT_OPTION);
                latch.countDown();
            }
        });
        try{
            latch.await();
        }catch(InterruptedException e){}
    }
    
    public String askText(String normalQuestion, String errorInformation){
        final String[] auxStr = new String[1];
        final String titulo = this.title;
        CountDownLatch latch = new CountDownLatch(1);
        
        SwingUtilities.invokeLater(
        new Runnable() {
            @Override
            public void run() {
                do{
                    auxStr[0] = JOptionPane.showInputDialog(null, normalQuestion, titulo, 
                                                            JOptionPane.QUESTION_MESSAGE);
                    if(auxStr[0] == null || auxStr[0].isBlank()){
                        JOptionPane.showMessageDialog(null, errorInformation, titulo, 
                                                                JOptionPane.ERROR_MESSAGE);
                    }
                }while(auxStr == null || auxStr[0].isBlank());
                latch.countDown();
            }
        });
        
        try{
            latch.await();
        }catch(InterruptedException e){}
        return auxStr[0];
    }
    public String askTextOnlyNumbers(String normalQuestion, String errorInformation){
        final String[] auxStr = new String[1];
        CountDownLatch latch = new CountDownLatch(1);
        String title = this.title;
        
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                do{
                    auxStr[0] = JOptionPane.showInputDialog(null, normalQuestion, 
                                                            title, JOptionPane.QUESTION_MESSAGE);
                    if(auxStr != null){
                        if(!auxStr[0].matches("[0-9]+")){
                            JOptionPane.showMessageDialog(null, "Por favor, digite somente numeros!", title, 
                                                                        JOptionPane.ERROR_MESSAGE);
                            auxStr[0] = null;
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, errorInformation, title, JOptionPane.ERROR_MESSAGE);
                    }
                }while(auxStr[0] == null);
                latch.countDown();
            }
        });
        try{
            latch.await();
        }catch(InterruptedException e){};
        return auxStr[0];
    }
    public float askFloatValue(String normalQuestion, String errorInformation){
        final float[] auxFloat = new float[1];
        String title = this.title;
        CountDownLatch latch = new CountDownLatch(1);
        
        SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run(){
                    String auxStr;
                    boolean auxValid = false;
                    do{
                        auxStr = JOptionPane.showInputDialog(null, normalQuestion, title, JOptionPane.QUESTION_MESSAGE);
                        try{
                               auxFloat[0] = Float.parseFloat(auxStr);
                               auxValid = true;
                        } catch(NumberFormatException e){
                            JOptionPane.showMessageDialog(null, errorInformation, title, 
                                                                    JOptionPane.ERROR_MESSAGE);
                            auxValid = false;
                        }
                    }while(!auxValid);
                    latch.countDown();
                }
            }
        );
        
        try{
            latch.await();
        }catch(InterruptedException e){}
        return auxFloat[0];
    }
    public int askOptionButtons(String normalQuestion, String errorInformation, String[] options){
        
        final Integer[] opt = new Integer[1];
        final String titulo = this.title;
        final CountDownLatch latch = new CountDownLatch(1);
        
        SwingUtilities.invokeLater(
            new Runnable() {
                @Override
                public void run() {
                    opt[0] = JOptionPane.showOptionDialog(null, normalQuestion, titulo, JOptionPane.YES_NO_OPTION, 
                                                JOptionPane.QUESTION_MESSAGE, null, options, "Masculino");
                    latch.countDown();
                }
            }
        );
        try{
            latch.await();
        }catch(InterruptedException e){}
        return opt[0];
    }
    
}
