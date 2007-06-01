/*
 * TeacherToolWindow.java
 *
 * Created on January 13, 2007, 3:27 PM
 */

package lambdacalc.gui;

import java.io.*;
import java.util.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.*;
import lambdacalc.exercises.*;

/**
 *
 * @author  tauberer
 */
public class TeacherToolWindow extends javax.swing.JFrame {
    private static TeacherToolWindow singleton=null;
    
    static TeacherToolWindow getSingleton() {
        return singleton;
    }
    File directory;
    
    Vector assignmentList = new Vector();
    Vector exercises = new Vector();
    String tableAsString = "";
    
    public static void showWindow() {
    
        if (singleton == null) {
            singleton = new TeacherToolWindow();
            
            // Ask the user to open a directory immediately,
            // but if user cancels, don't bother showing the window.
            if (!singleton.onOpen()) {
                singleton = null;
                return;
            }
        }
        singleton.show();
    }
    
    static void exit() {
        disposeWindow();
    }
    
    
    public static void disposeWindow() {
        if (singleton != null)
            singleton.dispose();
    }
    
    /** Creates new form TeacherToolWindow. Private so that showWindow is used instead. */
    private TeacherToolWindow() {
        initComponents();
        
        TrainingWindow.initializeJFileChooser(fileChooser, false, true);
        
        fileTable.getSelectionModel().addListSelectionListener(new SelectionListener());
        fileTable.getColumnModel().getSelectionModel().addListSelectionListener(new SelectionListener());
        
        jTextTeacherComments.getDocument().addDocumentListener(new DocumentListener());
        
        setExtendedState(MAXIMIZED_BOTH);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        fileChooser = new javax.swing.JFileChooser();
        jLabelCurrentDirectory = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextTeacherComments = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fileTable = new javax.swing.JTable();
        statsField = new javax.swing.JTextField();
        jListAssignments = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        menuItemOpen = new javax.swing.JMenuItem();
        menuItemClose = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        menuItemCopyTable = new javax.swing.JMenuItem();

        fileChooser.setApproveButtonText("Select Directory");
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        setTitle("Lambda Teacher Tool");
        jLabelCurrentDirectory.setText("Current Directory");

        textArea.setColumns(20);
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Serif", 0, 14));
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(textArea);

        jLabel1.setText("Teacher comments:");

        jTextTeacherComments.setColumns(20);
        jTextTeacherComments.setRows(5);
        jScrollPane3.setViewportView(jTextTeacherComments);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jLabel1)
                .addContainerGap())
            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jSplitPane1.setRightComponent(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        fileTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(fileTable);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        statsField.setEditable(false);
        statsField.setText(".... statistics ...");
        jPanel3.add(statsField, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setLeftComponent(jPanel3);

        jListAssignments.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jListAssignments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jListAssignmentsActionPerformed(evt);
            }
        });

        jMenuFile.setMnemonic('F');
        jMenuFile.setText("File");
        menuItemOpen.setMnemonic('O');
        menuItemOpen.setText("Open Directory...");
        menuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenActionPerformed(evt);
            }
        });

        jMenuFile.add(menuItemOpen);

        menuItemClose.setMnemonic('c');
        menuItemClose.setText("Close Window");
        menuItemClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCloseActionPerformed(evt);
            }
        });

        jMenuFile.add(menuItemClose);

        jMenuBar1.add(jMenuFile);

        jMenuEdit.setMnemonic('e');
        jMenuEdit.setText("Edit");
        jMenuEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEditActionPerformed(evt);
            }
        });

        menuItemCopyTable.setMnemonic('c');
        menuItemCopyTable.setText("Copy Table");
        menuItemCopyTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCopyTableActionPerformed(evt);
            }
        });

        jMenuEdit.add(menuItemCopyTable);

        jMenuBar1.add(jMenuEdit);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(jLabelCurrentDirectory, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                        .add(92, 92, 92))
                    .add(layout.createSequentialGroup()
                        .add(jListAssignments, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(617, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelCurrentDirectory)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jListAssignments, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemCopyTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCopyTableActionPerformed
        copyTable();
    }//GEN-LAST:event_menuItemCopyTableActionPerformed

    private void jMenuEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuEditActionPerformed
    
    private void jListAssignmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jListAssignmentsActionPerformed
        showExercises();
        clearTeacherComments();
    }//GEN-LAST:event_jListAssignmentsActionPerformed
    
    private void menuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemOpenActionPerformed
        onOpen();
    }
    
    private boolean onOpen() {
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == fileChooser.APPROVE_OPTION) {
            directory = fileChooser.getSelectedFile();
            jLabelCurrentDirectory.setText("Browsing: " + directory.getAbsolutePath());
            scanForAssignments();
            jListAssignments.setSelectedIndex(0); // there's always an entry for All Homeworks
            return true;
        }
        return false;
    }//GEN-LAST:event_menuItemOpenActionPerformed
    
    private void menuItemCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCloseActionPerformed
        this.hide();
    }//GEN-LAST:event_menuItemCloseActionPerformed
    
    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherToolWindow().setVisible(true);
            }
        });
    }*/
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JTable fileTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelCurrentDirectory;
    private javax.swing.JComboBox jListAssignments;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextArea jTextTeacherComments;
    private javax.swing.JMenuItem menuItemClose;
    private javax.swing.JMenuItem menuItemCopyTable;
    private javax.swing.JMenuItem menuItemOpen;
    private javax.swing.JTextField statsField;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
    
    
    // This gets a unique list of the titles of the
    // various exercise files in the directory, so that
    // we can display one assignment (i.e. class of homeworks)
    // at a time.
    private void scanForAssignments() {
        assignmentList.clear();
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (TrainingWindow.isSerialized(files[i])) {
                try {
                    ExerciseFile ex = new ExerciseFile(files[i]);
                    if (!assignmentList.contains(ex.getTitle()))
                        assignmentList.add(ex.getTitle());
                } catch (Exception e) {
                }
            }
        }
        Collections.sort(assignmentList);
        
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("All Assignments");
        for (int i = 0; i < assignmentList.size(); i++)
            model.addElement(assignmentList.get(i));
        
        jListAssignments.setModel(model);
    }
    
    private class ReadOnlyTableModel extends DefaultTableModel {
        public boolean isCellEditable(int x, int y) {
            return false;
        }
    }
    
    private class ExerciseFileListEntry implements Comparable {
        public File file;
        public ExerciseFile exFile;
        public String errorMessage;
        public int compareTo(Object other) {
            ExerciseFileListEntry o = (ExerciseFileListEntry)other;
            
            // put unopenable files first
            if (exFile == null && o.exFile != null) return -1;
            if (exFile != null && o.exFile == null) return 1;
            
            // sort by assignment name
            if (exFile != null) {
                int c = exFile.getTitle().compareTo(o.exFile.getTitle());
                if (c != 0)
                    return c;
            }
            
            // sort by student name, or if unopenable then file name
            return getSortKey().compareTo(o.getSortKey());
        }
        String getSortKey() {
            if (exFile == null)
                return file.getName();
            else if (exFile.getStudentName() == null)
                return "[Unknown]";
            else
                return exFile.getStudentName();
        }
    }
    
    private void showExercises() {
        // Load the student's work.
        
        // First load in the files so that we can sort them
        // ourself.
        exercises.clear();
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (TrainingWindow.isSerialized(files[i])) {
                Object[] row = new Object[6];
                
                ExerciseFileListEntry entry = new ExerciseFileListEntry();
                entry.file = files[i];
                
                try {
                    ExerciseFile ex = new ExerciseFile(files[i]);
                    
                    // If jListAssignments index 0 is selected, view all homeworks.
                    // Otherwise, filter out homeworks that don't match title in jListAssignments.
                    if (jListAssignments.getSelectedIndex() != 0
                            && !ex.getTitle().equals(jListAssignments.getSelectedItem()))
                        continue;
                    
                    entry.exFile = ex;
                    
                } catch (Exception e) {
                    entry.errorMessage = e.getMessage();
                }
                
                exercises.add(entry);
            }
        }
        
        Collections.sort(exercises);
        
        // Now put the exercises into a table model
        
        DefaultTableModel model = new ReadOnlyTableModel();
        
        if (jListAssignments.getSelectedIndex() == 0)
            model.addColumn("Assignment");
        model.addColumn("Student");
        model.addColumn("Score");
        model.addColumn("File Name");
        
        tableAsString = ""; // for copying to clipboard, easier to create now
        
        ArrayList scores = new ArrayList();
        
        for (int i = 0; i < exercises.size(); i++) {
            ExerciseFileListEntry entry = (ExerciseFileListEntry)exercises.get(i);
            
            Object[] row = new Object[model.getColumnCount()];
            
            int col = 0;
            
            if (entry.exFile != null) {
                if (jListAssignments.getSelectedIndex() == 0)
                    row[col++] = entry.exFile.getTitle();
                row[col++] = entry.exFile.getStudentName();
                row[col++] = entry.exFile.getPointsCorrect();
                    //+ "/" + entry.exFile.getTotalPointsAvailable();
                
                scores.add(entry.exFile.getPointsCorrect());
            } else {
                if (jListAssignments.getSelectedIndex() == 0)
                    row[col++] = "Unknown";
                row[col++] = "Error Loading File";
                row[col++] = "";
            }
            
            row[col++] = entry.file.getName();
            
            model.addRow(row);
            
            for (int j = 0; j < row.length; j++) {
                if (j > 0)
                    tableAsString = tableAsString + "\t";
                tableAsString = tableAsString + row[j];
            }
            tableAsString = tableAsString + "\n";
        }
        
        fileTable.setModel(model);
        
        textArea.setText(createAssignmentSummary());
        
        // Compute statistics.
        if (scores.size() == 0)
            statsField.setText("No student files found in this directory.");
        else
            statsField.setText("students: " + scores.size() + "; mean: " + formatDouble(mean(scores)) + "; std-dev: " + formatDouble(stdev(scores)) + "; median: " + formatDouble(median(scores)));
    }
    
    String formatDouble(double d) {
        return "" + Math.round(d * 10.0)/10.0; // round double to one decimal place
    }
    
    double mean(ArrayList scores) {
        double total = 0;
        for (Iterator i = scores.iterator(); i.hasNext(); )
            total += ((java.math.BigDecimal)i.next()).doubleValue();
        return total / (double)scores.size();
    }
    
    double stdev(ArrayList scores) {
        double mean = mean(scores);
        
        double total = 0;
        for (Iterator i = scores.iterator(); i.hasNext(); ) {
            double v = ((java.math.BigDecimal)i.next()).doubleValue() - mean;
            total += v*v;
        }
        return Math.sqrt(total / ((double)scores.size()-1));
    }
    
    double median(ArrayList scores) {
        ArrayList sortedScores = (ArrayList)scores.clone();
        Collections.sort(sortedScores);
        if (scores.size() % 2 == 1) {
            return ((java.math.BigDecimal)sortedScores.get((scores.size()-1) / 2)).doubleValue();
        } else {
            return (((java.math.BigDecimal)sortedScores.get(scores.size() / 2 - 1)).doubleValue() + ((java.math.BigDecimal)sortedScores.get(scores.size() / 2)).doubleValue())/2.0;
        }
    }
    
    class SelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (fileTable.getSelectedRow() == -1) { textArea.setText(createAssignmentSummary()); return; }
            ExerciseFileListEntry entry = (ExerciseFileListEntry)exercises.get(fileTable.getSelectedRow());
            showFileDetails(entry.file);
        }
    }
    
    class DocumentListener implements javax.swing.event.DocumentListener {
        public void insertUpdate(javax.swing.event.DocumentEvent e) { onChange(); }
        public void removeUpdate(javax.swing.event.DocumentEvent e) { onChange(); }
        public void changedUpdate(javax.swing.event.DocumentEvent e) { onChange(); }
        
        void onChange() {
            if (fileTable.getSelectedRow() == -1) return;
            ExerciseFileListEntry entry = (ExerciseFileListEntry)exercises.get(fileTable.getSelectedRow());
            if (entry.exFile == null) return;
            entry.exFile.setTeacherComments(jTextTeacherComments.getText());
            try {
                entry.exFile.saveTo(entry.file);
            } catch (IOException e) {
                javax.swing.JOptionPane.showMessageDialog
                        (TeacherToolWindow.this, "Could not save comments to exercise file: " + (e.getMessage() == null ? "Unknown write error." : e.getMessage()),
                        "Error saving comments", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void showFileDetails(File file) {
        try {
            ExerciseFile exfile = new ExerciseFile(file);
            
            String text = "";
            text += exfile.getTitle() + "\n";
            text += "\n";
            text += "Student: " + exfile.getStudentName() + "\n";
            text += "\n";
            
            text += "Points: " + exfile.getPointsCorrect() + " (out of " + exfile.getTotalPointsAvailable() + ")\n";
            
            text += "\n";
            
            for (int i = 0; i < exfile.size(); i++) {
                ExerciseGroup g = exfile.getGroup(i);
                
                text += (char)('A' + i) + ". " + g.getTitle() + "\n";
                text += "\n";
                
                java.math.BigDecimal groupPointsTotal = java.math.BigDecimal.valueOf(0);
                java.math.BigDecimal groupPointsCorrect = java.math.BigDecimal.valueOf(0);
                
                for (int j = 0; j < g.size(); j++) {
                    Exercise ex = g.getItem(j);
                    
                    text += "   ";
                    
                    if (!ex.isDone())
                        text += ExerciseTreeModel.BALLOT_EX;
                    else
                        text += ExerciseTreeModel.CHECKMARK;
                    
                    text += "   " + (j+1) + ".  ";
                    
                    text += ex.getExerciseText();
                    text += "\t";
                    
                    if (ex.hasBeenStarted() && !ex.isDone())
                        text += "Student's Last Answer: " + ex.getLastAnswer();
                    
                    text += " [" + ex.getPoints() + " pt]";
                    
                    text += "\n";
                    
                    groupPointsTotal = groupPointsTotal.add(ex.getPoints());
                    if (ex.isDone())
                        groupPointsCorrect = groupPointsCorrect.add(ex.getPoints());
                }
                
                text += "\n";
                text += "   " + groupPointsCorrect + "/" + groupPointsTotal + " points in this section.\n";
                text += "\n";
            }
            
            textArea.setText(text);
            
            jTextTeacherComments.setText(exfile.getTeacherComments());
            jTextTeacherComments.setEditable(true);
            
        } catch (ExerciseFileVersionException e) {
            String text = "The file " + file.getPath() + " could not be opened: " + e.getMessage();
            textArea.setText(text);
        } catch (ExerciseFileFormatException e) {
            String text = "The file " + file.getPath() + " could not be opened.  It looks like this isn't a student work file for this program, or a bug in the program prevented the file from being read: " + e.toString();
            textArea.setText(text);
        } catch (IOException e) {
            String text = "The file " + file.getPath() + " could not be opened: " + e.toString();
            textArea.setText(text);
        }
    }
    
    String createAssignmentSummary() {
        // This creates a summary of how many students got each question right.
        
        // Which assignments to show statistics for?
        Vector alist;
        if (jListAssignments.getSelectedIndex() == 0) {
            alist = assignmentList;
        } else {
            alist = new Vector();
            alist.add(jListAssignments.getSelectedItem());
        }
        
        String text = "Click on an exercise in the table to the left to view the student's details.\n\nAssignment Summary:\n\nEach line reports the number of students who got each question right and wrong.\n\n";
        
        // For each assignment...
        for (int a = 0; a < alist.size(); a++) {
            // Use one student's exercise as a template. Find one student for this assignment.
            
            ExerciseFile extempl = null;
            for (int i = 0; i < exercises.size(); i++) {
                ExerciseFile exf = ((ExerciseFileListEntry)exercises.get(i)).exFile;
                if (exf == null || !exf.getTitle().equals(alist.get(a)))
                    continue;
                extempl = exf;
            }
            if (extempl == null) // couldn't find any files for this assignment, strangely
                continue;
            
            text += extempl.getTitle() + "\n\n";
            
            // Compute stats for this homework.
            ArrayList scores = new ArrayList();
            for (int i = 0; i < exercises.size(); i++) {
                ExerciseFile exf = ((ExerciseFileListEntry)exercises.get(i)).exFile;
                if (exf == null || !exf.getTitle().equals(alist.get(a)))
                    continue;
                scores.add(exf.getPointsCorrect());
            }
            text += "Mean score: " + formatDouble(mean(scores)) + "; Std-dev: " + formatDouble(stdev(scores)) + "; Median: " + formatDouble(median(scores)) + "\n\n";
            
            for (int g = 0; g < extempl.size(); g++) {
                ExerciseGroup group = extempl.getGroup(g);
                
                text += (char)('A' + g) + ". " + group.getTitle() + "\n";
                text += "\n";
                
                for (int j = 0; j < group.size(); j++) {
                    Exercise ex = group.getItem(j);
                    
                    text += "   ";
                    
                    // Loop through each student's work
                    int correct = 0, incorrect = 0;
                    for (int i = 0; i < exercises.size(); i++) {
                        ExerciseFile exf = ((ExerciseFileListEntry)exercises.get(i)).exFile;
                        if (exf == null || !exf.getTitle().equals(alist.get(a)))
                            continue;
                        
                        ExerciseGroup gg = exf.getGroup(g);
                        Exercise ee = gg.getItem(j);
                        
                        if (ee.isDone())
                            correct++;
                        else
                            incorrect++;
                    }
                    
                    text += correct + " right";
                    text += "/";
                    text += incorrect + " wrong";
                    text += ": ";
                    text += ex.getExerciseText();
                    text += "\n";
                }
                
                text += "\n";
            }
        }
        
        return text;
    }
    
    private void clearTeacherComments() {
        jTextTeacherComments.setText("");
        jTextTeacherComments.setEditable(false);
    }
    
    public void copyTable() {
        java.awt.datatransfer.StringSelection ss
                = new java.awt.datatransfer.StringSelection(tableAsString);
        try {
            java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, ss);
        } catch (Exception e) {
            // if copying to clipboard is not supported at this time
        }
    }
}
