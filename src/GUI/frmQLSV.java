/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author HP
 */
public class frmQLSV extends javax.swing.JFrame {

    DefaultTableModel tbn = new DefaultTableModel();
    Connect a = new Connect();
    Connection conn = a.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     * Creates new form frmQLĐSV
     */
    public frmQLSV() {
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("FORM QUẢN LÝ SINH VIÊN");
        rdbNam.setSelected(true);
        loadData();
        //loadCombobox();
        getcbbSQL();
    }

//    public void loadCombobox() {
//        try {
//            ps = conn.prepareStatement("SELECT MALOP FROM SINHVIEN GROUP BY MALOP");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                cbbMaLop.addItem(rs.getString("MALOP"));
//            }
//
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//    }
    public void loadData() {
        try {
            int number;
            Vector row, column;
            column = new Vector();
            Statement st = conn.createStatement();
            rs = st.executeQuery("SELECT MSSV, HOTEN, NGAYSINH = CONVERT(varchar, NGAYSINH, 103), GIOITINH = (CASE GIOITINH WHEN 'TRUE' THEN N'NAM' \n"
                    + "WHEN 'FALSE' THEN N'NỮ' END), EMAIL, DIACHI, MALOP\n"
                    + "FROM SINHVIEN");
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount(); //trả về số cột

            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i)); //lấy ra tiêu đề của các cột
            }
            tbn.setColumnIdentifiers(column);

            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                }
                tbn.addRow(row);
                tblSV.setModel(tbn);
            }
            tblSV.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (tblSV.getSelectedRow() >= 0) {
                        txtMSSV.setText(tblSV.getValueAt(tblSV.getSelectedRow(), 0) + "");
                        txtHoten.setText(tblSV.getValueAt(tblSV.getSelectedRow(), 1) + "");
                        txtNgaySinh.setText(tblSV.getValueAt(tblSV.getSelectedRow(), 2) + "");
                        String gt = tblSV.getValueAt(tblSV.getSelectedRow(), 3).toString();
                        if (gt.equals("NAM")) {
                            rdbNam.setSelected(true);
                        } else {
                            rdbNu.setSelected(true);
                        }
                        txtEmail.setText(tblSV.getValueAt(tblSV.getSelectedRow(), 4) + "");
                        txaDiaChi.setText(tblSV.getValueAt(tblSV.getSelectedRow(), 5) + "");
                        cbbMaLop.setSelectedItem(tblSV.getValueAt(tblSV.getSelectedRow(), 6) + "");
                        cbbSearchMSSV.setSelectedItem(tblSV.getValueAt(tblSV.getSelectedRow(), 0) + "");
                        cbbSearchMaLop.setSelectedItem(tblSV.getValueAt(tblSV.getSelectedRow(), 6) + "");
                    }
                }
            });
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void getcbbSQL() {
        MSSVCombobox();
        MaLopCombobox();
    }

    public void MSSVCombobox() {
        try {
            conn = a.getConnection();
            ps = conn.prepareStatement("SELECT MSSV FROM SINHVIEN");
            rs = ps.executeQuery();

            //Tạo 1 DefaultComboboxModel
            DefaultComboBoxModel SeMSSV = (DefaultComboBoxModel) cbbSearchMSSV.getModel();
            SeMSSV.removeAllElements(); //Xóa hết dữ liệu trong combobox
            while (rs.next()) {
                cbbSearchMSSV.addItem(rs.getString("MSSV"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "LỖI LẤY MÃ SỐ SINH VIÊN");
        }
    }

    public void MaLopCombobox() {
        try {
            conn = a.getConnection();
            ps = conn.prepareStatement("SELECT MALOP FROM LOPHOC GROUP BY MALOP");
            rs = ps.executeQuery();

            //Tạo 1 DefaultComboboxModel
            DefaultComboBoxModel SeMaLop = (DefaultComboBoxModel) cbbSearchMaLop.getModel();
            DefaultComboBoxModel MaLop = (DefaultComboBoxModel) cbbMaLop.getModel();
            SeMaLop.removeAllElements(); //Xóa hết dữ liệu trong combobox
            MaLop.removeAllElements(); //Xóa hết dữ liệu trong combobox
            while (rs.next()) {
                cbbSearchMaLop.addItem(rs.getString("MALOP"));
                cbbMaLop.addItem(rs.getString("MALOP"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "LỖI LẤY MÃ LỚP");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cbbSearchMaLop = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbbSearchMSSV = new javax.swing.JComboBox<>();
        btnExit = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtMSSV = new javax.swing.JTextField();
        txtHoten = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDiaChi = new javax.swing.JTextArea();
        rdbNam = new javax.swing.JRadioButton();
        rdbNu = new javax.swing.JRadioButton();
        cbbMaLop = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSV = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ THÔNG TIN SINH VIÊN");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "BẢNG TÌM KIẾM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("MÃ LỚP");

        cbbSearchMaLop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbSearchMaLop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("MÃ SỐ SINH VIÊN");

        cbbSearchMSSV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(cbbSearchMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbbSearchMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbSearchMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbSearchMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        btnExit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExit.setText("THOÁT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "THÔNG TIN SINH VIÊN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("MÃ SỐ SINH VIÊN");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("HỌ VÀ TÊN");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("GIỚI TÍNH");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("EMAIL");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("NGÀY SINH");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("ĐỊA CHỈ");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("MÃ LỚP");

        txtMSSV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMSSV.setMaximumSize(null);

        txtHoten.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtHoten.setMaximumSize(null);

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEmail.setMaximumSize(null);

        txtNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNgaySinh.setMaximumSize(null);

        txaDiaChi.setColumns(20);
        txaDiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txaDiaChi.setRows(5);
        txaDiaChi.setMaximumSize(null);
        jScrollPane1.setViewportView(txaDiaChi);

        buttonGroup1.add(rdbNam);
        rdbNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbNam.setText("NAM");

        buttonGroup1.add(rdbNu);
        rdbNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbNu.setText("NỮ");
        rdbNu.setMaximumSize(null);

        cbbMaLop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbMaLop.setMaximumSize(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHoten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMSSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdbNam)
                                .addGap(18, 18, 18)
                                .addComponent(rdbNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbbMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbNu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        tblSV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblSV.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblSV);

        btnNew.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon - Copy/new-icon-16.png"))); // NOI18N
        btnNew.setText("THÊM MỚI");
        btnNew.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon - Copy/Save-icon.png"))); // NOI18N
        btnSave.setText("LƯU");
        btnSave.setHideActionText(true);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon - Copy/Actions-document-edit-icon-16.png"))); // NOI18N
        btnUpdate.setText("CẬP NHẬT");
        btnUpdate.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon - Copy/Actions-edit-delete-icon-16.png"))); // NOI18N
        btnDelete.setText("XÓA");
        btnDelete.setHideActionText(true);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon - Copy/search-icon-16.png"))); // NOI18N
        btnSearch.setText("TÌM KIẾM");
        btnSearch.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void resetForm() {
        txtMSSV.setText("");
        txtHoten.setText("");
        txtEmail.setText("");
        txtNgaySinh.setText("");
//        if (!rdbNam.isSelected() && !rdbNu.isSelected()) {
//            rdbNam.setSelected(true);
//        }
//        if (rdbNam.isSelected() || !rdbNu.isSelected()) {
//            rdbNam.setSelected(false);
//        } if (!rdbNam.isSelected() || rdbNu.isSelected()) {
//            rdbNu.setSelected(false);
//        }
        txaDiaChi.setText("");
        cbbMaLop.setSelectedIndex(0);
        cbbSearchMSSV.setSelectedIndex(0);
        cbbSearchMaLop.setSelectedIndex(0);
    }


    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        frmMenu menu = new frmMenu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (txtMSSV.getText().equals("") || txtHoten.getText().equals("") || txtNgaySinh.getText().equals("")
                || txtEmail.getText().equals("") || txaDiaChi.getText().equals("")) {
            delTB();
        } else {
            del();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    public void delTB() {
        try {
            ps = conn.prepareStatement("DELETE SINHVIEN WHERE MSSV=?");
            ps.setString(1, tblSV.getValueAt(tblSV.getSelectedRow(), 0).toString());
            if (JOptionPane.showConfirmDialog(this, "BẠN CÓ MUỐN XÓA?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ps.executeUpdate();
                tbn.setRowCount(0);
                loadData();
                JOptionPane.showMessageDialog(this, "XOA THÀNH CÔNG");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "XOA THẤT BẠI");
        }
    }

    public void del() {
        try {
            ps = conn.prepareStatement("DELETE SINHVIEN WHERE MSSV=?");
            ps.setString(1, cbbSearchMSSV.getSelectedItem().toString());
            if (JOptionPane.showConfirmDialog(this, "BẠN CÓ MUỐN XÓA?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ps.executeUpdate();
                tbn.setRowCount(0);
                loadData();
                JOptionPane.showMessageDialog(this, "XOA THÀNH CÔNG");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "XOA THẤT BẠI");
        }
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            ps = conn.prepareStatement("UPDATE SINHVIEN SET HOTEN=?,NGAYSINH=?,GIOITINH=?,EMAIL=?,DIACHI=?,MALOP=? WHERE MSSV=?");
            ps.setString(7, txtMSSV.getText());
            ps.setString(1, txtHoten.getText());
            ps.setString(2, txtNgaySinh.getText());
            if (rdbNam.isSelected()) {
                ps.setBoolean(3, true);
            } else {
                ps.setBoolean(3, false);
            }
            ps.setString(4, txtEmail.getText());
            ps.setString(5, txaDiaChi.getText());
            ps.setString(6, cbbMaLop.getSelectedItem().toString());
            ps.executeUpdate();
            tbn.setRowCount(0);
            loadData();
            JOptionPane.showMessageDialog(this, "CẬP NHẬT THÀNH CÔNG");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed


    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        StringBuilder sb = new StringBuilder();
        if (txtMSSV.getText().equals("") || txtHoten.getText().equals("") || txtNgaySinh.getText().equals("")
                || txtEmail.getText().equals("") || txaDiaChi.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "DỮ LIỆU KHÔNG ĐƯỢC BỎ TRỐNG!");
            checkEmpty(sb);
            HopleMSSV(sb);
            ngaynhap(sb);
            HopleEmail(sb);
            if (sb.length() > 0) {
                JOptionPane.showMessageDialog(this, sb.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                conn = a.getConnection();
                ps = conn.prepareStatement("INSERT INTO SINHVIEN VALUES(?,?,?,?,?,?,?)");
                ps.setString(1, txtMSSV.getText());
                ps.setString(2, txtHoten.getText());
                ps.setString(3, txtNgaySinh.getText());
                if (rdbNam.isSelected()) {
                    ps.setBoolean(4, true);
                } else {
                    ps.setBoolean(4, false);
                }
                ps.setString(5, txtEmail.getText());
                ps.setString(6, txaDiaChi.getText());
                ps.setString(7, cbbMaLop.getSelectedItem().toString());

                int check = ps.executeUpdate();
                if (check > 0) {
                    txaDiaChi.setBackground(Color.white);
                    txtMSSV.setBackground(Color.white);
                    txtHoten.setBackground(Color.white);
                    txtNgaySinh.setBackground(Color.white);
                    txtEmail.setBackground(Color.white);
                    JOptionPane.showMessageDialog(this, "THÊM THÀNH CÔNG!");
                    tbn.setRowCount(0);
                    loadData();//trước khi loadData cần sét số cột về 0 nếu không nó sẽ loadData lên bảng thêm 1 lần nữa
                }
            } catch (Exception e) {
                txtMSSV.setBackground(Color.red);
                JOptionPane.showMessageDialog(this, "MÃ SỐ SINH VIÊN ĐÃ TỒN TẠI!");
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    public void checkEmpty(StringBuilder sb) {
        if (txtMSSV.getText().equals("")) {
            txtMSSV.setBackground(Color.yellow);
            //JOptionPane.showMessageDialog(this, "MSSV CHƯA NHẬP!");
            sb.append("MSSV CHƯA NHẬP!\n");
        } else {
            txtMSSV.setBackground(Color.white);
        }
        if (txtHoten.getText().equals("")) {
            txtHoten.setBackground(Color.yellow);
            //JOptionPane.showMessageDialog(this, "HỌ TÊN CHƯA NHẬP!\n");
            sb.append("HỌ TÊN CHƯA NHẬP!\n");
        } else {
            txtHoten.setBackground(Color.white);
        }
        if (txtNgaySinh.getText().equals("")) {
            txtNgaySinh.setBackground(Color.yellow);
            //JOptionPane.showMessageDialog(this, "NGÀY SINH CHƯA NHẬP!\n");
            sb.append("NGÀY SINH CHƯA NHẬP!\n");
        } else {
            txtNgaySinh.setBackground(Color.white);
        }
        if (txtEmail.getText().equals("")) {
            txtEmail.setBackground(Color.yellow);
            //JOptionPane.showMessageDialog(this, "EMAIL CHƯA NHẬP!\n");
            sb.append("EMAIL CHƯA NHẬP!\n");
        } else {
            txtEmail.setBackground(Color.white);
        }
        if (txaDiaChi.getText().equals("")) {
            txaDiaChi.setBackground(Color.yellow);
            //JOptionPane.showMessageDialog(this, "ĐỊA CHỈ CHƯA NHẬP!\n");
            sb.append("ĐỊA CHỈ CHƯA NHẬP!\n");
        } else {
            txaDiaChi.setBackground(Color.white);
        }
    }

    private void HopleMSSV(StringBuilder sb) {
        String pattern = "B\\d{7}";
        if (!txtMSSV.getText().equals("")) {
            if (!txtMSSV.getText().matches(pattern)) {
                sb.append("MSSV SAI ĐỊNH DẠNG, PHẢI GỒM 1 B VÀ 7 CHỮ SỐ, VD: B1234567\n");
                txtMSSV.setBackground(Color.red);
            } else {
                txtMSSV.setBackground(Color.white);
            }
        }
    }

    public void ngaynhap(StringBuilder sb) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyLocalizedPattern("dd/mm/yyyy");
        if (!txtNgaySinh.getText().equals("")) {
            try {
                Date dod = sdf.parse(txtNgaySinh.getText());
                txtNgaySinh.setBackground(Color.white);
            } catch (Exception e) {
                txtNgaySinh.setBackground(Color.red);
                //JOptionPane.showMessageDialog(this, "NGÀY NHẬP KHÔNG HỢP LỆ, VD: 01/01/2001");
                sb.append("NGÀY NHẬP KHÔNG HỢP LỆ, VD: 01/01/2001");
            }
        }
    }

    private void HopleEmail(StringBuilder sb) {
        String pattern = "^[A-Za-z0-9]+[A-Za-z0-9]*@gmail.com$";
        if (!txtEmail.getText().equals("")) {
            if (!txtEmail.getText().matches(pattern)) {
                sb.append("EMAIL SAI ĐỊNH DẠNG, \nEMAIL PHẢI ĐƯỢC BẮT ĐẦU VỚI “[A-Za-z0-9]” \nVÀ KẾT THÚC BẰNG @gmail.com, VD: hungb1906679@gmail.com\n");
                txtEmail.setBackground(Color.red);
            } else {
                txtEmail.setBackground(Color.white);
            }
        }
    }

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        resetForm();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        search();

    }//GEN-LAST:event_btnSearchActionPerformed

    public void search() {
        try {
            ps = conn.prepareStatement("SELECT MSSV, HOTEN, NGAYSINH = CONVERT(varchar, NGAYSINH, 103), GIOITINH = (CASE GIOITINH WHEN 'TRUE' THEN N'NAM' \n"
                    + "WHEN 'FALSE' THEN N'NỮ' END), EMAIL, DIACHI, MALOP\n"
                    + "FROM SINHVIEN WHERE MSSV=? AND MALOP=?");
//            ps = conn.prepareStatement("SELECT SV.MSSV, SV.HOTEN, HP.TENHP\n"
//                    + "FROM SINHVIEN AS SV, BANGDIEM AS BD, HOCPHAN AS HP\n"
//                    + "WHERE SV.MSSV=BD.MSSV AND HP.MAHP=BD.MAHP");
            ps.setString(1, cbbSearchMSSV.getSelectedItem().toString());
            //ps.setString(2, cbbSearchMaKhoa.getSelectedItem().toString());
            ps.setString(2, cbbSearchMaLop.getSelectedItem().toString());
            rs = ps.executeQuery();
            if (rs.next()) {
                txtMSSV.setText(rs.getString("MSSV"));
                txtHoten.setText(rs.getString("HOTEN"));
                txtNgaySinh.setText(rs.getString("NGAYSINH"));
                String gt = "NAM";
                if (gt.equals(rs.getString("GIOITINH"))) {
                    rdbNam.setSelected(true);
                } else {
                    rdbNu.setSelected(true);
                }
                txtEmail.setText(rs.getString("EMAIL"));
                txaDiaChi.setText(rs.getString("DIACHI"));
                cbbMaLop.setSelectedItem(rs.getString("MALOP"));
            } else {
                JOptionPane.showMessageDialog(this, "KHÔNG TÌM THẤY!");
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
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
            java.util.logging.Logger.getLogger(frmQLSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmQLSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmQLSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmQLSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmQLSV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbMaLop;
    private javax.swing.JComboBox<String> cbbSearchMSSV;
    private javax.swing.JComboBox<String> cbbSearchMaLop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JRadioButton rdbNam;
    private javax.swing.JRadioButton rdbNu;
    private javax.swing.JTable tblSV;
    private javax.swing.JTextArea txaDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtMSSV;
    private javax.swing.JTextField txtNgaySinh;
    // End of variables declaration//GEN-END:variables
}
