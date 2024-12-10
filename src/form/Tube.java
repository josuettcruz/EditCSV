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
public class Tube extends javax.swing.JFrame {
    
    private csv doc;
    
    private int index;

    /**
     * Creates new form Tube
     */
    public Tube() {
        
        initComponents();
        
    }
    
    public void Enter(csv receive){
        
        this.doc = receive;
        
        setVisible(true);
        
        setTitle("YouTube");
        setLocation(Location.x,Location.y);
        
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
        
        final int n = 5;
        
        if(this.doc.Tot() >= 0 && num >= 0){
            
            select.setText(N() + "/" + this.doc.Tot());
            
        } else if(this.doc.Tot() >= 0){
            
            select.setText(this.doc.Tot() + "/" + this.doc.Tot());
            
        } else {
            
            select.setText("--/--");
            
        }
        
        if(num >= 0 && num < this.doc.Tot()){
            
            title.setText(this.doc.Read(num, 0));
            vcr.setText(this.doc.Read(num, 1));
            lnk.setText(this.doc.Read(num, 2));
            vch.setText(this.doc.Read(num, 3));
            lch.setText(this.doc.Read(num, 4));
            
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
            vcr.setText("");
            lnk.setText("");
            vch.setText("");
            lch.setText("");
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
        connect.add(vcr.getText().trim());
        connect.add(lnk.getText().trim());
        connect.add(vch.getText().trim());
        connect.add(lch.getText().trim());
        
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
        
        String geral = title.getText().trim();
        String nome = vcr.getText().trim();
        String link = lnk.getText().trim();
        String canal = vch.getText().trim();
        String ch = lch.getText().trim();
        
        if(d.Link(geral)){
            
            title.setText("");
            title.requestFocus();
            
        } else if(d.Link(nome)){
            
            vcr.setText("");
            vcr.requestFocus();
            
        } else if(!d.Link(ch)){//if(nome.isBlank())
            
            lch.setText("");
            lch.requestFocus();
            
        } else if(!d.Link(link)){//if(nome.isBlank())
            
            lnk.setText("");
            lnk.requestFocus();
            
        } else if(d.Link(canal)){//if(nome.isBlank())
            
            if(d.Link(canal)){
                
                String[] dot = canal.split("/");
                
                /*for(int g = dot.length-1; g >= 0; g--){
                    
                    if(!dot[g].isBlank()){
                        vch.setText(dot[g]);
                    }
                    
                }*/
                
                int ini = dot.length-1;
                boolean setter = true;
                
                while(setter && ini >=0){
                    
                    if(!dot[ini].isBlank()){
                        
                        vch.setText(dot[ini]);
                        setter = false;
                        
                    }//if(!dot[ini].isBlank())
                    
                    ini--;
                    
                }//while(setter && ini >=0)
                
                if(setter){
                    Err();
                }
                
            } else {//if(d.Link(ch))
                
                Err();
                
            }//if(d.Link(ch))
            
        } else if(canal.isBlank()){//if(nome.isBlank())
            
            vch.requestFocus();
            
            if(d.Link(ch)){
                
                String[] dot = ch.split("/");
                
                /*for(int g = dot.length-1; g >= 0; g--){
                    
                    if(!dot[g].isBlank()){
                        vch.setText(dot[g]);
                    }
                    
                }*/
                
                int ini = dot.length-1;
                boolean setter = true;
                
                while(setter && ini >=0){
                    
                    if(!dot[ini].isBlank()){
                        
                        vch.setText(dot[ini]);
                        setter = false;
                        
                    }//if(!dot[ini].isBlank())
                    
                    ini--;
                    
                }//while(setter && ini >=0)
                
                if(setter){
                    Err();
                }
                
            } else {//if(d.Link(ch))
                
                Err();
                
            }//if(d.Link(ch))
            
        } else {//if(nome.isBlank())
            
            Action();
            Click();
            
        }
        
    }//Save()
    
    private void KeyRelease(){
        
        boolean t0 = title.getText().trim().isBlank();
        boolean t1 = vcr.getText().trim().isBlank();
        boolean t2 = lnk.getText().trim().isBlank();
        boolean t3 = vch.getText().trim().isBlank();
        boolean t4 = lch.getText().trim().isBlank();
        boolean t5 = txt.getText().trim().isBlank();
        
        boolean auto = t0 && t1 && t2 && t3 && t4 && t5;
        
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

        video = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        vcr = new javax.swing.JTextField();
        lnk = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        video1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        vch = new javax.swing.JTextField();
        lch = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();
        save = new javax.swing.JButton();
        find = new javax.swing.JButton();
        menos = new javax.swing.JButton();
        mais = new javax.swing.JButton();
        select = new javax.swing.JLabel();
        title = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        video.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nome:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Link:");

        vcr.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        vcr.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        vcr.setText("jTextField1");
        vcr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                vcrKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vcrKeyReleased(evt);
            }
        });

        lnk.setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N
        lnk.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lnk.setText("jTextField2");
        lnk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lnkKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lnkKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout videoLayout = new javax.swing.GroupLayout(video);
        video.setLayout(videoLayout);
        videoLayout.setHorizontalGroup(
            videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(videoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vcr)
                    .addComponent(lnk))
                .addContainerGap())
        );
        videoLayout.setVerticalGroup(
            videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(videoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(vcr, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(videoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3))
                    .addGroup(videoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        jLabel1.setText("Vídeo");

        jLabel4.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        jLabel4.setText("Canal");

        video1.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Nome:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Link:");

        vch.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        vch.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        vch.setText("jTextField1");
        vch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                vchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vchKeyReleased(evt);
            }
        });

        lch.setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N
        lch.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lch.setText("jTextField2");
        lch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout video1Layout = new javax.swing.GroupLayout(video1);
        video1.setLayout(video1Layout);
        video1Layout.setHorizontalGroup(
            video1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(video1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(video1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(video1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vch)
                    .addComponent(lch))
                .addContainerGap())
        );
        video1Layout.setVerticalGroup(
            video1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(video1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(video1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(vch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(video1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(video1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6))
                    .addGroup(video1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel7.setText("Descrição:");

        txt.setColumns(20);
        txt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt.setRows(5);
        txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txt);

        save.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        save.setText("Validar");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        find.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        find.setText("Voltar");
        find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findActionPerformed(evt);
            }
        });

        menos.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        menos.setText("<<");
        menos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menosActionPerformed(evt);
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

        title.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        title.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        title.setText("jTextField1");
        title.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                titleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                titleKeyReleased(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(save)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menos, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(select, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(find))
                    .addComponent(video1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(video, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(video, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(video1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save)
                    .addComponent(find)
                    .addComponent(menos)
                    .addComponent(mais)
                    .addComponent(select))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        Save();
    }//GEN-LAST:event_saveActionPerformed

    private void findActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findActionPerformed
        Return();
    }//GEN-LAST:event_findActionPerformed

    private void maisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maisActionPerformed
        Alter(true);
    }//GEN-LAST:event_maisActionPerformed

    private void menosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menosActionPerformed
        Alter(false);
    }//GEN-LAST:event_menosActionPerformed

    private void titleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_titleKeyReleased
        KeyRelease();
    }//GEN-LAST:event_titleKeyReleased

    private void vcrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vcrKeyReleased
        KeyRelease();
    }//GEN-LAST:event_vcrKeyReleased

    private void lnkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lnkKeyReleased
        KeyRelease();
    }//GEN-LAST:event_lnkKeyReleased

    private void vchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vchKeyReleased
        KeyRelease();
    }//GEN-LAST:event_vchKeyReleased

    private void lchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lchKeyReleased
        KeyRelease();
    }//GEN-LAST:event_lchKeyReleased

    private void txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyReleased
        KeyRelease();
    }//GEN-LAST:event_txtKeyReleased

    private void titleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_titleKeyPressed
        
        switch(evt.getKeyCode()){
            
            case 10 ->{
                Save();
            }
            
            case 40 ->{
                
                vcr.requestFocus();
                
            }
            
        }//switch(evt.getKeyCode())
        
    }//GEN-LAST:event_titleKeyPressed

    private void vcrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vcrKeyPressed
        
        switch(evt.getKeyCode()){
            
            case 10 ->{
                Save();
            }
            
            case 38 ->{
                title.requestFocus();
            }
            
            case 40 ->{
                
                lnk.requestFocus();
                
            }
            
        }//switch(evt.getKeyCode())
        
    }//GEN-LAST:event_vcrKeyPressed

    private void vchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vchKeyPressed
        
        switch(evt.getKeyCode()){
            
            case 10 ->{
                Save();
            }
            
            case 38 ->{
                
                lnk.requestFocus();
                
            }
            
            case 40 ->{
                
                lch.requestFocus();
                
            }
            
        }//switch(evt.getKeyCode())
        
    }//GEN-LAST:event_vchKeyPressed

    private void lnkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lnkKeyPressed
        
        switch(evt.getKeyCode()){
            
            case 10 ->{
                Save();
            }
            
            case 38 ->{
                
                vcr.requestFocus();
                
            }
            
            case 40 ->{
                
                vch.requestFocus();
                
            }
            
        }//switch(evt.getKeyCode())
        
    }//GEN-LAST:event_lnkKeyPressed

    private void lchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lchKeyPressed
        
        switch(evt.getKeyCode()){
            
            case 10 ->{
                Save();
            }
            
            case 38 ->{
                vch.requestFocus();
            }
            
        }//switch(evt.getKeyCode())
        
    }//GEN-LAST:event_lchKeyPressed

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
            java.util.logging.Logger.getLogger(Tube.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tube.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tube.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tube.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tube().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton find;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lch;
    private javax.swing.JTextField lnk;
    private javax.swing.JButton mais;
    private javax.swing.JButton menos;
    private javax.swing.JButton save;
    private javax.swing.JLabel select;
    private javax.swing.JTextField title;
    private javax.swing.JTextArea txt;
    private javax.swing.JTextField vch;
    private javax.swing.JTextField vcr;
    private javax.swing.JPanel video;
    private javax.swing.JPanel video1;
    // End of variables declaration//GEN-END:variables
}
