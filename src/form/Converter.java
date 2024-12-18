/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import file.csv;
import java.time.LocalDate;

/**
 *
 * @author josue
 */
public class Converter extends javax.swing.JFrame {
    
    /**
     * Creates new form Converter
     */
    public Converter() {
        
        initComponents();
        
    }
    
    public void Enter(){
        
        setVisible(true);
        setResizable(false);
        
        setTitle("Converter CSV em HTML");
        setLocation(Location.x,Location.y);
        
    }
    
    private void Exc(boolean tentar, String tct){
        
        if(tentar){
            
            System.out.println(tct);
            System.exit(0);
            
        } else {
            
            dispose();
            Index into = new Index();
            into.Enter(tct);
            
        }
        
    }//Exc(boolean tentar, String tct)
    
    private String Date(String name){
        
        LocalDate tara = LocalDate.now();
        
        int a = tara.getYear();
        int m = tara.getMonthValue();
        int d = tara.getDayOfMonth();
        int s = tara.getDayOfWeek().getValue();
        
        String title = "";
        
        if(d < 10){
            title += "0";
        }
            
        title += d;
        
        title += " de ";
        
        switch(m){
            
            case 1 ->{
                title += "Janeiro";
            }
            
            case 2 ->{
                title += "Fevereiro";
            }
            
            case 3 ->{
                title += "Março";
            }
            
            case 4 ->{
                title += "Abril";
            }
            
            case 5 ->{
                title += "Maio";
            }
            
            case 6 ->{
                title += "Junho";
            }
            
            case 7 ->{
                title += "Julho";
            }
            
            case 8 ->{
                title += "Agosto";
            }
            
            case 9 ->{
                title += "Setembro";
            }
            
            case 10 ->{
                title += "Outubro";
            }
            
            case 11 ->{
                title += "Novembo";
            }
            
            case 12 ->{
                title += "Dezembro";
            }
            
        }//switch(m)
        
        title += " de ";
        title += a;
        title += " - ";
        
        switch(s){
            
            case 1 ->{
                title += "Segunda";
            }
            
            case 2 ->{
                title += "Terça";
            }
            
            case 3 ->{
                title += "Quarta";
            }
            
            case 4 ->{
                title += "Quinta";
            }
            
            case 5 ->{
                title += "Sexta";
            }
            
            case 6 ->{
                title += "Sáb";
            }
            
            case 7 ->{
                title += "DOM";
            }
            
        }//switch(tara.getDayOfWeek().getValue())
        
        if(s < 6){
            title += "-feira";
        }
        
        title += " - \"";
        title += name;
        title += "\"";
        
        return title;
        
    }//Date(String name)
    
    private String Date(){
        
        LocalDate tara = LocalDate.now();
        
        int a = tara.getYear();
        int m = tara.getMonthValue();
        int d = tara.getDayOfMonth();
        
        String title = "";
        
        if(d < 10){
            title += "0";
        }
            
        title += d;
        
        title += "/";
        
        if(m < 10){
            title += "0";
        }
        
        title += m;
        
        title += "/";
        title += a;
        
        return title;
        
    }//Date()
    
    private void Exportar(String diretory, String folder, String file){
        
        csv cmd = new csv(diretory.replace(".csv",""));
        
        Exportar mht = new Exportar(cmd, folder);
        
        mht.Export(Date(),Date(file.substring(0,file.lastIndexOf("."))),file.substring(0,file.lastIndexOf(".")));
        
    }
    
    private void Action(String diretory){
        
        String folder = diretory.substring(0,diretory.lastIndexOf("\\"));
        folder += "\\";
        
        String file = diretory.substring(diretory.lastIndexOf("\\")+1);
        
        if(file.contains(".")){
            
            String ext = file.substring(file.indexOf("."));
            
            switch(ext.toLowerCase().replace(".", "")){
                
                case "csv" ->{
                    
                    Exportar(diretory, folder, file);
                    Exc(true, "Execução bem sucedida!");
                    
                }
                
                default ->{
                    
                    Exc(false, "O arquivo: \n" + file + "\nNao é CSV!");
                    
                }
                
            }
            
        } else {
            
            Exc(false, "Arquivo \"" + diretory + "\" Inválido!");
            
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

        arq = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        arq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arqActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(arq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(arq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void arqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arqActionPerformed
        
        try{
            
            Action(arq.getSelectedFile().toString());
            
        }catch(Exception ev){
            
            Exc(false, ev.getMessage());
            
        }
        
    }//GEN-LAST:event_arqActionPerformed

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
            java.util.logging.Logger.getLogger(Converter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Converter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Converter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Converter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Converter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser arq;
    // End of variables declaration//GEN-END:variables
}
