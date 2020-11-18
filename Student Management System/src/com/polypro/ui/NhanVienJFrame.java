/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.ui;

import com.polypro.dao.NhanVienDAO;
import com.polypro.helper.DialogHelper;
import com.polypro.helper.ShareHelper;
import com.polypro.model.NhanVien;
import java.io.File;
import java.util.List;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class NhanVienJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NhanVienJFrame
     */
    public NhanVienJFrame() {
        initComponents();
        init();
    }

    int index = 0; // vị trí của nhân viên đang hiển thị trên form
    NhanVienDAO dao = new NhanVienDAO();
    public String gender;

    void init() {
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);
    }

    void load() {
        txtMaNV.requestFocus();
        DefaultTableModel model = (DefaultTableModel) tblGridView.getModel();
        model.setRowCount(0);
        try {
            List<NhanVien> list = dao.select();
            for (NhanVien nv : list) {
                Object[] row = {
                    nv.getMaNV(),
                    nv.getGioiTinh(),
                    nv.getMatKhau(),
                    nv.getHoTen().toUpperCase(),
                    nv.getDienThoai(),
                    nv.getEmail(),
                    nv.getDiaChi(),
                    nv.getHinh(),
                    nv.getVaiTro() ? "Trưởng phòng" : "Nhân viên"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void insert() {
        NhanVien model = getModel();

        String confirm = new String(txtXacNhanMK.getPassword());
        if (confirm.equals(model.getMatKhau())) {
            try {
                dao.insert(model);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Thêm mới thất bại!");
            }
        } else {
            DialogHelper.alert(this, "Xác nhận mật khẩu không đúng!");
        }
    }
    void checkdata() {
        String pass = ".{3,}";// it nhat 3 ky tu;
        String hoten = "[a-zA-Z  ]+"; //họ tên chỉ chứa chữ thôi!!!
        String email = "\\w+@\\w+(\\.\\w+){1,2}";
        String SDT = "\\d+";

        if (txtMaNV.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống ID,vui lòng nhập lại");
        } else if (!txtMatKhau.getText().matches(pass)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu có ít nhất 3 kí tự");
            txtMatKhau.setText("");
            txtMatKhau.requestFocus();
        } else if (!txtXacNhanMK.getText().equals(txtMatKhau.getText())) {
            JOptionPane.showMessageDialog(this, "Mật khẩu ko trùng nhau");
            txtXacNhanMK.setText("");
            txtXacNhanMK.requestFocus();
        } else if (!txtHoTen.getText().matches(hoten)) {
            JOptionPane.showMessageDialog(this, "Họ và Tên chỉ được chưa kí tự là chữ");
            txtHoTen.setText("");
            txtHoTen.requestFocus();
        } else if (!txtDienThoai.getText().matches(SDT)) {
            JOptionPane.showMessageDialog(this, "Sai định dạng số điện thoại");
            txtDienThoai.setText("");
            txtDienThoai.requestFocus();
        } else if (!txtEmail.getText().matches(email)) {
            JOptionPane.showMessageDialog(this, "Sai định dạng Email, vui lòng nhập lại");
            txtEmail.setText("");
            txtEmail.requestFocus();
        } else if (!rdoTruongPhong.isSelected() && !rdoTruongPhong.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn chức vụ");
        }else if(!radioNam.isSelected()&&!radioNu.isSelected()&&radioKhac.isSelected()){ 
             JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính");
        } else {
            NhanVien model = getModel();
            try {
                dao.insert(model);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Mã Nhân Viên trùng nhau, thêm mới thất bại!!!");
            }

        }

    }

    void update() {
        NhanVien model = getModel();

        String confirm = new String(txtXacNhanMK.getPassword());
        if (!confirm.equals(model.getMatKhau())) {
            DialogHelper.alert(this, "Xác nhận mật khẩu không đúng!");
        } else {
            try {
                dao.update(model);
                this.load();
                DialogHelper.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Cập nhật thất bại!");
            }
        }
    }
    
    void check_update() {
        String pass = ".{3,}";// it nhat 3 ky tu;
        String hoten = "[a-zA-Z  ]+"; //họ tên chỉ chứa chữ thôi!!!
        String email = "\\w+@\\w+(\\.\\w+){1,2}";
        String SDT = "\\d+";

        if (txtMaNV.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống ID,vui lòng nhập lại");
        } else if (!txtMatKhau.getText().matches(pass)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu có ít nhất 3 kí tự");
            txtMatKhau.setText("");
            txtMatKhau.requestFocus();
        } else if (!txtXacNhanMK.getText().equals(txtMatKhau.getText())) {
            JOptionPane.showMessageDialog(this, "Mật khẩu ko trùng nhau");
            txtXacNhanMK.setText("");
            txtXacNhanMK.requestFocus();
        } else if (!txtHoTen.getText().matches(hoten)) {
            JOptionPane.showMessageDialog(this, "Họ và Tên chỉ được chưa kí tự là chữ");
            txtHoTen.setText("");
            txtHoTen.requestFocus();
        } else if (!txtDienThoai.getText().matches(SDT)) {
            JOptionPane.showMessageDialog(this, "Sai định dạng số điện thoại");
            txtDienThoai.setText("");
            txtDienThoai.requestFocus();
        } else if (!txtEmail.getText().matches(email)) {
            JOptionPane.showMessageDialog(this, "Sai định dạng Email, vui lòng nhập lại");
            txtEmail.setText("");
            txtEmail.requestFocus();
        } else if (!rdoTruongPhong.isSelected() && !rdoTruongPhong.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn chức vụ");
        }else if(!radioNam.isSelected()&&!radioNu.isSelected()&&radioKhac.isSelected()){ 
             JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính");
        } else {
            NhanVien model = getModel();
            try {
                dao.update(model);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Cập nhật thất bại!!!");
            }

        }

    }

    void delete() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa nhân viên này?")) {
            String manv = txtMaNV.getText();
            try {
                dao.delete(manv);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
         
            }
        }
    }

    void edit() {
        try {
            String manv = (String) tblGridView.getValueAt(this.index, 0);
            NhanVien model = dao.findByID(manv);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void clear() {
        this.setModel_1(new NhanVien());
        this.setStatus(true);
    }

    void setModel(NhanVien model) {
        txtMaNV.setText(model.getMaNV());
        txtHoTen.setText(model.getHoTen().toUpperCase());
        txtMatKhau.setText(model.getMatKhau());
        txtXacNhanMK.setText(model.getMatKhau());
        txtDienThoai.setText(String.valueOf(model.getDienThoai()));
        txtDiaChi.setText(model.getDiaChi());
        txtEmail.setText(model.getEmail());
        lblAvt.setToolTipText(model.getHinh());
        if (model.getHinh() != null) {
            lblAvt.setIcon(ShareHelper.readLogo(model.getHinh()));
        }
        rdoTruongPhong.setSelected(model.getVaiTro());
        rdoNhanVien.setSelected(!model.getVaiTro());
//        if (model.getGioiTinh().equals("Male")) {
//            radioNam.setSelected(true);
//        } else if (model.getGioiTinh().equals("Female")) {
//            radioNu.setSelected(true);
//        } else {
//            radioKhac.setSelected(true);
         
//        }
        cbboxGioiTinh.setSelectedItem(model.getGioiTinh());
      
    }
     void setModel_1(NhanVien model) {
        txtMaNV.setText(model.getMaNV());
        txtHoTen.setText(model.getHoTen());
        txtMatKhau.setText(model.getMatKhau());
        txtXacNhanMK.setText(model.getMatKhau());
        txtDienThoai.setText(String.valueOf(model.getDienThoai()));
        txtDiaChi.setText(model.getDiaChi());
        txtEmail.setText(model.getEmail());
        lblAvt.setToolTipText(model.getHinh());
        if (model.getHinh() != null) {
            lblAvt.setIcon(ShareHelper.readLogo(model.getHinh()));
        }
        rdoTruongPhong.setSelected(model.getVaiTro());
        rdoNhanVien.setSelected(!model.getVaiTro());
       
      
    }
    
    
    

    NhanVien getModel() {
        NhanVien model = new NhanVien();
        model.setMaNV(txtMaNV.getText());
        model.setGioiTinh(gender);
//        if(radioNam.isSelected()){
//            model.setGioiTinh("Male");
//        }else if(radioNu.isSelected()){
//            model.setGioiTinh("Female");
//        }else{
//             model.setGioiTinh("Other");
//        }
        model.setGioiTinh(cbboxGioiTinh.getSelectedItem().toString());
        String hoten = txtHoTen.getText().toUpperCase();
        model.setHoTen(hoten);
        model.setMatKhau(new String(txtMatKhau.getPassword()));
        model.setDienThoai(String.valueOf(txtDienThoai.getText()));
        model.setDiaChi(txtDiaChi.getText());
        model.setEmail(txtEmail.getText());
        model.setHinh(lblAvt.getToolTipText());
        model.setVaiTro(rdoTruongPhong.isSelected());
        return model;
    }

    void setStatus(boolean insertable) {
        txtMaNV.setEditable(insertable);
        btnInsert.setEnabled(insertable);
        btnUpdate.setEnabled(!insertable);
        btnDelete.setEnabled(!insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblGridView.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnPrev.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
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
    
    public boolean isvalid() {
        if (txtMaNV.getText().length()==0) {
            DialogHelper.alert(this, "Vui lòng nhập tên đăng nhập!");
            txtMaNV.requestFocus();
            return false;
        }
            return true;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrVaiTro = new javax.swing.ButtonGroup();
        fileChooser = new javax.swing.JFileChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        lblTitle = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        pnlEdit = new javax.swing.JPanel();
        lblMaNV = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        lblMatKhau = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        lblXacNhanMK = new javax.swing.JLabel();
        txtXacNhanMK = new javax.swing.JPasswordField();
        lblHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        lblVaiTro = new javax.swing.JLabel();
        rdoTruongPhong = new javax.swing.JRadioButton();
        rdoNhanVien = new javax.swing.JRadioButton();
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
        lblDienThoai = new javax.swing.JLabel();
        txtDienThoai = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblDiaChi = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        lblEmail1 = new javax.swing.JLabel();
        radioNam = new javax.swing.JRadioButton();
        radioNu = new javax.swing.JRadioButton();
        radioKhac = new javax.swing.JRadioButton();
        cbboxGioiTinh = new javax.swing.JComboBox<>();
        pnlList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGridView = new javax.swing.JTable();

        fileChooser.setDialogTitle("Chọn hình nhân viên");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ NHÂN VIÊN");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 153));
        lblTitle.setText("QUẢN LÝ NHÂN VIÊN QUẢN TRỊ");

        lblMaNV.setText("Mã nhân viên");

        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        lblMatKhau.setText("Mật khẩu");

        lblXacNhanMK.setText("Xác nhận mật khẩu");

        lblHoTen.setText("Họ và tên");

        lblVaiTro.setText("Vai trò");

        bgrVaiTro.add(rdoTruongPhong);
        rdoTruongPhong.setSelected(true);
        rdoTruongPhong.setText("Trưởng phòng");
        rdoTruongPhong.setContentAreaFilled(false);

        bgrVaiTro.add(rdoNhanVien);
        rdoNhanVien.setText("Nhân viên ");

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

        pnlAvatar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlAvatar.setLayout(new java.awt.BorderLayout());

        lblAvt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAvtMouseClicked(evt);
            }
        });
        pnlAvatar.add(lblAvt, java.awt.BorderLayout.CENTER);

        lblDienThoai.setText("Điện thoại");

        lblEmail.setText("Email");

        lblDiaChi.setText("Địa chỉ");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane2.setViewportView(txtDiaChi);

        lblEmail1.setText("Giới Tính");

        buttonGroup1.add(radioNam);
        radioNam.setText("Nam");
        radioNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioNu);
        radioNu.setText("Nữ");
        radioNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNuActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioKhac);
        radioKhac.setText("Khác");
        radioKhac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioKhacActionPerformed(evt);
            }
        });

        cbboxGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Other", " " }));

        javax.swing.GroupLayout pnlEditLayout = new javax.swing.GroupLayout(pnlEdit);
        pnlEdit.setLayout(pnlEditLayout);
        pnlEditLayout.setHorizontalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addComponent(btnInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast)
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblDienThoai)
                                .addComponent(txtDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                .addComponent(lblEmail)
                                .addComponent(txtEmail))
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(rdoTruongPhong)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNhanVien))
                            .addComponent(lblVaiTro)
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaNV)
                                    .addComponent(lblMaNV)
                                    .addComponent(lblMatKhau)
                                    .addComponent(txtMatKhau)
                                    .addComponent(lblXacNhanMK)
                                    .addComponent(txtXacNhanMK)
                                    .addComponent(lblHoTen)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                                .addGap(109, 109, 109)
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDiaChi)
                                    .addComponent(pnlAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail1)
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addComponent(radioNam)
                                .addGap(18, 18, 18)
                                .addComponent(radioNu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioKhac)
                                .addGap(18, 18, 18)
                                .addComponent(cbboxGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 314, Short.MAX_VALUE))))
        );
        pnlEditLayout.setVerticalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMaNV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMatKhau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblXacNhanMK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtXacNhanMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblHoTen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDienThoai)
                    .addComponent(lblDiaChi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblEmail1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioNam)
                    .addComponent(radioNu)
                    .addComponent(radioKhac)
                    .addComponent(cbboxGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(lblVaiTro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNhanVien)
                    .addComponent(rdoTruongPhong))
                .addGap(18, 18, 18)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFirst, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast)
                    .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnInsert)
                        .addComponent(btnUpdate)
                        .addComponent(btnDelete)
                        .addComponent(btnClear)))
                .addContainerGap())
        );

        tabs.addTab("CẬP NHẬT", pnlEdit);

        pnlList.setLayout(new java.awt.BorderLayout());

        tblGridView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ NV", "GIỚI TÍNH", "MẬT KHẨU", "HỌ VÀ TÊN", "ĐIỆN THOẠI", "EMAIL", "ĐỊA CHỈ", "HÌNH", "VAI TRÒ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
//        this.update();
         this.check_update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.load();
        this.setStatus(true);
    }//GEN-LAST:event_formWindowOpened

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

    private void lblAvtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAvtMouseClicked
        // TODO add your handling code here:
        this.selectImage();
    }//GEN-LAST:event_lblAvtMouseClicked

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void radioNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNamActionPerformed
        // TODO add your handling code here:
          gender="Male";
    }//GEN-LAST:event_radioNamActionPerformed

    private void radioNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNuActionPerformed
        // TODO add your handling code here:
          gender="Female";
        
    }//GEN-LAST:event_radioNuActionPerformed

    private void radioKhacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioKhacActionPerformed
        // TODO add your handling code here:
          gender="Other";
    }//GEN-LAST:event_radioKhacActionPerformed

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
            java.util.logging.Logger.getLogger(NhanVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVienJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrVaiTro;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbboxGioiTinh;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAvt;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblDienThoai;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmail1;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JLabel lblXacNhanMK;
    private javax.swing.JPanel pnlAvatar;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlList;
    private javax.swing.JRadioButton radioKhac;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoTruongPhong;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblGridView;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JPasswordField txtXacNhanMK;
    // End of variables declaration//GEN-END:variables
}
