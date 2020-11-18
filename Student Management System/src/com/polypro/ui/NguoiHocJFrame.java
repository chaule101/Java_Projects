/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.ui;

import com.polypro.dao.NguoiHocDAO;
import com.polypro.helper.DateHelper;
import com.polypro.helper.DialogHelper;
import com.polypro.helper.ShareHelper;
import com.polypro.model.NguoiHoc;
import com.polypro.model.NhanVien;

import java.awt.HeadlessException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class NguoiHocJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NhanVienJFrame
     */
    public NguoiHocJFrame() {
        initComponents();
        init();
    }

    int index = 0;
    NguoiHocDAO dao = new NguoiHocDAO();
    public String gender;

    void init() {
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);
    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblGridView.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
//           
            List<NguoiHoc> list = dao.selectByKeyword(keyword);
            for (NguoiHoc nh : list) {
                Object[] row = {
                    nh.getMaNH(),
                    nh.getHoTen(),
//                    nh.isGioiTinh() ? "Nam" : "Nữ" ,
                    
                    nh.getGioiTinh(),
                    DateHelper.toString(nh.getNgaySinh()),
                    nh.getEmail(),
                    nh.getDienThoai(),
                    nh.getHinh(),
                    nh.getMaNV(),
                    DateHelper.toString(nh.getNgayDK())
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void insert() {
        NguoiHoc model = getModel();
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại!");
        }
    }
    
    void checkdata() {
        String MaNH = "[a-zA-Z0-9_]{4}";
       Pattern p = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);
      
        String hoten = "[a-zA-Z  ]+"; //họ tên chỉ chứa chữ thôi!!!
        
        String email = "\\w+@\\w+(\\.\\w+){1,2}";
         String SDT = "\\d+";

        if (txtMaNH.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống ID,vui lòng nhập lại");
        } else if (!txtMaNH.getText().matches(MaNH)) {
            JOptionPane.showMessageDialog(this, "Mã Người Học Phải đúng 5 kí tự");
            txtMaNH.setText("");
            txtMaNH.requestFocus();
        }else if(!txtHoTen.getText().matches(hoten)){
//        } else if (!hoten.matcher(txtHoTen.getText()).matches()) {
            JOptionPane.showMessageDialog(this, "Họ và Tên chỉ được chưa kí tự là chữ");
            txtHoTen.setText("");
            txtHoTen.requestFocus();
        
        } else if (!txtEmail.getText().matches(email)) {
            JOptionPane.showMessageDialog(this, "Sai định dạng Email, vui lòng nhập lại");
            txtEmail.setText("");
            txtEmail.requestFocus();
         }else if(!txtDienThoai.getText().matches(SDT)){   
              JOptionPane.showMessageDialog(this, "Số điện thoại chỉ được nhập số!!!");
            txtDienThoai.setText("");
            txtDienThoai.requestFocus();   
        } else {
            NguoiHoc model = getModel();
            try {
                dao.insert(model);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Mã Người Học trùng nhau, thêm mới thất bại!!!");
//                System.out.println(e);
            }

        }

    }
     void check_update() {
//        String MaNH = "[a-zA-Z0-9_]{5}";
        String hoten = "[a-zA-Z  ]+"; //họ tên chỉ chứa chữ thôi!!!
        String email = "\\w+@\\w+(\\.\\w+){1,2}";
        String SDT = "\\d+";
        
        if (!txtHoTen.getText().matches(hoten)) {
            JOptionPane.showMessageDialog(this, "Họ và Tên chỉ được chưa kí tự là chữ");
            txtHoTen.setText("");
            txtHoTen.requestFocus();        
        } else if (!txtEmail.getText().matches(email)) {
            JOptionPane.showMessageDialog(this, "Sai định dạng Email, vui lòng nhập lại");
            txtEmail.setText("");
            txtEmail.requestFocus();
        }else if(!txtDienThoai.getText().matches(SDT)){   
              JOptionPane.showMessageDialog(this, "Số điện thoại chỉ được nhập số!!!");
            txtDienThoai.setText("");
            txtDienThoai.requestFocus();
        } else {
            NguoiHoc model = getModel();
            try {
                dao.update(model);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Cập nhật thất bại");        
            }
        }
    }

    void update() {
        NguoiHoc model = getModel();
        try {
            dao.update(model);
            this.load();
            DialogHelper.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhật thất bại!");
            System.out.println(e);
        }
    }

    void delete() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa người học này?")) {
            String manh = txtMaNH.getText();
            try {
                dao.delete(manh);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (HeadlessException e) {
                DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    void clear() {
        NguoiHoc model = new NguoiHoc();      
        model.setMaNV(ShareHelper.USER.getMaNV());
        model.setNgaySinh(DateHelper.now());
        this.setModel_1(model);
        this.setStatus(true);
       
    }

    void edit() {
        try {
            String manh = (String) tblGridView.getValueAt(this.index, 0);
            NguoiHoc model = dao.findById(manh);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
         
        }
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

    void setModel(NguoiHoc model) {
        txtMaNH.setText(model.getMaNH());
        txtHoTen.setText(model.getHoTen());
//      cboGioiTinh.setSelectedIndex(model.isGioiTinh() ? 0 : 1);       
        date_chooser.setDate(model.getNgaySinh());
        txtDienThoai.setText(model.getDienThoai());
        txtEmail.setText(model.getEmail());
        txtGhiChu.setText(model.getGhiChu());
        lblAvt.setToolTipText(model.getHinh());
        if (model.getHinh() != null) {
            lblAvt.setIcon(ShareHelper.readLogo(model.getHinh()));
        }
//        if (model.getGioiTinh().equals("Male")) {
//            radioNam.setSelected(true);
//        } else if (model.getGioiTinh().equals("Female")) {
//            radioNu.setSelected(true);
//        } else {
//            radioKhac.setSelected(true);
//        }
         cbboxGioiTinh.setSelectedItem(String.valueOf(model.getGioiTinh()));
         

        //model.setMaNV(ShareHelper.USER.getMaNV());
        //model.setNgayDK(DateHelper.now());
    }
      void setModel_1(NguoiHoc model) {
        txtMaNH.setText(model.getMaNH());
        txtHoTen.setText(model.getHoTen());
//      cboGioiTinh.setSelectedIndex(model.isGioiTinh() ? 0 : 1);       
        date_chooser.setDate(model.getNgaySinh());
        txtDienThoai.setText(model.getDienThoai());
        txtEmail.setText(model.getEmail());
        txtGhiChu.setText(model.getGhiChu());
        lblAvt.setToolTipText(model.getHinh());
        if (model.getHinh() != null) {
            lblAvt.setIcon(ShareHelper.readLogo(model.getHinh()));
        }
    }
    
    
    

    NguoiHoc getModel() {
        
        NguoiHoc model = new NguoiHoc();
        model.setMaNH(txtMaNH.getText());
        model.setHoTen(txtHoTen.getText().toUpperCase());
//        model.setGioiTinh(cboGioiTinh.getSelectedIndex() == 0);
//        model.setGioiTinh(gender); 
//        if(radioNam.isSelected()){
//            model.setGioiTinh(String.valueOf(radioNam.getText()));
//        }else if(radioNu.isSelected()){
//            model.setGioiTinh(String.valueOf(radioNu.getText()));
//        }else{
//             model.setGioiTinh(String.valueOf(radioKhac.getText()));
//        }
         model.setGioiTinh(cbboxGioiTinh.getSelectedItem().toString());
        model.setNgaySinh(date_chooser.getDate());
        model.setDienThoai(txtDienThoai.getText());
        model.setEmail(txtEmail.getText());
        model.setGhiChu(txtGhiChu.getText());
        model.setHinh(lblAvt.getToolTipText());
        model.setMaNV(ShareHelper.USER.getMaNV());
        model.setNgayDK(DateHelper.now());
        return model;
    }
    
    void setStatus(boolean insertable) {
        txtMaNH.setEditable(insertable);
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

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        lblTitle = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        pnlEdit = new javax.swing.JPanel();
        lblMaNH = new javax.swing.JLabel();
        txtMaNH = new javax.swing.JTextField();
        lblHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        pnlAvatar = new javax.swing.JPanel();
        lblAvt = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblDienThoai = new javax.swing.JLabel();
        txtDienThoai = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblGhiChu = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnPrev = new javax.swing.JButton();
        date_chooser = new com.toedter.calendar.JDateChooser();
        radioNam = new javax.swing.JRadioButton();
        radioNu = new javax.swing.JRadioButton();
        radioKhac = new javax.swing.JRadioButton();
        cbboxGioiTinh = new javax.swing.JComboBox<>();
        pnlList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGridView = new javax.swing.JTable();
        pnlTimKiem = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();

        fileChooser.setDialogTitle("Chọn ảnh cho người học");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ NGƯỜI HỌC");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 153));
        lblTitle.setText("QUẢN LÝ NGƯỜI HỌC");

        lblMaNH.setText("Mã người học");

        lblHoTen.setText("Họ và tên");

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

        pnlAvatar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        pnlAvatar.setLayout(new java.awt.BorderLayout());

        lblAvt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAvtMouseClicked(evt);
            }
        });
        pnlAvatar.add(lblAvt, java.awt.BorderLayout.CENTER);

        lblGioiTinh.setText("Giới tính");

        lblNgaySinh.setText("Ngày sinh");

        lblDienThoai.setText("Điện thoại");

        lblEmail.setText("Địa chỉ email");

        lblGhiChu.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/polypro/icon/Previous.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        date_chooser.setDateFormatString("yyyy MMM, dd");

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
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaNH)
                                .addComponent(txtMaNH, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblHoTen)
                            .addComponent(lblGioiTinh)
                            .addComponent(lblEmail)
                            .addComponent(lblDienThoai)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addComponent(radioNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioNu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioKhac))))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(date_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbboxGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGhiChu)
                    .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlEditLayout.createSequentialGroup()
                            .addComponent(btnFirst)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnPrev)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnNext)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnLast))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 18, Short.MAX_VALUE))
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNgaySinh)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEditLayout.setVerticalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addComponent(lblMaNH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHoTen)
                        .addGap(12, 12, 12)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblGioiTinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioNam)
                            .addComponent(radioNu)
                            .addComponent(radioKhac))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbboxGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(lblNgaySinh)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditLayout.createSequentialGroup()
                        .addComponent(pnlAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(date_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDienThoai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addComponent(lblGhiChu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrev, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnFirst, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInsert)
                            .addComponent(btnUpdate)
                            .addComponent(btnDelete)
                            .addComponent(btnClear)))
                    .addComponent(btnLast, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNext, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        tabs.addTab("CẬP NHẬT", pnlEdit);

        tblGridView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ NH", "HỌ VÀ TÊN", "GIỚI TÍNH", "NGÀY SINH", "EMAIL", "ĐIỆN THOẠI", "HÌNH", "MÃ NV", "NGÀY ĐK"
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblGridViewMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblGridView);

        pnlTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        txtTimKiem.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtTimKiemInputMethodTextChanged(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyTyped(evt);
            }
        });

        btnTimKiem.setText("Tìm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemLayout = new javax.swing.GroupLayout(pnlTimKiem);
        pnlTimKiem.setLayout(pnlTimKiemLayout);
        pnlTimKiemLayout.setHorizontalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem)
                .addGap(20, 20, 20))
        );
        pnlTimKiemLayout.setVerticalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlListLayout = new javax.swing.GroupLayout(pnlList);
        pnlList.setLayout(pnlListLayout);
        pnlListLayout.setHorizontalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(pnlTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
            .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlListLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlListLayout.setVerticalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(427, Short.MAX_VALUE))
            .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListLayout.createSequentialGroup()
                    .addContainerGap(121, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(59, Short.MAX_VALUE)))
        );

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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tabs)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
       this.checkdata();
      
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
       this.check_update();
//        update();
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

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        this.load();
        this.clear();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void lblAvtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAvtMouseClicked
        // TODO add your handling code here:
        this.selectImage();
    }//GEN-LAST:event_lblAvtMouseClicked

    private void tblGridViewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGridViewMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblGridViewMouseEntered

    private void txtTimKiemInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtTimKiemInputMethodTextChanged
        // TODO add your handling code here:
        this.load();
        this.clear();
    }//GEN-LAST:event_txtTimKiemInputMethodTextChanged

    private void txtTimKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKeyTyped

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
         // TODO add your handling code here:
         DefaultTableModel table =(DefaultTableModel)tblGridView.getModel();
         String search=txtTimKiem.getText().toUpperCase();
         TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);         
         tblGridView.setRowSorter(tr);  
         //import thuw vien (import javax.swing.table.TableRowSorter;)
         tr.setRowFilter(RowFilter.regexFilter(search.trim()));
         
         
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void radioNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNamActionPerformed
        // TODO add your handling code here:
        gender="Male";
    }//GEN-LAST:event_radioNamActionPerformed

    private void radioNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNuActionPerformed
        // TODO add your handling code here:
        gender = "Female";
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
            java.util.logging.Logger.getLogger(NguoiHocJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NguoiHocJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NguoiHocJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NguoiHocJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NguoiHocJFrame().setVisible(true);
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
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbboxGioiTinh;
    private com.toedter.calendar.JDateChooser date_chooser;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAvt;
    private javax.swing.JLabel lblDienThoai;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMaNH;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlAvatar;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlList;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JRadioButton radioKhac;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblGridView;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNH;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
