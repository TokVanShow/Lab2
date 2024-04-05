package Main;

import calculations.*;
import Excel.ExcelExport;
import Excel.ExcelReader;
import Excel.Storage;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;


public class MainFrame extends javax.swing.JFrame {

    Storage storage = new Storage();

    public MainFrame() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadButton = new javax.swing.JButton();
        calculateButton = new javax.swing.JButton();
        exportButton = new javax.swing.JButton();
        showButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loadButton)
                            .addComponent(exportButton)
                            .addComponent(showButton))
                        .addContainerGap(242, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(calculateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(calculateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(exportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(showButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateButtonActionPerformed

        Stat_Calc[] calculators = {
            new Geometric_Mean_Calculator(),
            new Arithmetic_Mean_Calculator(),
            new Standard_Deviation_Calculator(),
            new Range_Calculator(),
            new Elements_Count_Calculator(),
            new Variation_Calculator(),
            new Confidence_Interval_Calculator(),
            new Variance_Calculator(),
            new Extremum_Calculator(), //new Covariance_Calculator()
        };

        for (Stat_Calc calculator : calculators) {
            storage.setStatCalc(calculator);
            storage.performCalculations();
        }
    }//GEN-LAST:event_calculateButtonActionPerformed

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        String filePath = "src\\main\\java\\Excel\\Results.xlsx";
        ExcelExport excelExport = new ExcelExport(storage);  // Создание экземпляра ExcelExport
        excelExport.exportToExcel(filePath);  // Вызов метода exportToExcel через созданный экземпляр

    }//GEN-LAST:event_exportButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        ExcelReader excelReader = new ExcelReader("src\\main\\java\\Excel\\ДЗ4.xlsx");
        excelReader.run(storage);

    }//GEN-LAST:event_loadButtonActionPerformed

    private void showButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showButtonActionPerformed
                                         
    try {
        File file = new File("src\\main\\java\\Excel\\Results.xlsx");
        if (file.exists()) {
            Desktop.getDesktop().open(file);
        } else {
            System.out.println("Файл не найден");
        }
    } catch (IOException e) {
        System.out.println("Ошибка при открытии файла: " + e.getMessage());
    } catch (IllegalArgumentException e) {
        System.out.println("Некорректный аргумент: " + e.getMessage());
    } catch (UnsupportedOperationException e) {
        System.out.println("Операция не поддерживается: " + e.getMessage());
    }
    }//GEN-LAST:event_showButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calculateButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton exportButton;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton showButton;
    // End of variables declaration//GEN-END:variables
}
