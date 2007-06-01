/*
 * WelcomeWindow.java
 *
 * Created on May 31, 2007, 1:47 PM
 */

package lambdacalc.gui;

import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;

/**
 *
 * @author  champoll
 */
public class WelcomeWindow extends javax.swing.JFrame {
    
    private static WelcomeWindow singleton=null;

    static WelcomeWindow getSingleton() {
        return singleton;
    }    
    
    
    /** Creates new form WelcomeWindow. Private so that showWindow is used instead. */
    private WelcomeWindow() {
        TrainingWindow.prepareWindow();
        ScratchPadWindow.prepareWindow();
        // we don't prepare the TeacherToolWindow because it's not
        // likely to be used often and because its startup is conditioned on
        // the user answering positively to a JFileChooser
        
        initComponents();
    }
    
    public static void prepareWindow() {
       if (singleton == null) {
            singleton = new WelcomeWindow();
       }
    }
        
    public static void showWindow() {
        
        prepareWindow();
        singleton.show();
    }
    
    public static void exit() {
        
        disposeWindow();
        
    }
    
    static void disposeWindow() {
        if (singleton != null)
            singleton.dispose();
    }    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuMenu = new javax.swing.JMenu();
        menuItemExercise = new javax.swing.JMenuItem();
        menuItemScratchPad = new javax.swing.JMenuItem();
        menuItemTeacherTool = new javax.swing.JMenuItem();
        menuItemTrees = new javax.swing.JMenuItem();
        menuItemExit = new javax.swing.JMenuItem();
        btnExercise = new javax.swing.JButton();
        btnScratchPad = new javax.swing.JButton();
        btnTeacherTool = new javax.swing.JButton();
        btnTrees = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jMenuMenu.setMnemonic('m');
        jMenuMenu.setText("Menu");
        jMenuMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuMenuActionPerformed(evt);
            }
        });

        menuItemExercise.setMnemonic('e');
        menuItemExercise.setText("Work on an Exercise");
        menuItemExercise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExerciseActionPerformed(evt);
            }
        });

        jMenuMenu.add(menuItemExercise);

        menuItemScratchPad.setMnemonic('s');
        menuItemScratchPad.setText("Use the Scratch Pad");
        menuItemScratchPad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemScratchPadActionPerformed(evt);
            }
        });

        jMenuMenu.add(menuItemScratchPad);

        menuItemTeacherTool.setMnemonic('t');
        menuItemTeacherTool.setText("Use the Teacher Tool");
        menuItemTeacherTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemTeacherToolActionPerformed(evt);
            }
        });

        jMenuMenu.add(menuItemTeacherTool);

        menuItemTrees.setMnemonic('r');
        menuItemTrees.setText("Show Trees");
        menuItemTrees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemTreesActionPerformed(evt);
            }
        });

        jMenuMenu.add(menuItemTrees);

        menuItemExit.setMnemonic('x');
        menuItemExit.setText("Exit Program");
        menuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExitActionPerformed(evt);
            }
        });

        jMenuMenu.add(menuItemExit);

        jMenuBar1.add(jMenuMenu);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lambda");
        btnExercise.setText("Use the Interactive Exercise Solver");
        btnExercise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExerciseActionPerformed(evt);
            }
        });

        btnScratchPad.setText("Use the Scratch Pad");
        btnScratchPad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScratchPadActionPerformed(evt);
            }
        });

        btnTeacherTool.setText("Use the Teacher Tool");
        btnTeacherTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTeacherToolActionPerformed(evt);
            }
        });

        btnTrees.setText("MYSTERY BUTTON: DO NOT CLICK");

        btnExit.setText("Exit Program");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Welcome to the Lambda Calculator");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, btnScratchPad, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, btnExercise, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, btnTeacherTool, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, btnTrees, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                    .add(btnExit))
                .addContainerGap())
            .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnExercise, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnScratchPad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnTeacherTool, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnTrees, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnExit, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        TrainingWindow.exit();
        ScratchPadWindow.exit();
        TeacherToolWindow.exit();
        
        exit();
        
    }//GEN-LAST:event_btnExitActionPerformed

    private void menuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExitActionPerformed
        btnExit.doClick();
    }//GEN-LAST:event_menuItemExitActionPerformed

    private void menuItemTreesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemTreesActionPerformed
        btnTrees.doClick();
    }//GEN-LAST:event_menuItemTreesActionPerformed

    private void menuItemScratchPadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemScratchPadActionPerformed
        btnScratchPad.doClick();
    }//GEN-LAST:event_menuItemScratchPadActionPerformed

    private void menuItemExerciseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExerciseActionPerformed
        btnExercise.doClick();
    }//GEN-LAST:event_menuItemExerciseActionPerformed

    private void btnTeacherToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTeacherToolActionPerformed
        TeacherToolWindow.showWindow();
    }//GEN-LAST:event_btnTeacherToolActionPerformed

    private void btnScratchPadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScratchPadActionPerformed
        ScratchPadWindow.showWindow();
    }//GEN-LAST:event_btnScratchPadActionPerformed

    private void menuItemTeacherToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemTeacherToolActionPerformed
        btnTeacherTool.doClick();
    }//GEN-LAST:event_menuItemTeacherToolActionPerformed

    private void jMenuMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuMenuActionPerformed
    }//GEN-LAST:event_jMenuMenuActionPerformed

    private void btnExerciseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExerciseActionPerformed
        TrainingWindow.showWindow();
    }//GEN-LAST:event_btnExerciseActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomeWindow().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExercise;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnScratchPad;
    private javax.swing.JButton btnTeacherTool;
    private javax.swing.JButton btnTrees;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuMenu;
    private javax.swing.JMenuItem menuItemExercise;
    private javax.swing.JMenuItem menuItemExit;
    private javax.swing.JMenuItem menuItemScratchPad;
    private javax.swing.JMenuItem menuItemTeacherTool;
    private javax.swing.JMenuItem menuItemTrees;
    // End of variables declaration//GEN-END:variables
    
}
