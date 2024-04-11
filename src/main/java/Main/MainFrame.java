package Main;

import java.io.File;
import static java.lang.System.exit;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;

public class MainFrame extends javax.swing.JFrame {

    JFileChooser fileChooser;
    private final Manager manager;
    private ButtonGroup radioGroup;

    public MainFrame() {
        initComponents();
        fileChooser = new JFileChooser();
        manager = new Manager();
        radioGroup = new ButtonGroup();
        radioGroup.add(radioIndex);
        radioGroup.add(radioName);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        loadButton = new javax.swing.JButton();
        calculateButton = new javax.swing.JButton();
        exportButton = new javax.swing.JButton();
        showButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        radioIndex = new javax.swing.JRadioButton();
        radioName = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loadButton.setText("Загрузить файл");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        calculateButton.setText("Выполнить расчеты");
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateButtonActionPerformed(evt);
            }
        });

        exportButton.setText("Выгрузка результатов");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        showButton.setText("Вывести результаты");
        showButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Выход");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        radioIndex.setText("По индексу листа");
        radioIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioIndexActionPerformed(evt);
            }
        });

        radioName.setText("По имени листа");
        radioName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(showButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calculateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(exportButton)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(loadButton)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioName)
                                    .addComponent(radioIndex))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radioIndex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radioName)))
                .addGap(18, 18, 18)
                .addComponent(calculateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(exportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateButtonActionPerformed
        manager.performCalculations();
    }//GEN-LAST:event_calculateButtonActionPerformed

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            // Ensure the file extension is .xlsx
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }
            manager.exportToExcel(filePath);
        } else {
            System.out.println("Отменено");
        }

    }//GEN-LAST:event_exportButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String selectedFilePath = selectedFile.getAbsolutePath();
            if (radioIndex.isSelected()) {
                String input = javax.swing.JOptionPane.showInputDialog("Введите индекс листа:");
                int sheetIndex = Integer.parseInt(input);
                manager.loadExcelSheetByIndex(selectedFilePath, sheetIndex);
                System.out.println("Выбранный файл: " + selectedFilePath + ", индекс листа: " + sheetIndex);
            }
            if (radioName.isSelected()) {
                String sheetName = javax.swing.JOptionPane.showInputDialog("Введите имя листа:");
                manager.loadExcelSheetByName(selectedFilePath, sheetName);
                System.out.println("Выбранный файл: " + selectedFilePath + ", имя листа: " + sheetName);
            }
        } else {
            System.out.println("Отменено");
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void showButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showButtonActionPerformed
        manager.showResultsFile();
    }//GEN-LAST:event_showButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void radioIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioIndexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioIndexActionPerformed

    private void radioNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioNameActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton calculateButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton exportButton;
    private javax.swing.JButton loadButton;
    private javax.swing.JRadioButton radioIndex;
    private javax.swing.JRadioButton radioName;
    private javax.swing.JButton showButton;
    // End of variables declaration//GEN-END:variables
}
