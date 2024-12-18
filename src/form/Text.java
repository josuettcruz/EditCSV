/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import file.cod;
import file.csv;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josue
 */
public class Text extends javax.swing.JFrame {
    
    private csv doc;
    
    private int index;

    /**
     * Creates new form Tube
     */
    public Text() {
        
        initComponents();
        
    }
    
    public void Enter(csv receive){
        
        this.doc = receive;
        
        setVisible(true);
        
        setTitle("Texto");
        setLocation(Location.x,Location.y);
        setResizable(false);
        
        mais.setEnabled(this.doc.Tot() > 1);
        menos.setEnabled(this.doc.Tot() > 1);
        
        if(this.doc.Tot() > 0){
            this.index = this.doc.Tot();
        } else {
            this.index = -1;
        }
        
        Click();
        
    }//Enter(csv doc)
    
    private String N(){
        
        String note = "";
        
        int n = this.index + 1;
        
        if(this.index >= 0){
            
            n = this.index+1;
            
        } else {
            
            n = this.doc.Tot();
            
        }
        
        int max = this.doc.Tot();
        
        if(n < 10 && max >= 10){
            note += "0";
        }
        
        if(n < 100 && max >= 100){
            note += "0";
        }
        
        if(n < 1000 && max >= 1000){
            note += "0";
        }
        
        if(n < 10000 && max >= 10000){
            note += "0";
        }
        
        note += n;
        
        return note;
        
    }
    
    private void Click(){
        
        int num = this.index;
        
        final int n = 1;
        
        if(this.doc.Tot() >= 0 && num >= 0){
            
            select.setText(N() + "/" + this.doc.Tot());
            
        } else if(this.doc.Tot() >= 0){
            
            select.setText(this.doc.Tot() + "/" + this.doc.Tot());
            
        } else {
            
            select.setText("--/--");
            
        }
        
        if(num >= 0 && num < this.doc.Tot()){
            
            title.setText(this.doc.Read(num, 0));
            
            if(this.doc.Tot(num) > n){
                
                String texto = "";
                
                for(int i = n; i < this.doc.Tot(num); i++){
                    
                    if(i > n){
                        texto += "\n";
                    }
                    
                    texto += this.doc.Read(num, i);
                    
                }
                
                txt.setText(texto);
                
            } else {//if(this.doc.Tot(num) > n)
                
                txt.setText("");
                
            }//if(this.doc.Tot(num) > n)
            
        } else {//if(num >= 0 && num < this.doc.Tot())
            
            title.setText("");
            txt.setText("");
            
        }//if(num >= 0 && num < this.doc.Tot())
        
        KeyRelease();
        
    }//Click(int num)
    
    private void Alter(boolean sum){
        
        if(sum){
            
            if(this.index < 0){
                this.index = 0;
            } else if(this.index == this.doc.Tot()){
                this.index = 0;
            } else {
                this.index = this.index+1;
            }
            
        } else {
            
            if(this.index < 0){
                this.index = this.doc.Tot();
            } else if(this.index == 0){
                this.index = this.doc.Tot();
            } else {
                this.index = this.index-1;
            }
            
        }//if(sum)
        
        Click();
        
    }//Alter(boolean sum)
    
    private void Return(){
        dispose();
        Index novo = new Index();
        novo.Enter(true);
    }
    
    private void Err(){
        System.out.println("Erro inesperado!");
        System.exit(0);
    }
    
    private void Action(){
        
        List<String> connect = new ArrayList();
        
        String obs = txt.getText().trim();
        
        connect.add(title.getText().trim());
        
        if(!obs.isBlank()){
            
            if(obs.contains("\n")){
                
                String[] description = obs.split("\n");
                
                for(String op : description){
                    
                    if(!op.isBlank()){
                        
                        connect.add(op.trim());
                        
                    }//if(!op.isBlank())
                    
                }//for(String op : description)
                
            } else {//if(obs.contains("\n"))
                
                connect.add(obs.trim());
                
            }//if(obs.contains("\n"))
            
        }//if(!obs.isBlank())
        
        if(this.index >= 0 && this.index < this.doc.Tot()){
            
            this.doc.Reply(this.index, connect);
            
        } else {
            
            this.doc.Insert(connect);
            
        }//if(this.index >= 0 && this.index < this.doc.Tot())
        
        Alter(true);
        Click();
        
    }//Action()
    
    private void Save(){
        
        cod d = new cod();
        
        if(d.Link(title.getText().trim()) || title.getText().trim().isBlank()){
            
            title.setText("");
            title.requestFocus();
            
        } else {//if(nome.isBlank())
            
            String document;
            
            if(txt.getText().contains("\n")){
                
                String[] tx = txt.getText().split("\n");
                
                int ini = 0;
                
                while(tx[ini].trim().isBlank() && ini < tx.length){
                    
                    ini++;
                    
                }
                
                document = tx[ini].trim();
                
            } else {//if(txt.getText().contains("\n"))
                
                document = txt.getText().trim();
                
            }//if(txt.getText().contains("\n"))
            
            if(d.Link(document) || document.isBlank()){
                
                txt.setText("");
                txt.requestFocus();
                
            } else {//if(d.Link(document) || document.isBlank())
                
                Action();
                
            }//if(d.Link(document) || document.isBlank())
            
        }//if(d.Link(title.getText().trim()) || title.getText().trim().isBlank())
        
    }//Save()
    
    private void KeyRelease(){
        
        if(txt.getText().contains("\t")){
            txt.setText(txt.getText().replaceAll("\t", ""));
        }
        
        boolean t0 = title.getText().trim().isBlank();
        boolean t5 = txt.getText().trim().isBlank();
        
        boolean auto = t0 && t5;
        
        boolean date = this.doc.Tot() > 0;
        
        if(this.index >= 0 && this.index < this.doc.Tot()){
            
            mais.setEnabled(date);
            menos.setEnabled(date);
            find.setEnabled(true);
            
        } else {
            
            mais.setEnabled(auto && date);
            menos.setEnabled(auto && date);
            find.setEnabled(auto);
            
        }//if(this.index >= 0 && this.index < this.doc.Tot())
        
    }//KeyRelease()

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JTextField();
        title_txt = new javax.swing.JLabel();
        conteudo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();
        find = new javax.swing.JButton();
        mais = new javax.swing.JButton();
        select = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        menos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        title.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        title.setText("jTextField1");
        title.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                titleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                titleKeyReleased(evt);
            }
        });

        title_txt.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        title_txt.setText("Título:");

        conteudo.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        conteudo.setText("Conteúdo:");

        txt.setColumns(20);
        txt.setRows(5);
        txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txt);

        find.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        find.setText("Voltar");
        find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findActionPerformed(evt);
            }
        });

        mais.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        mais.setText(">>");
        mais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maisActionPerformed(evt);
            }
        });

        select.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        select.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        select.setText("1/1");

        save.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        save.setText("Validar");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        menos.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        menos.setText("<<");
        menos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(title)
                    .addComponent(title_txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(conteudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(save)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menos)
                        .addGap(14, 14, 14)
                        .addComponent(select, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(mais, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(find)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(title_txt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(conteudo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save)
                    .addComponent(find)
                    .addComponent(menos)
                    .addComponent(mais)
                    .addComponent(select))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void findActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findActionPerformed
        Return();
    }//GEN-LAST:event_findActionPerformed

    private void maisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maisActionPerformed
        Alter(true);
    }//GEN-LAST:event_maisActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        Save();
    }//GEN-LAST:event_saveActionPerformed

    private void menosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menosActionPerformed
        Alter(false);
    }//GEN-LAST:event_menosActionPerformed

    private void titleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_titleKeyReleased
        KeyRelease();
    }//GEN-LAST:event_titleKeyReleased

    private void txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyReleased
        KeyRelease();
    }//GEN-LAST:event_txtKeyReleased

    private void titleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_titleKeyPressed
        
        /*if(evt.getKeyCode() == 10){
            Save();
        }*/
        
        switch(evt.getKeyCode()){
            
            case 10 ->{
                
                Save();
                
            }
            
            case 40 ->{
                
                if(!txt.getText().contains("\n")){
                    txt.requestFocus();
                }
                
            }
            
        }//switch(evt.getKeyCode())
        
    }//GEN-LAST:event_titleKeyPressed

    private void txtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyPressed
        
        switch(evt.getKeyCode()){
            
            case 9 ->{
                
                title.requestFocus();
                
            }//case 9
            
            case 38 ->{
                
                if(!txt.getText().contains("\n")){
                    title.requestFocus();
                }
                
            }//case 9
            
            case 12 ->{
                
                Save();
                
            }//case 12
            
        }//switch(evt.getKeyCode())
        
    }//GEN-LAST:event_txtKeyPressed

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
            java.util.logging.Logger.getLogger(Text.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Text.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Text.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Text.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Text().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel conteudo;
    private javax.swing.JButton find;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mais;
    private javax.swing.JButton menos;
    private javax.swing.JButton save;
    private javax.swing.JLabel select;
    private javax.swing.JTextField title;
    private javax.swing.JLabel title_txt;
    private javax.swing.JTextArea txt;
    // End of variables declaration//GEN-END:variables
}
