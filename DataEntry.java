/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author peba007
 */
public class DataEntry extends javax.swing.JFrame {
    
    String host = "jdbc:postgresql://localhost:5432/CIMIdentity";
    String uName = "postgres";
    String uPass = "F@lzkyyf";
    ArrayList<String> forCombo = new ArrayList<>();
    boolean uuidEntered = false;
    boolean enterPressed = false;
    
    String n_nameNew = "";
    String nt_nameNew = "";
    String nt_desNew = "";
    String nta_nameNew = "";
    String nta_desNew = "";
    String newUUID = "";
    /**
     * Creates new form DataEntry
     */
    public DataEntry() {
        initComponents();
    }
    
    //Populate Combobox with previous entries
    public void connect(String column, String table){
        try{
            Connection con = DriverManager.getConnection(host, uName, uPass);           
            Statement stmt = con.createStatement();

            String getData = "SELECT DISTINCT " + column + " FROM " + table;

            ResultSet dataSet = stmt.executeQuery(getData);
            while(dataSet.next()){
                forCombo.add(dataSet.getString(column));
            }

        }
            
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    //Send text entered to SQL database
    public void sendToDB(String n_nameNew, String nt_nameNew, String nt_desNew, String nta_nameNew, String nta_desNew){
        
        try{
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement();
        
            if(uuidEntered ==false){
            
                String genUUID = "INSERT INTO public.\"Identity\" (id_pkey, entry)"
                        + "VALUES (DEFAULT, DEFAULT)";
                stmt.executeUpdate(genUUID);
                String getUUID = "SELECT id.id_pkey FROM \"Identity\" id "
                        + "ORDER BY id.entry desc LIMIT 1";
                ResultSet rs = stmt.executeQuery(getUUID);
                rs.next();
                newUUID = rs.getString("id_pkey");
            
                System.out.println("Generate UUID selected: " + newUUID);
            }
        
            else if (uuidEntered == true){
                newUUID = enter_uuidBox.getText();
                String entUUID = "INSERT INTO public.\"Identity\"(id_pkey, entry)"
                        + "VALUES ('" + newUUID + "', DEFAULT)";
                stmt.executeUpdate(entUUID);
            }
        
            String insertIDObj = "INSERT INTO public.\"IdentifiedObject\"(io_pkey)"
                    + "VALUES('" + newUUID + "')";
            String insertName = "INSERT INTO public.\"Name\"(n_pkey, n_name)"
                    + "VALUES('" + newUUID + "', '" + n_nameNew + "')";
            String insertNT = "INSERT INTO public.\"NameType\"(nt_pkey, nt_description, nt_name)"
                    + "VALUES ('" + newUUID + "', '" + nt_desNew + "', '" + nt_nameNew + "')";
            String insertNTA = "INSERT INTO public.\"NameTypeAuthority\"(nta_pkey, nta_name, nta_description)"
                    + "VALUES ('" + newUUID + "', '" + nta_nameNew + "', '" + nta_desNew + "')";
        
            stmt.executeUpdate(insertIDObj);
            stmt.executeUpdate(insertName);
            stmt.executeUpdate(insertNT);
            stmt.executeUpdate(insertNTA);
            
            stmt.close();
            con.close();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        enterButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        n_nameBox = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        nt_nameBox = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        nt_desBox = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nta_nameBox = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        nta_desBox = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        gen_uuidSel = new javax.swing.JRadioButton();
        enter_uuidSel = new javax.swing.JRadioButton();
        enter_uuidBox = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nt_descb = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        nta_namecb = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        nta_descb = new javax.swing.JComboBox<>();
        n_name = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        nt_namecb = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        enterButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        enterButton.setText("Enter");
        enterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("NameType");

        jLabel4.setText("Name");

        nt_nameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nt_nameBoxActionPerformed(evt);
            }
        });

        jLabel5.setText("Description");

        nt_desBox.setColumns(20);
        nt_desBox.setLineWrap(true);
        nt_desBox.setRows(5);
        jScrollPane1.setViewportView(nt_desBox);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("NameTypeAuthority");

        jLabel7.setText("Name");

        nta_nameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nta_nameBoxActionPerformed(evt);
            }
        });

        jLabel8.setText("Description");

        nta_desBox.setColumns(20);
        nta_desBox.setLineWrap(true);
        nta_desBox.setRows(5);
        jScrollPane2.setViewportView(nta_desBox);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("UUID");

        buttonGroup1.add(gen_uuidSel);
        gen_uuidSel.setText("Randomly Generate");
        gen_uuidSel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gen_uuidSelActionPerformed(evt);
            }
        });

        buttonGroup1.add(enter_uuidSel);
        enter_uuidSel.setText("Enter Here:");

        try {
            enter_uuidBox.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("AAAAAAAA-AAAA-AAAA-AAAA-AAAAAAAAAAAA")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        enter_uuidBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_uuidBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Select Name");

        jLabel10.setText("Select Description");

        connect("nt_description", "\"NameType\"");
        for(int i = 0; i < forCombo.size(); i ++)
        nt_descb.addItem(forCombo.get(i));
        forCombo.clear();
        nt_descb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nt_descbActionPerformed(evt);
            }
        });

        jLabel11.setText("Select Name");

        connect("nta_name", "\"NameTypeAuthority\"");
        for(int i = 0; i < forCombo.size(); i ++)
        nta_namecb.addItem(forCombo.get(i));
        forCombo.clear();
        nta_namecb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nta_namecbActionPerformed(evt);
            }
        });

        jLabel12.setText("Select Description");

        connect("nta_description", "\"NameTypeAuthority\"");
        for(int i = 0; i < forCombo.size(); i ++)
        nta_descb.addItem(forCombo.get(i));
        forCombo.clear();
        nta_descb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nta_descbActionPerformed(evt);
            }
        });

        connect("n_name", "\"Name\"");
        for (int i = 0; i < forCombo.size(); i ++)
        n_name.addItem(forCombo.get(i));
        forCombo.clear();
        n_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n_nameActionPerformed(evt);
            }
        });

        jLabel13.setText("Select Name");

        connect("nt_name", "\"NameType\"");
        for(int i = 0; i < forCombo.size(); i ++)
        nt_namecb.addItem(forCombo.get(i));
        forCombo.clear();
        nt_namecb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nt_namecbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(nta_nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(nt_nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(n_nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(nt_namecb, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(nt_descb, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(nta_namecb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addComponent(nta_descb, 0, 202, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(n_name, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jSeparator1)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jSeparator3)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jSeparator2)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(gen_uuidSel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(enter_uuidSel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enter_uuidBox))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(enterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {nt_descb, nt_namecb, nta_descb, nta_namecb});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n_nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nt_nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nt_namecb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nt_descb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nta_namecb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nta_nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(gen_uuidSel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(enter_uuidSel)
                            .addComponent(enter_uuidBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(nta_descb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(enterButton, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterButtonActionPerformed
        // TODO add your handling code here:
        
        n_nameNew = n_nameBox.getText();
        nt_nameNew = nt_nameBox.getText();
        nt_desNew = nt_desBox.getText();
        nta_nameNew = nta_nameBox.getText();
        nta_desNew = nta_desBox.getText();
      
        sendToDB(n_nameNew, nt_nameNew, nt_desNew, nta_nameNew, nta_desNew);
        
        n_nameBox.setText("");
        nt_nameBox.setText("");
        nt_desBox.setText("");
        nta_nameBox.setText("");
        nta_desBox.setText("");
    }//GEN-LAST:event_enterButtonActionPerformed

    private void nt_nameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nt_nameBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nt_nameBoxActionPerformed

    private void gen_uuidSelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gen_uuidSelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gen_uuidSelActionPerformed

    private void nta_nameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nta_nameBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nta_nameBoxActionPerformed

    private void enter_uuidBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enter_uuidBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enter_uuidBoxActionPerformed

    private void nt_descbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nt_descbActionPerformed
        // TODO add your handling code here:
        String nameSel = (String)nt_descb.getSelectedItem();
        nt_desBox.setText(nameSel);
    }//GEN-LAST:event_nt_descbActionPerformed

    private void n_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n_nameActionPerformed
        // TODO add your handling code here:
        String nameSel = (String)n_name.getSelectedItem();
        n_nameBox.setText(nameSel);
    }//GEN-LAST:event_n_nameActionPerformed

    private void nt_namecbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nt_namecbActionPerformed
        // TODO add your handling code here:
        String nameSel = (String)nt_namecb.getSelectedItem();
        nt_nameBox.setText(nameSel);
    }//GEN-LAST:event_nt_namecbActionPerformed

    private void nta_namecbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nta_namecbActionPerformed
        // TODO add your handling code here:
        String nameSel = (String)nta_namecb.getSelectedItem();
        nta_nameBox.setText(nameSel);
    }//GEN-LAST:event_nta_namecbActionPerformed

    private void nta_descbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nta_descbActionPerformed
        // TODO add your handling code here:
        String nameSel = (String)nta_descb.getSelectedItem();
        nta_desBox.setText(nameSel);
    }//GEN-LAST:event_nta_descbActionPerformed

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
            java.util.logging.Logger.getLogger(DataEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataEntry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton enterButton;
    private javax.swing.JFormattedTextField enter_uuidBox;
    private javax.swing.JRadioButton enter_uuidSel;
    private javax.swing.JRadioButton gen_uuidSel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JComboBox<String> n_name;
    private javax.swing.JTextField n_nameBox;
    private javax.swing.JTextArea nt_desBox;
    private javax.swing.JComboBox<String> nt_descb;
    private javax.swing.JTextField nt_nameBox;
    private javax.swing.JComboBox<String> nt_namecb;
    private javax.swing.JTextArea nta_desBox;
    private javax.swing.JComboBox<String> nta_descb;
    private javax.swing.JTextField nta_nameBox;
    private javax.swing.JComboBox<String> nta_namecb;
    // End of variables declaration//GEN-END:variables
}
