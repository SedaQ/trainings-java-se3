package com.trainings.swing.view;

import com.trainings.swing.controller.PersonsController;
import com.trainings.swing.dto.PersonDto;
import com.trainings.swing.model.PersonModel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.table.*;

/**
 *
 * @author Pavel Seda
 */
public class App extends javax.swing.JFrame {

    private PersonsController personsController;
    private SwingWorker worker;
    private TableRowSorter<TableModel> sorter;

    /**
     * Creates new form MainFrame
     */
    public App() {
        initComponents();
        initComponentsWithOwnProperties();

        initControllers();
        initListeners();
    }

    private void initControllers() {
        PersonModel personModel = new PersonModel();
        personsController = new PersonsController(personModel);
    }

    private void initComponentsWithOwnProperties() {
        sorter = new TableRowSorter<>(personsTable.getModel());
        personsTable.setRowSorter(sorter);

    }

    private void initListeners() {
        personsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = 0;
                    String personId = target.getModel().getValueAt(row, column).toString().trim();

                    PersonEditFrame pef = new PersonEditFrame(Integer.parseInt(personId), personsController);
                    pef.setVisible(true);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        personsTab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        personsTable = new javax.swing.JTable();
        loadAllPersons = new javax.swing.JButton();
        filterTable = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        personsLoadProgressBar = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuClearTable = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemContactViaEmail = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        personsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "fist name", "last name", "nick name"
            }
        ));
        personsTable.setFocusable(false);
        personsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                personsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(personsTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addContainerGap())
        );

        personsTab.addTab("Persons", jPanel1);

        loadAllPersons.setText("Load Persons");
        loadAllPersons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadAllPersonsActionPerformed(evt);
            }
        });

        filterTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTableActionPerformed(evt);
            }
        });
        filterTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                filterTableKeyPressed(evt);
            }
        });

        jLabel1.setText("Filter Persons");

        jLabel2.setText("Progress Bar");

        jMenuFile.setText("File");

        jMenuClearTable.setText("Clear Table");
        jMenuClearTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuClearTableActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuClearTable);

        jMenuItem1.setText("Close");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItem1);

        jMenuBar1.add(jMenuFile);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu1.setText("Contact Us");

        jMenuItemContactViaEmail.setText("Email");
        jMenuItemContactViaEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContactViaEmailActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemContactViaEmail);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(personsTab)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(personsLoadProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filterTable, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loadAllPersons)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(personsTab, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(loadAllPersons))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(personsLoadProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadAllPersonsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadAllPersonsActionPerformed
        this.worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                List<PersonDto> persons = personsController.findAllPersons();
                personsLoadProgressBar.setMaximum(persons.size() - 1); // odpovida initTemperature 10000.0 a coolingRate 0.999
                personsLoadProgressBar.setValue(0);
                personsLoadProgressBar.setForeground(Color.black);
                personsLoadProgressBar.setBackground(Color.green);
                personsLoadProgressBar.setStringPainted(true);

                DefaultTableModel tableModel = (DefaultTableModel) personsTable.getModel();
                tableModel.setRowCount(0); // removes rows in the table
                for (int i = 0; i < persons.size(); i++) {
                    tableModel.addRow(new Object[]{persons.get(i).getId(), persons.get(i).getFirstName(), persons.get(i).getLastName(), persons.get(i).getNickname()});
                    personsLoadProgressBar.setValue(i);
                }
                return "";
            }
        };
        worker.execute();
    }//GEN-LAST:event_loadAllPersonsActionPerformed

    private void jMenuClearTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuClearTableActionPerformed
        DefaultTableModel tableModel = (DefaultTableModel) personsTable.getModel();
        tableModel.setRowCount(0); // removes rows in the table
        personsLoadProgressBar.setValue(0);
    }//GEN-LAST:event_jMenuClearTableActionPerformed

    private void filterTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterTableActionPerformed
    }//GEN-LAST:event_filterTableActionPerformed

    private void filterTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filterTableKeyPressed
        RowFilter<TableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filterTable.getText(), 0, 1, 2, 3);
        } catch (java.util.regex.PatternSyntaxException e) {
            System.out.println(e);
            return;
        }
        sorter.setRowFilter(rf);
    }//GEN-LAST:event_filterTableKeyPressed

    private void jMenuItemContactViaEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContactViaEmailActionPerformed
        EmailFrame eF = new EmailFrame();
        eF.setVisible(true);
    }//GEN-LAST:event_jMenuItemContactViaEmailActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void personsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_personsTableMouseClicked
        ListSelectionModel lsm = personsTable.getSelectionModel();
    }//GEN-LAST:event_personsTableMouseClicked

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
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField filterTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuClearTable;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemContactViaEmail;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadAllPersons;
    private javax.swing.JProgressBar personsLoadProgressBar;
    private javax.swing.JTabbedPane personsTab;
    private javax.swing.JTable personsTable;
    // End of variables declaration//GEN-END:variables
}

class TextFrame extends JFrame {

    public TextFrame(String content) {
        super("TextFrame");

        JTextArea ta = new JTextArea();
        ta.setText(content);
        getContentPane().add(ta);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setSize(200, 100);
    }
}
