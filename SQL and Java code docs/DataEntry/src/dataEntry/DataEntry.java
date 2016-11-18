package dataEntry;

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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.HierarchyEvent;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.HierarchyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingUtilities;
/**
 *
 * @author pdlo003
 */
public class DataEntry extends javax.swing.JFrame {
    String host = "jdbc:postgresql://localhost:5432/CIMIdentity";
    String uName = "postgres";
    String uPass; 
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
    public DataEntry(String Pass) {
        uPass = Pass;
        initComponents();
        
    }
    
        //Populate Combobox with previous entries
    public void connect(String column, String table){
        try{
            
           // Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(host, uName, uPass);           
            Statement stmt = con.createStatement();

            String getData = "SELECT DISTINCT " + column + " FROM " + table +
                    " ORDER BY " + column;

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
            JOptionPane.showMessageDialog(null,"Data Entered.");
            System.exit(0);
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        n_nameBox = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        n_name = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nt_nameBox = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        nt_desBox = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        nt_namecb = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        nt_descb = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        nta_nameBox = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        nta_desBox = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        nta_namecb = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        nta_descb = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        gen_uuidSel = new javax.swing.JRadioButton();
        enter_uuidSel = new javax.swing.JRadioButton();
        enter_uuidBox = new javax.swing.JFormattedTextField();
        enterButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Name");

        n_nameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n_nameBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Select Name");

        n_name.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        connect("n_name", "\"Name\"");
        for (int i = 0; i < forCombo.size(); i ++)
        n_name.addItem(forCombo.get(i));
        forCombo.clear();
        n_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n_nameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("NameType");

        jLabel4.setText("Name");

        jLabel5.setText("Description");

        jScrollPane1.setMinimumSize(new java.awt.Dimension(20, 5));
        jScrollPane1.setName(""); // NOI18N

        nt_desBox.setColumns(20);
        nt_desBox.setRows(5);
        jScrollPane1.setViewportView(nt_desBox);

        jLabel6.setText("Select Name");

        connect("nt_name", "\"NameType\"");
        for(int i = 0; i < forCombo.size(); i ++)
        nt_namecb.addItem(forCombo.get(i));
        forCombo.clear();
        nt_namecb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nt_namecbActionPerformed(evt);
            }
        });

        jLabel7.setText("Select Description");

        connect("nt_description", "\"NameType\"");
        for(int i = 0; i < forCombo.size(); i ++)
        nt_descb.addItem(forCombo.get(i));
        forCombo.clear();
        nt_descb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nt_descbActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("NameTypeAuthority");

        jLabel9.setText("Name");

        jLabel10.setText("Description");

        nta_desBox.setColumns(20);
        nta_desBox.setRows(5);
        jScrollPane2.setViewportView(nta_desBox);

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

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("UUID");

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
        enter_uuidBox.setToolTipText("");
        enter_uuidBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_uuidBoxActionPerformed(evt);
            }
        });

        enterButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        enterButton.setText("Enter");
        enterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(n_nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(n_name, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(nt_nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(nt_namecb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(nt_descb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(145, 145, 145))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nta_nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nta_namecb, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(nta_descb, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(enter_uuidSel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enter_uuidBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(gen_uuidSel))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(enterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n_nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nt_nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nt_namecb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nt_descb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nta_nameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nta_namecb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nta_descb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(gen_uuidSel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enter_uuidSel)
                    .addComponent(enter_uuidBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(enterButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void n_nameBoxActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void enterButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
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
    }                                           

    private void gen_uuidSelActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void enter_uuidBoxActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void nt_descbActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        String nameSel = (String)nt_descb.getSelectedItem();
        nt_desBox.setText(nameSel);
    }                                        

    private void nta_namecbActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
        String nameSel = (String)nta_namecb.getSelectedItem();
        nta_nameBox.setText(nameSel);
    }                                          

    private void nt_namecbActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
        String nameSel = (String)nt_namecb.getSelectedItem();
        nt_nameBox.setText(nameSel);
    }                                         

    private void n_nameActionPerformed(java.awt.event.ActionEvent evt) {                                       
      
        String nameSel = (String)n_name.getSelectedItem();
        n_nameBox.setText(nameSel);
    }                                      

    private void nta_descbActionPerformed(java.awt.event.ActionEvent evt) {                                          

        String nameSel = (String)nta_descb.getSelectedItem();
        nta_desBox.setText(nameSel);
    }                                         

    
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
        /* Acquire password to PostgreSQL */
        JPasswordField pwd = new JPasswordField(20);
        pwd.addHierarchyListener(new HierarchyListener() 
        {
            public void hierarchyChanged(HierarchyEvent e) 
                {
                     final Component c = e.getComponent();
                        if (c.isShowing() && (e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) 
                        {
                                Window toplevel = SwingUtilities.getWindowAncestor(c);
                                toplevel.addWindowFocusListener(new WindowAdapter() 
                                {
                                         public void windowGainedFocus(WindowEvent e) 
                                        {
                                            c.requestFocus();
                                        }
                                });
                        }
                }
        });
        int action = JOptionPane.showConfirmDialog(null, pwd,"Enter PostgreSQL Password",JOptionPane.OK_CANCEL_OPTION);
        if (action < 0) {
            JOptionPane.showMessageDialog(null,"Cancel, X or Escape key selected");
            System.exit(0);
        }
        
       String passw = new String(pwd.getPassword());
       
       Connection s = null;
       
       try {

         s = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CIMIdentity", "postgres", passw);
       
         } catch ( Exception e ) {
         JOptionPane.showMessageDialog( null, e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
         }
       
       /* End password acquisition */
       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataEntry(passw).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
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
    // End of variables declaration                   
}
