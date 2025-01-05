/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import file.cod;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author josue
 */
public class Index extends javax.swing.JFrame {

    /**
     * Creates new form Index
     */
    public Index() {
        
        initComponents();
        
    }
    
    private String Text(String dig, int max){
        
        String txt = "";
        
        int letter = 1;
        
        for(int i = 0; i < dig.length(); i++){
            
            char at = dig.charAt(i);
            
            switch(at){
                
                case '\n' ->{
                    letter = 1;
                    txt += "\n";
                }
                
                default ->{
                        
                    txt += at;
                    
                    if(letter >= max){
                        txt += '\n';
                        letter = 1;
                    } else {
                        letter++;
                    }
                    
                }
                
            }//switch(at)
            
        }//for(int i = 0; i < dig.length(); i++)
        
        return txt;
        
    }//Text(String dig, int max)
    
    private String DataCompleta(){
        
        int a = LocalDate.now().getYear();
        int m = LocalDate.now().getMonthValue();
        int d = LocalDate.now().getDayOfMonth();
        int s = LocalDate.now().getDayOfWeek().getValue();
        
        String txt = "";
        
        final String comp[] = {"Bom Dia", "Boa Tarde", "Boa Noite"};
        
        if(LocalTime.now().getHour() < 12){
            txt = comp[0];
        } else if(LocalTime.now().getHour() < 18){
            txt = comp[1];
        } else if(LocalTime.now().getHour() > 18){
            txt = comp[2];
        } else if(LocalTime.now().getMinute() <= 30){
            txt = comp[1];
        } else {
            txt = comp[2];
        }
        
        txt += ", hoje é dia ";
        
        switch(s){
            
            case 1 ->{
                txt += "Segunda";
            }
            
            case 2 ->{
                txt += "Terça";
            }
            
            case 3 ->{
                txt += "Quarta";
            }
            
            case 4 ->{
                txt += "Quinta";
            }
            
            case 5 ->{
                txt += "Sexta";
            }
            
            case 6 ->{
                txt += "Sábado";
            }
            
            case 7 ->{
                txt += "Domingo";
            }
            
        }//switch(s)
        
        if(s < 6){
            
            txt += "-feira";
            
        }
        
        txt += ", dia ";
        
        txt += d;
        
        if(d == 1){
            txt += "º";
        }
        
        txt += " de ";
        
        switch(m){
            
            case 1 ->{
                txt += "Janeiro";
            }
            
            case 2 ->{
                txt += "Fevereiro";
            }
            
            case 3 ->{
                txt += "Março";
            }
            
            case 4 ->{
                txt += "Abril";
            }
            
            case 5 ->{
                txt += "Maio";
            }
            
            case 6 ->{
                txt += "Junho";
            }
            
            case 7 ->{
                txt += "Julho";
            }
            
            case 8 ->{
                txt += "Agosto";
            }
            
            case 9 ->{
                txt += "Setembro";
            }
            
            case 10 ->{
                txt += "Outubro";
            }
            
            case 11 ->{
                txt += "Novembro";
            }
            
            case 12 ->{
                txt += "Dezembro";
            }
            
        }//switch(m)
        
        txt += " de ";
        
        txt += a;
        
        txt += "!";
        
        return txt;
        
    }//DataCompleta()
    
    public String DataAbreviada(){
        
        final String separator = "/";
        
        int a = LocalDate.now().getYear();
        int m = LocalDate.now().getMonthValue();
        int d = LocalDate.now().getDayOfMonth();
        
        String txt = "";
        
        if(d < 10){
            txt += "0";
        }
        
        txt += d;
        txt += separator;
        
        if(m < 10){
            txt += "0";
        }
        
        txt += m;
        txt += separator;
        txt += a;
        
        return txt;
        
    }//DataAbreviada()
    
    private void Enter(boolean exit, String tct){
        
        setVisible(true);
        setResizable(false);
        
        sair.setEnabled(exit);
        
        abrir.setEnabled(true);
        novo.setEnabled(true);
        msg.setEditable(false);
        msg.setVisible(true);
        if(exit){
            setTitle(DataAbreviada());
        } else {
            setTitle(DataCompleta());
        }
        
        setLocation(Location.x,Location.y);
        
        if(tct.isBlank()){
            
            msg.setVisible(false);
            msg.setText("");
            
        } else {
            
            msg.setVisible(true);
            msg.setText(Text(tct,95));
            
        }//if(tct.isBlank())
        
        //msg.setText("-".repeat(95));
        
    }
    
    public void Enter(boolean exit){
        
        Enter(exit,"");
        
    }//Enter()
    
    public void Enter(String tct){
        
        Enter(true,tct);
        
    }//Enter()

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        abrir = new javax.swing.JButton();
        novo = new javax.swing.JButton();
        converter = new javax.swing.JButton();
        sair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        msg = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        abrir.setBackground(new java.awt.Color(0, 204, 204));
        abrir.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        abrir.setText("Abrir");
        abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirActionPerformed(evt);
            }
        });

        novo.setBackground(new java.awt.Color(0, 204, 204));
        novo.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        novo.setText("Novo");
        novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoActionPerformed(evt);
            }
        });

        converter.setBackground(new java.awt.Color(0, 204, 204));
        converter.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        converter.setText("CONVERTER");
        converter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                converterActionPerformed(evt);
            }
        });

        sair.setBackground(new java.awt.Color(0, 204, 204));
        sair.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        sair.setForeground(java.awt.Color.red);
        sair.setText("Sair");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });

        msg.setColumns(20);
        msg.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        msg.setRows(5);
        jScrollPane1.setViewportView(msg);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(converter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(abrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(novo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(converter, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(abrir, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(novo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sair, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirActionPerformed
        
        dispose();
        Open open = new Open();
        open.Enter();
        
    }//GEN-LAST:event_abrirActionPerformed

    private void novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoActionPerformed
        
        dispose();
        Novo new_arq = new Novo();
        new_arq.Enter();
        
    }//GEN-LAST:event_novoActionPerformed

    private void converterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_converterActionPerformed
        
        dispose();
        Converter csv_html = new Converter();
        csv_html.Enter();
        
    }//GEN-LAST:event_converterActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        
        System.exit(0);
        
    }//GEN-LAST:event_sairActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Index().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrir;
    private javax.swing.JButton converter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea msg;
    private javax.swing.JButton novo;
    private javax.swing.JButton sair;
    // End of variables declaration//GEN-END:variables
}
