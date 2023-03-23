/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class frmQLDSV extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    Connect a = new Connect();
    Connection conn = a.getConnection();
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     * Creates new form frmQLSV
     */
    public frmQLDSV() {
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("FORM QUẢN LÝ ĐIỂM SINH VIÊN");
        loadData();
        loadCombobox();
        updateComboboxMSSV();
        updateComboboxMaHP();
        updateComboboxHKNK();
    }
    
    public void loadCombobox() {
        try {
            conn = a.getConnection();
            ps = conn.prepareStatement("SELECT MSSV FROM BANGDIEM GROUP BY MSSV");
            rs = ps.executeQuery();
            while (rs.next()) {
                cbbMSSV.addItem(rs.getString("MSSV"));
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void loadData() {
        try {
            int number;
            Vector row, column;
            column = new Vector();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM BANGDIEM");
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount(); //trả về số cột

            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i)); //lấy ra tiêu đề của các cột
            }
            model.setColumnIdentifiers(column);

            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                }
                model.addRow(row);
                tblBD.setModel(model);
            }

            tblBD.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (tblBD.getSelectedRow() >= 0) {
                        cbbMSSV.setSelectedItem(tblBD.getValueAt(tblBD.getSelectedRow(), 0) + "");
                        txtHoTen.setText(tblBD.getValueAt(tblBD.getSelectedRow(), 1) + "");
                        txtMaLop.setText(tblBD.getValueAt(tblBD.getSelectedRow(), 2) + "");
                        cbbMaHP.setSelectedItem(tblBD.getValueAt(tblBD.getSelectedRow(), 3) + "");
                        txtTenHP.setText(tblBD.getValueAt(tblBD.getSelectedRow(), 4) + "");
                        txtSTC.setText(tblBD.getValueAt(tblBD.getSelectedRow(), 5) + "");
                        txtDiem.setText(tblBD.getValueAt(tblBD.getSelectedRow(), 6) + "");
                        cbbHK.setSelectedItem(tblBD.getValueAt(tblBD.getSelectedRow(), 7) + "");
                        cbbNK.setSelectedItem(tblBD.getValueAt(tblBD.getSelectedRow(), 8) + "");
                    }
                }
            });
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void updateComboboxMSSV() {
        try {
            String sqlMSSV = "SELECT MSSV FROM SINHVIEN GROUP BY MSSV";
            ps = conn.prepareStatement(sqlMSSV);
            rs = ps.executeQuery();

            //Tạo 1 DefaultComboboxModel
            DefaultComboBoxModel mssvModel = (DefaultComboBoxModel) cbbMSSV.getModel();
            mssvModel.removeAllElements(); //Xóa hết dữ liệu trong combobox
            while (rs.next()) {
                String mssv = rs.getString("MSSV");
                cbbMSSV.addItem(mssv);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    private void updateComboboxMaHP() {
        try {
            String sql = "SELECT MAHP FROM HOCPHAN GROUP BY MAHP";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            //Tạo 1 DefaultComboboxModel
            DefaultComboBoxModel modelMaHP = (DefaultComboBoxModel) cbbMaHP.getModel();
            modelMaHP.removeAllElements(); //Xóa hết dữ liệu trong combobox
            while (rs.next()) {
                String mahp = rs.getString("MAHP");
                cbbMaHP.addItem(mahp);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void updateComboboxHKNK() {
        try {
            String sql = "SELECT HOCKY, NIENKHOA FROM HOCPHAN GROUP BY HOCKY=? AND NIENKHOA=?";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            //Tạo 1 DefaultComboboxModel
            DefaultComboBoxModel modelHK = (DefaultComboBoxModel) cbbHK.getModel();
            DefaultComboBoxModel modelNK = (DefaultComboBoxModel) cbbNK.getModel();
            modelHK.removeAllElements(); //Xóa hết dữ liệu trong combobox
            modelNK.removeAllElements(); //Xóa hết dữ liệu trong combobox
            while (rs.next()) {
                String hk = rs.getString("HOCKY");
                String nk = rs.getString("NIENKHOA");
                cbbMaHP.addItem(hk);
                cbbMaHP.addItem(nk);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.toString());
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

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        txtTenHP = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtMaLop = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSTC = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBD = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtDiem = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        cbbMaHP = new javax.swing.JComboBox<>();
        cbbMSSV = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbbHK = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbbNK = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ ĐIỂM SINH VIÊN");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Thông tin chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel1.setToolTipText("");
        jPanel1.setName(""); // NOI18N

        txtTenHP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("TÊN HỌC PHẦN");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("HỌ VÀ TÊN");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("MÃ LỚP");

        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtMaLop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("SỐ TÍN CHỈ");

        txtSTC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtTenHP))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSTC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenHP, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSTC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblBD.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblBD);

        btnNew.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon - Copy/new-icon-16.png"))); // NOI18N
        btnNew.setText("THÊM MỚI");
        btnNew.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnNew.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon - Copy/Save-icon.png"))); // NOI18N
        btnSave.setText("LƯU");
        btnSave.setDisabledIcon(null);
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon - Copy/Actions-document-edit-icon-16.png"))); // NOI18N
        btnUpdate.setText("CẬP NHẬT");
        btnUpdate.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon - Copy/Actions-edit-delete-icon-16.png"))); // NOI18N
        btnDelete.setText("XÓA");
        btnDelete.setDisabledIcon(null);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExit.setText("THOÁT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon - Copy/search-icon-16.png"))); // NOI18N
        btnSearch.setText("TÌM KIẾM");
        btnSearch.setDisabledIcon(null);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ĐIỂM");

        txtDiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDiem.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "BẢNG TÌM KIẾM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        cbbMaHP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbbMSSV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("MÃ SỐ SINH VIÊN");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("MÃ HỌC PHẦN");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("HỌC KỲ");

        cbbHK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbHK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("NIÊN KHÓA");

        cbbNK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbNK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020-2021", "2021-2022", "2022-2023", "2023-2024" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbbMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbbMaHP, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbbHK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbbNK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbHK, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbNK, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbMaHP, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addComponent(jSeparator3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        resetForm();
    }//GEN-LAST:event_btnNewActionPerformed

    public void resetForm() {
        txtHoTen.setText("");
        txtMaLop.setText("");
        txtTenHP.setText("");
        txtSTC.setText("");
        txtDiem.setText("");
        cbbMSSV.setSelectedIndex(0);
        cbbMaHP.setSelectedIndex(0);
        cbbHK.setSelectedIndex(0);
        cbbNK.setSelectedIndex(0);
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        StringBuilder sb = new StringBuilder();
        if (txtHoTen.getText().equals("") || txtMaLop.getText().equals("") || txtTenHP.getText().equals("")
                || txtSTC.getText().equals("") || txtDiem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "DỮ LIỆU KHÔNG ĐƯỢC BỎ TRỐNG!");
            checkEmpty(sb);
            if (sb.length() > 0) {
                JOptionPane.showMessageDialog(this, sb.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                if (txtHoTen.getText().equals("") || txtMaLop.getText().equals("") || txtTenHP.getText().equals("")
                        || txtSTC.getText().equals("") || txtDiem.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "DỮ LIỆU KHÔNG ĐƯỢC BỎ TRỐNG!");
                } else {
                    ps = conn.prepareStatement("INSERT INTO BANGDIEM VALUES(?,?,?,?,?,?,?,?,?)");
                    ps.setString(1, cbbMSSV.getSelectedItem().toString());
                    ps.setString(2, txtHoTen.getText());
                    ps.setString(3, txtMaLop.getText());
                    ps.setString(4, cbbMaHP.getSelectedItem().toString());
                    ps.setString(5, txtTenHP.getText());
                    ps.setString(6, txtSTC.getText());
                    ps.setString(7, txtDiem.getText());
                    ps.setString(8, cbbHK.getSelectedItem().toString());
                    ps.setString(9, cbbNK.getSelectedItem().toString());
                    int check = ps.executeUpdate();
                    if (check > 0) {
                        txtHoTen.setBackground(Color.white);
                        txtMaLop.setBackground(Color.white);
                        txtSTC.setBackground(Color.white);
                        txtTenHP.setBackground(Color.white);
                        JOptionPane.showMessageDialog(this, "THÊM THÀNH CÔNG!");
                        model.setRowCount(0);
                        txtDiem.setBackground(Color.white);
                        loadData();//trước khi loadData cần sét số cột về 0 nếu không nó sẽ loadData lên bảng thêm 1 lần nữa
                    }
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    public void checkEmpty(StringBuilder sb) {
        if (txtHoTen.getText().equals("")) {
            txtHoTen.setBackground(Color.yellow);
            //JOptionPane.showMessageDialog(this, "HỌ TÊN CHƯA NHẬP!\n");
            sb.append("HỌ TÊN KHÔNG ĐƯỢC RỖNG!\n");
        } else {
            txtHoTen.setBackground(Color.white);
        }
        if (txtMaLop.getText().equals("")) {
            txtMaLop.setBackground(Color.yellow);
            //JOptionPane.showMessageDialog(this, "NGÀY SINH CHƯA NHẬP!\n");
            sb.append("NGÀY SINH KHÔNG ĐƯỢC RỖNG!\n");
        } else {
            txtMaLop.setBackground(Color.white);
        }
        if (txtTenHP.getText().equals("")) {
            txtTenHP.setBackground(Color.yellow);
            //JOptionPane.showMessageDialog(this, "EMAIL CHƯA NHẬP!\n");
            sb.append("EMAIL KHÔNG ĐƯỢC RỖNG!\n");
        } else {
            txtTenHP.setBackground(Color.white);
        }
        if (txtSTC.getText().equals("")) {
            txtSTC.setBackground(Color.yellow);
            //JOptionPane.showMessageDialog(this, "ĐỊA CHỈ CHƯA NHẬP!\n");
            sb.append("STC KHÔNG ĐƯỢC RỖNG!\n");
        } else {
            txtSTC.setBackground(Color.white);
        }
        if (txtDiem.getText().equals("")) {
            txtDiem.setBackground(Color.yellow);
            //JOptionPane.showMessageDialog(this, "ĐỊA CHỈ CHƯA NHẬP!\n");
            sb.append("ĐIỂM KHÔNG ĐƯỢC RỖNG!\n");
        } else {
            txtDiem.setBackground(Color.white);
        }
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            ps = conn.prepareStatement("UPDATE BANGDIEM SET HOTEN=?,MALOP=?,TENHP=?,STC=?,DIEMTB=? WHERE MSSV=? AND MAHP=? AND HOCKY=? AND NIENKHOA=?");
            ps.setString(6, cbbMSSV.getSelectedItem().toString());
            ps.setString(7, cbbMaHP.getSelectedItem().toString());
            ps.setString(8, cbbHK.getSelectedItem().toString());
            ps.setString(9, cbbNK.getSelectedItem().toString());
            ps.setString(1, txtHoTen.getText());
            ps.setString(2, txtMaLop.getText());
            ps.setString(3, txtTenHP.getText());
            ps.setString(4, txtSTC.getText());
            ps.setString(5, txtDiem.getText());
            ps.executeUpdate();
            model.setRowCount(0);
            loadData();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (txtHoTen.getText().equals("") || txtSTC.getText().equals("") || txtMaLop.getText().equals("")
                || txtDiem.getText().equals("") || txtTenHP.getText().equals("")) {
            del();
        } else {
            del1();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    public void del() {
        try {
            ps = conn.prepareStatement("DELETE BANGDIEM WHERE MSSV=? AND MAHP=? AND HOCKY=? AND NIENKHOA=?");
            ps.setString(1, tblBD.getValueAt(tblBD.getSelectedRow(), 0).toString());
            ps.setString(2, tblBD.getValueAt(tblBD.getSelectedRow(), 3).toString());
            ps.setString(3, tblBD.getValueAt(tblBD.getSelectedRow(), 7).toString());
            ps.setString(4, tblBD.getValueAt(tblBD.getSelectedRow(), 8).toString());
            if (JOptionPane.showConfirmDialog(this, "BẠN CÓ MUỐN XÓA?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ps.executeUpdate();
                model.setRowCount(0);
                loadData();
                JOptionPane.showMessageDialog(this, "XOA THÀNH CÔNG");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "XOA THẤT BẠI");
        }
    }
    
    public void del1() {
        try {
            ps = conn.prepareStatement("DELETE BANGDIEM WHERE MSSV=? AND MAHP=? AND HOCKY=? AND NIENKHOA=?");
            ps.setString(1, cbbMSSV.getSelectedItem().toString());
            ps.setString(2, cbbMaHP.getSelectedItem().toString());
            ps.setString(3, cbbHK.getSelectedItem().toString());
            ps.setString(4, cbbNK.getSelectedItem().toString());
            if (JOptionPane.showConfirmDialog(this, "BẠN CÓ MUỐN XÓA?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ps.executeUpdate();
                model.setRowCount(0);
                loadData();
                JOptionPane.showMessageDialog(this, "XOA THÀNH CÔNG");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "XOA THẤT BẠI");
        }
    }

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        frmMenu menu = new frmMenu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchMSSV();
        searchMAHP();
    }//GEN-LAST:event_btnSearchActionPerformed

    public void searchMSSV() {
        try {
            conn = a.getConnection();
            ps = conn.prepareStatement("SELECT * FROM SINHVIEN WHERE MSSV=?");
//            ps = conn.prepareStatement("SELECT SV.MSSV, SV.HOTEN, HP.TENHP\n"
//                    + "FROM SINHVIEN AS SV, BANGDIEM AS BD, HOCPHAN AS HP\n"
//                    + "WHERE SV.MSSV=BD.MSSV AND HP.MAHP=BD.MAHP");
            ps.setString(1, cbbMSSV.getSelectedItem().toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                txtHoTen.setText(rs.getString("HOTEN"));
                txtMaLop.setText(rs.getString("MALOP"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void searchMAHP() {
        try {
            conn = a.getConnection();
            ps = conn.prepareStatement("SELECT * FROM HOCPHAN WHERE MAHP=?");
            ps.setString(1, cbbMaHP.getSelectedItem().toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                txtTenHP.setText(rs.getString("TENHP"));
                txtSTC.setText(rs.getString("STC"));
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
            java.util.logging.Logger.getLogger(frmQLDSV.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmQLDSV.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmQLDSV.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmQLDSV.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmQLDSV().setVisible(true);
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
    private javax.swing.JComboBox<String> cbbHK;
    private javax.swing.JComboBox<String> cbbMSSV;
    private javax.swing.JComboBox<String> cbbMaHP;
    private javax.swing.JComboBox<String> cbbNK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable tblBD;
    private javax.swing.JTextField txtDiem;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaLop;
    private javax.swing.JTextField txtSTC;
    private javax.swing.JTextField txtTenHP;
    // End of variables declaration//GEN-END:variables
}
