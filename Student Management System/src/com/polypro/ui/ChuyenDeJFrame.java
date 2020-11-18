/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.ui;

import com.polypro.dao.ChuyenDeDAO;
import com.polypro.helper.DialogHelper;
import com.polypro.helper.ShareHelper;
import com.polypro.model.ChuyenDe;
import com.polypro.model.NhanVien;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 
 */
public class ChuyenDeJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NhanVienJFrame
     */
    public ChuyenDeJFrame() {
        initComponents();
        init();
    }

    int index = 0;
    ChuyenDeDAO dao = new ChuyenDeDAO();

    void init() {
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);
    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblGridView.getModel();
        model.setRowCount(0);
        try {
            List<ChuyenDe> list = dao.select();
            for (ChuyenDe cd : list) {
                Object[] row = {
                    cd.getMaCD(),
                    cd.getTenCD(),
                    cd.getHocPhi(),
                    cd.getThoiLuong(),
                    cd.getHinh()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void insert() {
        ChuyenDe model = getModel();
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại!");
        }

    }
    void checkdata(){
        String maCD= "[a-zA-Z0-9_]{5}";
        String tenCD="[a-zA-Z  ]+"; 
        String hocPhi="\\d+";
        String thoiLuong ="\\d+";
         if (txtMaCD.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống ID,vui lòng nhập lại");
        } else if (!txtMaCD.getText().matches(maCD)) {
            JOptionPane.showMessageDialog(this, "ít nhất 5 kí tự");
            txtMaCD.setText("");
            txtMaCD.requestFocus();
        } else if (!txtTenCD.getText().matches(tenCD)) {
            JOptionPane.showMessageDialog(this, "Tên Chuyên Đề Chỉ được nhập chữ");
            txtTenCD.setText("");
            txtTenCD.requestFocus();
//        } else if (!txtThoiLuong.getText().matches(thoiLuong)) {
//            JOptionPane.showMessageDialog(this, "Thời Lượng Chỉ được nhập số");
//            txtThoiLuong.setText("");
//            txtThoiLuong.requestFocus();
//        } else if (Integer.parseInt(txtThoiLuong.getText().toString()) <=0) {
//            JOptionPane.showMessageDialog(this, "Thời lượng phải là số dương");
//            txtThoiLuong.setText("");
//            txtThoiLuong.requestFocus();
        } else if (!txtHocPhi.getText().matches(hocPhi)) {
            JOptionPane.showMessageDialog(this, "Học Phí Chỉ được nhập số");
            txtHocPhi.setText("");
            txtHocPhi.requestFocus();
        } else if (Double.parseDouble(txtHocPhi.getText().toString())<=0) {
            JOptionPane.showMessageDialog(this, "Học phí phải là số dương");
            txtHocPhi.requestFocus();
        } else {
            ChuyenDe model = getModel();
            try {
                dao.insert(model);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "thêm mới thất bại!!!");
            }

        }

        
    
    
    }

    void update() {
        ChuyenDe model = getModel();
        try {
            dao.update(model);
            this.load();
            DialogHelper.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhật thất bại!");
        }

    }

    void delete() {
        if (DialogHelper.confirm(this, "Bạn có muốn xóa hay không?")) {
            String macd = txtMaCD.getText();
            try {
                dao.delete(macd);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    void clear() {
        this.setModel(new ChuyenDe());
        this.setStatus(true);
    }

    void edit() {
        try {
            String macd = (String) tblGridView.getValueAt(this.index, 0);
            ChuyenDe model = dao.findById(macd);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setModel(ChuyenDe model) {
        txtMaCD.setText(model.getMaCD());
        txtTenCD.setText(model.getTenCD());
//        txtThoiLuong.setText(String.valueOf(model.getThoiLuong()));
        cbbox.setSelectedItem(String.valueOf(model.getThoiLuong()));
        txtHocPhi.setText(String.valueOf(model.getHocPhi()));
        txtMoTa.setText(model.getMoTa());
        lblAvt.setToolTipText(model.getHinh());
        if (model.getHinh() != null) {
            lblAvt.setIcon(ShareHelper.readLogo(model.getHinh()));
        }
    }

    ChuyenDe getModel() {
        ChuyenDe model = new ChuyenDe();
        model.setMaCD(txtMaCD.getText());
        model.setTenCD(txtTenCD.getText());
//        model.setThoiLuong(Integer.valueOf(txtThoiLuong.getText()));
        model.setThoiLuong(Integer.valueOf(cbbox.getSelectedItem().toString()));
        model.setHocPhi(Double.valueOf(txtHocPhi.getText()));
        model.setHinh(lblAvt.getToolTipText());
        model.setMoTa(txtMoTa.getText());
        return model;
    }

    void setStatus(boolean insertable) {
        txtMaCD.setEditable(insertable);
        btnInsert.setEnabled(insertable);
        btnUpdate.setEnabled(!insertable);
        btnDelete.setEnabled(!insertable);

        boolean first = this.index > 0;
        boolean last = this.index < tblGridView.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnPrev.setEnabled(!insertable && first);
        btnLast.setEnabled(!insertable && last);
        btnNext.setEnabled(!insertable && last);
    }

    void selectImage() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (ShareHelper.saveLogo(file)) {
                // Hiển thị hình lên form
                lblAvt.setIcon(ShareHelper.readLogo(file.getName()));
                lblAvt.setToolTipText(file.getName());
            }
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

        fileChooser = new javax.swing.JFileChooser();
        lblTitle = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        pnlEdit = new javax.swing.JPanel();
        lblMaCD = new javax.swing.JLabel();
        txtMaCD = new javax.swing.JTextField();
        lblTenCD = new javax.swing.JLabel();
        lblThoiLuong = new javax.swing.JLabel();
        lblHocPhi = new javax.swing.JLabel();
        txtHocPhi = new javax.swing.JTextField();
        lblMoTaCD = new javax.swing.JLabel();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        pnlAvatar = new javax.swing.JPanel();
        lblAvt = new javax.swing.JLabel();
        txtTenCD = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        lblHinhLogo = new javax.swing.JLabel();
        cbbox = new javax.swing.JComboBox<>();
        pnlList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGridView = new javax.swing.JTable();

        fileChooser.setDialogTitle("Chọn Logo chuyên đề");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ CHUYÊN ĐỀ");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 153));
        lblTitle.setText("QUẢN LÝ CHUYÊN ĐỀ");

        lblMaCD.setText("Mã chuyên đề");

        lblTenCD.setText("Tên chuyên đề");

        lblThoiLuong.setText("Thời lượng (giờ)");

        lblHocPhi.setText("Học phí");

        lblMoTaCD.setText("Mô tả chuyên đề");

        btnInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Add.png"))); // NOI18N
        btnInsert.setText("Thêm");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Edit.png"))); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Delete.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Create.png"))); // NOI18N
        btnClear.setText("Mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/First.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Previous.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Next.png"))); // NOI18N
        btnNext.setToolTipText("");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Last.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        pnlAvatar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));

        lblAvt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblAvt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAvtMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlAvatarLayout = new javax.swing.GroupLayout(pnlAvatar);
        pnlAvatar.setLayout(pnlAvatarLayout);
        pnlAvatarLayout.setHorizontalGroup(
            pnlAvatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAvt, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlAvatarLayout.setVerticalGroup(
            pnlAvatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAvt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        lblHinhLogo.setText("Hình ");

        cbbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "80", "100", "120", "160" }));

        javax.swing.GroupLayout pnlEditLayout = new javax.swing.GroupLayout(pnlEdit);
        pnlEdit.setLayout(pnlEditLayout);
        pnlEditLayout.setHorizontalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addComponent(lblMaCD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblHinhLogo)
                                .addGap(61, 61, 61))
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addComponent(lblMoTaCD)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtHocPhi, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                    .addComponent(txtMaCD, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                    .addComponent(lblTenCD)
                                    .addComponent(txtTenCD, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                    .addComponent(lblThoiLuong)
                                    .addComponent(lblHocPhi)
                                    .addComponent(cbbox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear)
                        .addGap(55, 55, 55)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLast)
                .addGap(26, 26, 26))
        );
        pnlEditLayout.setVerticalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaCD)
                    .addComponent(lblHinhLogo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAvatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addComponent(txtMaCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTenCD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblThoiLuong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lblHocPhi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMoTaCD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsert)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnClear)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addContainerGap())
        );

        tabs.addTab("CẬP NHẬT", pnlEdit);

        pnlList.setLayout(new java.awt.BorderLayout());

        tblGridView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ CD", "TÊN CD", "HỌC PHÍ", "THỜI LƯỢNG", "HÌNH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGridView.setRowHeight(25);
        tblGridView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGridViewMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGridView);

        pnlList.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tabs.addTab("DANH SÁCH", pnlList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tabs))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
//        this.insert();
            this.checkdata();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        this.update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        this.index--;
        this.edit();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        this.index++;
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblGridView.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblGridViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGridViewMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tblGridView.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
                tabs.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tblGridViewMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.load();
        this.setStatus(true);
    }//GEN-LAST:event_formWindowOpened

    private void lblAvtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAvtMouseClicked
        // TODO add your handling code here:
        this.selectImage();
    }//GEN-LAST:event_lblAvtMouseClicked

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
            java.util.logging.Logger.getLogger(ChuyenDeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChuyenDeJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbox;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAvt;
    private javax.swing.JLabel lblHinhLogo;
    private javax.swing.JLabel lblHocPhi;
    private javax.swing.JLabel lblMaCD;
    private javax.swing.JLabel lblMoTaCD;
    private javax.swing.JLabel lblTenCD;
    private javax.swing.JLabel lblThoiLuong;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlAvatar;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlList;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblGridView;
    private javax.swing.JTextField txtHocPhi;
    private javax.swing.JTextField txtMaCD;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenCD;
    // End of variables declaration//GEN-END:variables
}
