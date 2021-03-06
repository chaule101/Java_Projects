/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.ui;

import com.polypro.helper.DialogHelper;
import com.polypro.helper.ShareHelper;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author Chau Le
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        init();
    }

    void init() {
        setSize(1000, 600);
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);

        new Timer(1000, new ActionListener() {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");

            @Override
            public void actionPerformed(ActionEvent e) {
                lblDongHo.setText(format.format(new Date()));
            }
        }).start();

        this.openWelcome();
        this.openLogin();
    }

    void openWelcome() {
        new ChaoJDialog(this, true).setVisible(true);
    }

    void openLogin() {
        new DangNhapJDialog(this, true).setVisible(true);
    }

    void logoff() {
        ShareHelper.logoff();
        this.openLogin();
    }

    void exit() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn kết thúc?")) {
            System.exit(0);
        }
    }

    void openThongKe(int index) {
        if (ShareHelper.authenticated() && ShareHelper.USER.getVaiTro()) {
            new ThongKeJFrame(index).setVisible(true);
        } else {
            DialogHelper.alert(this, "Bạn không được phân quyền truy cập!");
        }
    }

    void openNhanVien() {
        if (ShareHelper.authenticated()) {
            new NhanVienJFrame().setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập!");
        }
    }

    void openKhoaHoc() {
        if (ShareHelper.authenticated()) {
            new KhoaHocJFrame().setVisible(true);
        } else {
            DialogHelper.alert(this, "Bạn không được phân quyền truy cập!");
        }
    }

    void openChuyenDe() {
        if (ShareHelper.authenticated()) {
            new ChuyenDeJFrame().setVisible(true);
        } else {
            DialogHelper.alert(this, "Bạn không được phân quyền truy cập!");
        }
    }

    void openNguoiHoc() {
        if (ShareHelper.authenticated()) {
            new NguoiHocJFrame().setVisible(true);
        } else {
            DialogHelper.alert(this, "Bạn không được phân quyền truy cập!");
        }
    }

//    void openAbout() {
//        new GioiThieuJDialog(this, true).setVisible(true);
//    }

    void openWebsite() {
        try {
            Desktop.getDesktop().browse(new File("help/index.html").toURI());
        } catch (IOException ex) {
            DialogHelper.alert(this, "Không tìm thấy file hướng dẫn!");
        }
    }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        btnDangXuat = new javax.swing.JButton();
        btnKetThuc = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnChuyenDe = new javax.swing.JButton();
        btnNguoiHoc = new javax.swing.JButton();
        btnKhoaHoc = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnHuongDan = new javax.swing.JButton();
        javax.swing.JPanel pnlNoiDung = new javax.swing.JPanel();
        lblNoiDung = new javax.swing.JLabel();
        javax.swing.JPanel pnlTrangThai = new javax.swing.JPanel();
        lblDongHo = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuHeThong = new javax.swing.JMenu();
        mniDangNhap = new javax.swing.JMenuItem();
        mniDangXuat = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mniDoiMatKhau = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mniKetThuc = new javax.swing.JMenuItem();
        mnuQuanLy = new javax.swing.JMenu();
        mniQLNguoiHoc = new javax.swing.JMenuItem();
        mniQLChuyenDe = new javax.swing.JMenuItem();
        mniQLKhoaHoc = new javax.swing.JMenuItem();
        mniQLNhanVien = new javax.swing.JMenuItem();
        mnuThongKe = new javax.swing.JMenu();
        mniTKNguoiHoc = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mniTKBangDiem = new javax.swing.JMenuItem();
        mniTKKhoaHoc = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        mniTKDoanhThu = new javax.swing.JMenuItem();
        mnuTroGiup = new javax.swing.JMenu();
        mniHuongDan = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        mniGioiThieu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HỆ THỐNG QUẢN LÝ ĐÀO TẠO");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusTraversalPolicyProvider(true);

        toolBar.setRollover(true);

        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Exit.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setFocusable(false);
        btnDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseClicked(evt);
            }
        });
        toolBar.add(btnDangXuat);

        btnKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Stop.png"))); // NOI18N
        btnKetThuc.setText("Kết thúc");
        btnKetThuc.setFocusable(false);
        btnKetThuc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKetThuc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKetThuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKetThucMouseClicked(evt);
            }
        });
        toolBar.add(btnKetThuc);
        toolBar.add(jSeparator1);

        btnChuyenDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Lists.png"))); // NOI18N
        btnChuyenDe.setText("Chuyên đề");
        btnChuyenDe.setFocusable(false);
        btnChuyenDe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChuyenDe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChuyenDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChuyenDeMouseClicked(evt);
            }
        });
        toolBar.add(btnChuyenDe);

        btnNguoiHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Conference.png"))); // NOI18N
        btnNguoiHoc.setText("Người học");
        btnNguoiHoc.setFocusable(false);
        btnNguoiHoc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNguoiHoc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNguoiHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNguoiHocMouseClicked(evt);
            }
        });
        toolBar.add(btnNguoiHoc);

        btnKhoaHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Certificate.png"))); // NOI18N
        btnKhoaHoc.setText("Khóa học");
        btnKhoaHoc.setFocusable(false);
        btnKhoaHoc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKhoaHoc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKhoaHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKhoaHocMouseClicked(evt);
            }
        });
        btnKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoaHocActionPerformed(evt);
            }
        });
        toolBar.add(btnKhoaHoc);
        toolBar.add(jSeparator2);

        btnHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Globe.png"))); // NOI18N
        btnHuongDan.setText("Hướng dẫn");
        btnHuongDan.setFocusable(false);
        btnHuongDan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHuongDan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHuongDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuongDanActionPerformed(evt);
            }
        });
        toolBar.add(btnHuongDan);

        getContentPane().add(toolBar, java.awt.BorderLayout.PAGE_START);

        lblNoiDung.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/logo.png"))); // NOI18N
        lblNoiDung.setPreferredSize(new java.awt.Dimension(32, 32));
        lblNoiDung.setRequestFocusEnabled(false);

        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
        );
        pnlNoiDungLayout.setVerticalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnlNoiDung, java.awt.BorderLayout.CENTER);

        pnlTrangThai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnlTrangThai.setForeground(new java.awt.Color(204, 204, 204));
        pnlTrangThai.setLayout(new java.awt.BorderLayout());

        lblDongHo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Alarm.png"))); // NOI18N
        pnlTrangThai.add(lblDongHo, java.awt.BorderLayout.LINE_END);

        lblTrangThai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Info.png"))); // NOI18N
        lblTrangThai.setText("Hệ thống quản lý");
        pnlTrangThai.add(lblTrangThai, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlTrangThai, java.awt.BorderLayout.PAGE_END);

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        mnuHeThong.setText("Hệ thống");

        mniDangNhap.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        mniDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Key.png"))); // NOI18N
        mniDangNhap.setText("Đăng nhập");
        mniDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangNhapActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDangNhap);

        mniDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mniDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Log out.png"))); // NOI18N
        mniDangXuat.setText("Đăng xuất");
        mniDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangXuatActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDangXuat);
        mnuHeThong.add(jSeparator3);

        mniDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Refresh.png"))); // NOI18N
        mniDoiMatKhau.setText("Đổi mật khấu");
        mnuHeThong.add(mniDoiMatKhau);
        mnuHeThong.add(jSeparator4);

        mniKetThuc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        mniKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Stop.png"))); // NOI18N
        mniKetThuc.setText("Kết thúc");
        mnuHeThong.add(mniKetThuc);

        jMenuBar1.add(mnuHeThong);

        mnuQuanLy.setText("Quản lý");

        mniQLNguoiHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Conference.png"))); // NOI18N
        mniQLNguoiHoc.setText("Người học");
        mniQLNguoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQLNguoiHocActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniQLNguoiHoc);

        mniQLChuyenDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Lists.png"))); // NOI18N
        mniQLChuyenDe.setText("Chuyên đề");
        mniQLChuyenDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQLChuyenDeActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniQLChuyenDe);

        mniQLKhoaHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Certificate.png"))); // NOI18N
        mniQLKhoaHoc.setText("Khóa học");
        mniQLKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQLKhoaHocActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniQLKhoaHoc);

        mniQLNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/User group.png"))); // NOI18N
        mniQLNhanVien.setText("Quản lý nhân viên");
        mniQLNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQLNhanVienActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniQLNhanVien);

        jMenuBar1.add(mnuQuanLy);

        mnuThongKe.setText("Thống kê");

        mniTKNguoiHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Clien list.png"))); // NOI18N
        mniTKNguoiHoc.setText("Người học từng năm");
        mniTKNguoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTKNguoiHocActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniTKNguoiHoc);
        mnuThongKe.add(jSeparator5);

        mniTKBangDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Card file.png"))); // NOI18N
        mniTKBangDiem.setText("Bảng điểm khóa...");
        mniTKBangDiem.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                mniTKBangDiemMenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        mniTKBangDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTKBangDiemActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniTKBangDiem);

        mniTKKhoaHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Bar chart.png"))); // NOI18N
        mniTKKhoaHoc.setText("Điểm từng khóa ");
        mniTKKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTKKhoaHocActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniTKKhoaHoc);
        mnuThongKe.add(jSeparator6);

        mniTKDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Dollar.png"))); // NOI18N
        mniTKDoanhThu.setText("Thống kê doanh thu");
        mniTKDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTKDoanhThuActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniTKDoanhThu);

        jMenuBar1.add(mnuThongKe);

        mnuTroGiup.setText("Trợ giúp");

        mniHuongDan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mniHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Globe.png"))); // NOI18N
        mniHuongDan.setText("Hướng dẫn sử dụng ");
        mniHuongDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHuongDanActionPerformed(evt);
            }
        });
        mnuTroGiup.add(mniHuongDan);
        mnuTroGiup.add(jSeparator7);

        mniGioiThieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Brick house.png"))); // NOI18N
        mniGioiThieu.setText("Giới thiệu sản phẩm ");
        mniGioiThieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGioiThieuActionPerformed(evt);
            }
        });
        mnuTroGiup.add(mniGioiThieu);

        jMenuBar1.add(mnuTroGiup);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoaHocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKhoaHocActionPerformed

    private void btnKetThucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKetThucMouseClicked
        // TODO add your handling code here:
        this.exit();
    }//GEN-LAST:event_btnKetThucMouseClicked

    private void btnChuyenDeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChuyenDeMouseClicked
        // TODO add your handling code here:
        this.openChuyenDe();
    }//GEN-LAST:event_btnChuyenDeMouseClicked

    private void btnDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseClicked
        // TODO add your handling code here:
        this.openLogin();
    }//GEN-LAST:event_btnDangXuatMouseClicked

    private void btnNguoiHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNguoiHocMouseClicked
        // TODO add your handling code here:
        this.openNguoiHoc();
    }//GEN-LAST:event_btnNguoiHocMouseClicked

    private void btnKhoaHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhoaHocMouseClicked
        // TODO add your handling code here:
        this.openKhoaHoc();
    }//GEN-LAST:event_btnKhoaHocMouseClicked

    private void mniQLNguoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLNguoiHocActionPerformed
        // TODO add your handling code here:
        this.openNguoiHoc();
    }//GEN-LAST:event_mniQLNguoiHocActionPerformed

    private void mniQLChuyenDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLChuyenDeActionPerformed
        // TODO add your handling code here:
        this.openChuyenDe();
    }//GEN-LAST:event_mniQLChuyenDeActionPerformed

    private void mniDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangNhapActionPerformed
        // TODO add your handling code here:
        this.openLogin();
    }//GEN-LAST:event_mniDangNhapActionPerformed

    private void mniDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangXuatActionPerformed
        // TODO add your handling code here:
        this.openLogin();
    }//GEN-LAST:event_mniDangXuatActionPerformed

    private void mniQLKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLKhoaHocActionPerformed
        // TODO add your handling code here:
        this.openKhoaHoc();
    }//GEN-LAST:event_mniQLKhoaHocActionPerformed

    private void mniQLNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLNhanVienActionPerformed
        // TODO add your handling code here:
        this.openNhanVien();
    }//GEN-LAST:event_mniQLNhanVienActionPerformed

    private void mniTKNguoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTKNguoiHocActionPerformed
        // TODO add your handling code here:
        this.openThongKe(0);
    }//GEN-LAST:event_mniTKNguoiHocActionPerformed

    private void mniTKBangDiemMenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_mniTKBangDiemMenuKeyPressed
        // TODO add your handling code here:
        this.openThongKe(1);
    }//GEN-LAST:event_mniTKBangDiemMenuKeyPressed

    private void mniTKKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTKKhoaHocActionPerformed
        // TODO add your handling code here:
        this.openThongKe(2);
    }//GEN-LAST:event_mniTKKhoaHocActionPerformed

    private void mniTKDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTKDoanhThuActionPerformed
        // TODO add your handling code here:
        this.openThongKe(3);
    }//GEN-LAST:event_mniTKDoanhThuActionPerformed

    private void mniGioiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGioiThieuActionPerformed
        // TODO add your handling code here:
//        this.openAbout();
    }//GEN-LAST:event_mniGioiThieuActionPerformed

    private void mniTKBangDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTKBangDiemActionPerformed
        // TODO add your handling code here:
        this.openThongKe(1);
    }//GEN-LAST:event_mniTKBangDiemActionPerformed

    private void btnHuongDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuongDanActionPerformed
        // TODO add your handling code here:
        this.openWebsite();
    }//GEN-LAST:event_btnHuongDanActionPerformed

    private void mniHuongDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHuongDanActionPerformed
        // TODO add your handling code here:
        this.openWebsite();
    }//GEN-LAST:event_mniHuongDanActionPerformed

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
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChuyenDe;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnHuongDan;
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JButton btnKhoaHoc;
    private javax.swing.JButton btnNguoiHoc;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblNoiDung;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JMenuItem mniDangNhap;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenuItem mniDoiMatKhau;
    private javax.swing.JMenuItem mniGioiThieu;
    private javax.swing.JMenuItem mniHuongDan;
    private javax.swing.JMenuItem mniKetThuc;
    private javax.swing.JMenuItem mniQLChuyenDe;
    private javax.swing.JMenuItem mniQLKhoaHoc;
    private javax.swing.JMenuItem mniQLNguoiHoc;
    private javax.swing.JMenuItem mniQLNhanVien;
    private javax.swing.JMenuItem mniTKBangDiem;
    private javax.swing.JMenuItem mniTKDoanhThu;
    private javax.swing.JMenuItem mniTKKhoaHoc;
    private javax.swing.JMenuItem mniTKNguoiHoc;
    private javax.swing.JMenu mnuHeThong;
    private javax.swing.JMenu mnuQuanLy;
    private javax.swing.JMenu mnuThongKe;
    private javax.swing.JMenu mnuTroGiup;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
