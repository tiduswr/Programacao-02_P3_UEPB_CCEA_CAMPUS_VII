package uepb.clinica.ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import uepb.clinica.Atendimento;
import uepb.clinica.Paciente;
import uepb.clinica.Servidor;

public class frmRegAtend extends javax.swing.JDialog {
    
    private Paciente paciente;
    private final String[] tblHeaders = new String [] {"Cód.Atendimento", "CPF Prof.Saude", "Data Atendimento",
                                                "Valor Pago", "Data de Pagamento", "Descrição"};
    private DialogUI ask;
    private Servidor org;
    
    public frmRegAtend(Paciente paciente, Servidor org) {
        this.initComponents();
        this.paciente = paciente;
        this.org = org;
    }
    public Paciente getPaciente(){
        return paciente;
    }
    public Servidor getOrg() {
        return org;
    }
    
    public void showForm(){
        
        if(this.paciente != null){
            Paciente p = this.getPaciente();
            DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
            
            this.lblCPF.setText("CPF: " + p.getCpf());
            this.lblNome.setText("Nome: " + p.getNome());
            this.lblProbSaude.setText("Problema de Saúde: " + p.getProblemaSaude());
            try{
                this.lblDtUltPagamento.setText("Data do Ultimo Pagamento: " + df.format(p.getDataUltimoPagamento()));
            }catch(NullPointerException e){
                this.lblDtUltPagamento.setText("Data do Ultimo Pagamento: ");
            }
            this.tblAtendimentos.setModel(new DefaultTableModel(paciente.getAtendimentosObjectArray(), this.tblHeaders));
        }else{
            this.tblAtendimentos.setModel(new DefaultTableModel(null, this.tblHeaders));
        }
          
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setTitle("Registrar Atendimentos");
        this.ask = new DialogUI("Atenção!");
        this.setVisible(true);
    }
    
     private boolean txtboxIsEmpty(JTextField t){
        if(t.getText() != null){
            if(t.getText().trim().isEmpty()){
                return true;
            }else{
                return false;
            }
        }else{
            return true;
        }
    }
    private boolean txtboxIsDate(JTextField t){
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        boolean retorno = true;
        
        try{
            format.setLenient(false);
            format.parse(t.getText());
        }catch(ParseException e){
            retorno = false;
        }
        return retorno;
    }
     
    private void addAtend(){
        DefaultTableModel dm = (DefaultTableModel) this.tblAtendimentos.getModel();
        
        if(this.txtboxIsEmpty(this.txtbCPFProfSaude) || this.txtboxIsEmpty(this.txtbDescAtend) ||
                this.txtboxIsEmpty(this.txtbDtPag) || this.txtboxIsEmpty(this.txtbDtAtend) ||
                this.txtboxIsEmpty(this.txtbValorPago)){
            ask.showMsg("Erro, por favor, preencha todos os campos!");
        } else{
            
            if(org.getProfSaude(this.txtbCPFProfSaude.getText()) != null){
                if(this.txtbValorPago.getText().matches("[0-9]+")){
                    if(this.txtboxIsDate(this.txtbDtPag) && this.txtboxIsDate(this.txtbDtAtend)){
                        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");                    
                        SimpleDateFormat df2 = new SimpleDateFormat("dd-mm-yyyy");

                        try {
                            int temp = this.org.gerarCodAtend();

                            dm.addRow(new Object[] {temp, this.txtbCPFProfSaude.getText(),
                                df2.format(df.parse(this.txtbDtAtend.getText())),
                                Float.parseFloat(this.txtbValorPago.getText()), 
                                df2.format(df.parse(this.txtbDtPag.getText())), this.txtbDescAtend.getText()});
                            this.paciente.addAtendimento(new Atendimento(temp, this.getPaciente().getCpf(), this.txtbCPFProfSaude.getText(),
                                                        df.parse(this.txtbDtAtend.getText()), df.parse(this.txtbDtPag.getText()), 
                                                        Float.parseFloat(this.txtbValorPago.getText()), this.txtbDescAtend.getText()));
                            
                            this.getPaciente().setDataUltimoPagamento(df.parse(this.txtbDtPag.getText()));
                            this.lblDtUltPagamento.setText("Data do Ultimo Pagamento: " + df.format(this.getPaciente().getDataUltimoPagamento()));
                            this.tblAtendimentos.setModel(dm);
                        } catch (ParseException ex) {
                            ask.showMsg("Erro, por favor, digite datas validas no formato ##/##/####!");
                        }

                    }else{
                        ask.showMsg("Erro, por favor, digite datas validas no formato ##/##/####!");
                    }
                }else{
                    ask.showMsg("Erro, por favor, digite apenas numeros no campo de Valor!");
                }
            }else{
                ask.showMsg("Nenhum profissional foi encontrado para o CPF digitado!");
            }
        }
        
    }
    private void removeAtend(){
        int row = this.tblAtendimentos.getSelectedRow();
        
        if(row != -1){
            DefaultTableModel dm = (DefaultTableModel) this.tblAtendimentos.getModel();
            int value = Integer.parseInt(String.valueOf(dm.getValueAt(row, 0)));
            
            if(paciente.removeAtendimento(value)){
                dm.removeRow(row);
                this.tblAtendimentos.setModel(dm);
                ask.showMsg("Item removido!");
            }else{
                ask.showMsg("Item não removido!");
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNome = new javax.swing.JLabel();
        lblCPF = new javax.swing.JLabel();
        lblProbSaude = new javax.swing.JLabel();
        lblDtUltPagamento = new javax.swing.JLabel();
        lblLine = new javax.swing.JLabel();
        lblCPFProfSaude = new javax.swing.JLabel();
        txtbCPFProfSaude = new javax.swing.JTextField();
        txtbDtAtend = new javax.swing.JTextField();
        lblDtAtend = new javax.swing.JLabel();
        txtbValorPago = new javax.swing.JTextField();
        lblValorPago = new javax.swing.JLabel();
        lblDtPag = new javax.swing.JLabel();
        txtbDtPag = new javax.swing.JTextField();
        txtbDescAtend = new javax.swing.JTextField();
        lblDescAtend = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAtendimentos = new javax.swing.JTable();
        btRegistrar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNome.setForeground(new java.awt.Color(0, 0, 0));
        lblNome.setText("Nome:");

        lblCPF.setForeground(new java.awt.Color(0, 0, 0));
        lblCPF.setText("CPF:");

        lblProbSaude.setForeground(new java.awt.Color(0, 0, 0));
        lblProbSaude.setText("Problema de Saude:");

        lblDtUltPagamento.setForeground(new java.awt.Color(0, 0, 0));
        lblDtUltPagamento.setText("Data do Ultimo Pagamento:");

        lblLine.setBackground(new java.awt.Color(0, 0, 0));
        lblLine.setOpaque(true);

        lblCPFProfSaude.setForeground(new java.awt.Color(0, 0, 0));
        lblCPFProfSaude.setText("CPF Profissional Saude:");

        lblDtAtend.setForeground(new java.awt.Color(0, 0, 0));
        lblDtAtend.setText("Data do Atendimento:");

        lblValorPago.setForeground(new java.awt.Color(0, 0, 0));
        lblValorPago.setText("Valor Pago:");

        lblDtPag.setForeground(new java.awt.Color(0, 0, 0));
        lblDtPag.setText("Data do Pagamento:");

        lblDescAtend.setForeground(new java.awt.Color(0, 0, 0));
        lblDescAtend.setText("Descrição do Atendimento:");

        tblAtendimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblAtendimentos);

        btRegistrar.setText("Registrar");
        btRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegistrarActionPerformed(evt);
            }
        });

        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
                    .addComponent(lblProbSaude, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
                    .addComponent(lblDtUltPagamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
                    .addComponent(lblLine, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCPFProfSaude, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtbCPFProfSaude))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDtAtend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtbDtAtend, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblValorPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtbValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDtPag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtbDtPag, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbDescAtend)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDescAtend, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCPF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProbSaude)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDtUltPagamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLine, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCPFProfSaude)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbCPFProfSaude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDtAtend)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbDtAtend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblValorPago)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtbValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDtPag)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbDtPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDescAtend)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbDescAtend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(btExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegistrarActionPerformed
        this.addAtend();
    }//GEN-LAST:event_btRegistrarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        this.removeAtend();
    }//GEN-LAST:event_btExcluirActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btRegistrar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCPFProfSaude;
    private javax.swing.JLabel lblDescAtend;
    private javax.swing.JLabel lblDtAtend;
    private javax.swing.JLabel lblDtPag;
    private javax.swing.JLabel lblDtUltPagamento;
    private javax.swing.JLabel lblLine;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblProbSaude;
    private javax.swing.JLabel lblValorPago;
    private javax.swing.JTable tblAtendimentos;
    private javax.swing.JTextField txtbCPFProfSaude;
    private javax.swing.JTextField txtbDescAtend;
    private javax.swing.JTextField txtbDtAtend;
    private javax.swing.JTextField txtbDtPag;
    private javax.swing.JTextField txtbValorPago;
    // End of variables declaration//GEN-END:variables
}
