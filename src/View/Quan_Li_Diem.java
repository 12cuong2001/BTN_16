
package View;


import Model.Grade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

public class Quan_Li_Diem extends javax.swing.JFrame {
    
    ArrayList<Grade> list = new ArrayList<>();
    DefaultTableModel model;
    private Connection conn;
    int index = 0;
    public Quan_Li_Diem() {
        initComponents();
        setLocationRelativeTo(null);
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
                model = (DefaultTableModel) tblBang.getModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLSV;user=sa;password=sa");
        } catch (Exception e) {
            System.out.println(e);
        }
        list = (ArrayList<Grade>) getListGrade();
        loadDbToTable();
        loadDataToCbo();
    }
    
    public void loadDataToCbo() {
        try {
            String sql = "SELECT MASV FROM STUDENTS";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
             cboMasv.addItem(rs.getString("MASV"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean selectItem() {
        try {
            String sql = "SELECT * FROM STUDENTS WHERE MASV = ?";
            String ma = (String) cboMasv.getSelectedItem();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                txtMaSV.setText(ma);
                lblHotentxt.setText(rs.getString("Hoten"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<Grade> getListGrade() {
        try {
            String sql = "SELECT TOP 3 STUDENTS.MASV,Hoten,Tienganh,Tinhoc,GDTC,(Tienganh+Tinhoc+GDTC)/3 AS TBM FROM STUDENTS JOIN GRADE\n"
                    + " ON STUDENTS.MASV = GRADE.MASV ORDER BY TBM DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Grade sv = new Grade();
                sv.setMasv(rs.getString("MASV"));
                sv.setName(rs.getString("Hoten"));
                sv.setAnh(Double.parseDouble(rs.getString("Tienganh")));
                sv.setTin(Double.parseDouble(rs.getString("Tinhoc")));
                sv.setGdtc(Double.parseDouble(rs.getString("GDTC")));
                sv.setAvg(Double.parseDouble(rs.getString("TBM")));
                list.add(sv);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void fillTable() {
        model.setRowCount(0);
        for (Grade sv : list) {
            sv.masv = txtMaSV.getText();
            Object[] row = new Object[]{sv.getMasv(), sv.name, sv.getAnh(), sv.getTin(), sv.getGdtc(), sv.getAvg()};
            model.addRow(row);
        }
    }

    public void showDetail(int index) {
       lblHotentxt.setText(list.get(index).getName());
        txtMaSV.setText(list.get(index).getMasv());
        txtTienganh.setText(String.valueOf(list.get(index).getAnh()));
        txtTinhoc.setText(String.valueOf(list.get(index).getTin()));
        txtGDTC.setText(String.valueOf(list.get(index).getGdtc()));
        lblDiemTBtxt.setText(String.valueOf(list.get(index).getAvg()));
    }

    public void loadDbToTable() {
        try {
            model.setRowCount(0);
            String sql = "SELECT TOP 3 STUDENTS.MASV,Hoten,Tienganh,Tinhoc,GDTC,(Tienganh+Tinhoc+GDTC)/3 AS TBM FROM STUDENTS JOIN GRADE\n"
                    + " ON STUDENTS.MASV = GRADE.MASV ORDER BY TBM DESC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString("MASV"));
                 row.add(rs.getString("Hoten"));
                row.add(Double.parseDouble(rs.getString("Tienganh")));
                row.add(Double.parseDouble(rs.getString("Tinhoc")));
                row.add(Double.parseDouble(rs.getString("GDTC")));
                row.add(Double.parseDouble(rs.getString("TBM")));
                model.addRow(row);
            }
            tblBang.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean deleteGrade() {
        try {
            String ma = txtMaSV.getText();
            String sql = "DELETE FROM GRADE WHERE MASV = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateGrade() {

        try {
            String ma = txtMaSV.getText();
            String sql = "UPDATE GRADE SET Tienganh = ?, Tinhoc = ?, GDTC = ? WHERE MASV = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, Double.parseDouble(txtTienganh.getText()));
            ps.setDouble(2, Double.parseDouble(txtTinhoc.getText()));
            ps.setDouble(3, Double.parseDouble(txtGDTC.getText()));
            ps.setString(4, ma);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblMaSV1 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        cboMasv = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        lblHoten = new javax.swing.JLabel();
        lblMaSV2 = new javax.swing.JLabel();
        lblTienganh = new javax.swing.JLabel();
        lblTinhoc = new javax.swing.JLabel();
        lblGDTC = new javax.swing.JLabel();
        lblHotentxt = new javax.swing.JLabel();
        txtMaSV = new javax.swing.JTextField();
        txtTienganh = new javax.swing.JTextField();
        txtTinhoc = new javax.swing.JTextField();
        txtGDTC = new javax.swing.JTextField();
        lblDiemTB = new javax.swing.JLabel();
        lblDiemTBtxt = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnback = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnlast = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lí điểm sinh viên");
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("QUẢN LÍ ĐIỂM SINH VIÊN");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm\n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lblMaSV1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMaSV1.setText("Mã SV:");

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinhanh/search.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        cboMasv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMasvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblMaSV1)
                .addGap(18, 18, 18)
                .addComponent(cboMasv, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnSearch)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaSV1)
                    .addComponent(btnSearch)
                    .addComponent(cboMasv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblHoten.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblHoten.setText("Họ tên SV:");

        lblMaSV2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMaSV2.setText("Mã SV:");

        lblTienganh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTienganh.setText("Tiếng anh:");

        lblTinhoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTinhoc.setText("Tin học:");

        lblGDTC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblGDTC.setText("Giáo dục TC:");

        lblDiemTB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDiemTB.setText("Điểm TB:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMaSV2)
                            .addComponent(lblHoten)
                            .addComponent(lblTienganh)
                            .addComponent(lblTinhoc)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblGDTC)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtGDTC, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTinhoc))
                        .addGap(18, 18, 18)
                        .addComponent(lblDiemTBtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHotentxt, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtTienganh, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(lblDiemTB)
                        .addGap(26, 26, 26))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHoten)
                    .addComponent(lblHotentxt, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaSV2)
                    .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienganh)
                    .addComponent(txtTienganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiemTB))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDiemTBtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTinhoc)
                        .addComponent(txtTinhoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGDTC)
                    .addComponent(txtGDTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btnNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinhanh/new1.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinhanh/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinhanh/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinhanh/update1.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinhanh/back1.png"))); // NOI18N
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinhanh/next2.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnlast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinhanh/next1.png"))); // NOI18N
        btnlast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlastActionPerformed(evt);
            }
        });

        btnnext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinhanh/back2.png"))); // NOI18N
        btnnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnextActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 0, 255));
        jLabel11.setText("3 sinh viên có điểm cao nhất:");

        tblBang.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblBang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã SV", "Họ tên", "Tiếng anh", "Tin học", "GDTC", "TBM"
            }
        ));
        tblBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(54, 54, 54)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnlast, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(20, 20, 20))
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(135, 135, 135)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel11))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlast, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
         this.selectItem();// TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        cboMasv.setSelectedIndex(0);
        lblHotentxt.setText(null);
        txtMaSV.setText(null);
        txtTienganh.setText(null);
        txtTinhoc.setText(null);
        txtGDTC.setText(null);
        lblDiemTBtxt.setText(null);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
                Grade sv = new Grade();
        sv.name = lblHotentxt.getText();
        sv.anh = Double.parseDouble(txtTienganh.getText());
        sv.tin = Double.parseDouble(txtTinhoc.getText());
        sv.gdtc = Double.parseDouble(txtGDTC.getText());
        lblDiemTBtxt.setText(String.valueOf(sv.getAvg()));
        list.add(sv);
        try {
            String sql = "INSERT INTO GRADE(MASV, Tienganh, Tinhoc, GDTC) VALUES ( ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtMaSV.getText());
            ps.setDouble(2, sv.anh);
            ps.setDouble(3, sv.tin);
            ps.setDouble(4, sv.gdtc);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Lưu thành công!");
        } catch (Exception e) {
            System.out.println(e);
        }
        fillTable();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
         index = tblBang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hàng cần cập nhật");
        } else {
            list.remove(index);
            this.updateGrade();
            Grade sv = new Grade();
            sv.anh = Double.parseDouble(txtTienganh.getText());
            sv.tin = Double.parseDouble(txtTinhoc.getText());
            sv.gdtc = Double.parseDouble(txtGDTC.getText());
            lblDiemTBtxt.setText(String.valueOf(sv.getAvg()));
            list.add(sv);
            fillTable();
            JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công!");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
               index = tblBang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn hàng cần xóa");
        } else {
            this.deleteGrade();
            list.remove(index);
            fillTable();
            this.btnNewActionPerformed(evt);
            JOptionPane.showMessageDialog(rootPane, "Đã xóa thành công!");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        index--;
        if (index < 0) {
            index = 0;
        }
        showDetail(index);
    }//GEN-LAST:event_btnbackActionPerformed

    private void btnlastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlastActionPerformed
        index = getListGrade().size() - 1;
        showDetail(index);
    }//GEN-LAST:event_btnlastActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
       showDetail(0);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnextActionPerformed
        index++;
        if (index >= getListGrade().size()) {
            index = getListGrade().size() - 1;
        }
        showDetail(index);
    }//GEN-LAST:event_btnnextActionPerformed

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked
        index = tblBang.getSelectedRow();
        this.showDetail(index);
    }//GEN-LAST:event_tblBangMouseClicked

    private void cboMasvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMasvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMasvActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Quan_Li_Diem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnback;
    private javax.swing.JButton btnlast;
    private javax.swing.JButton btnnext;
    private javax.swing.JComboBox<String> cboMasv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDiemTB;
    private javax.swing.JLabel lblDiemTBtxt;
    private javax.swing.JLabel lblGDTC;
    private javax.swing.JLabel lblHoten;
    private javax.swing.JLabel lblHotentxt;
    private javax.swing.JLabel lblMaSV1;
    private javax.swing.JLabel lblMaSV2;
    private javax.swing.JLabel lblTienganh;
    private javax.swing.JLabel lblTinhoc;
    private javax.swing.JTable tblBang;
    private javax.swing.JTextField txtGDTC;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JTextField txtTienganh;
    private javax.swing.JTextField txtTinhoc;
    // End of variables declaration//GEN-END:variables

}
