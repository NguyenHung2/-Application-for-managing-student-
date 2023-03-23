/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.awt.Color;
import java.io.File;
import java.io.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 *
 * @author HP
 */
public class frmMusic extends javax.swing.JFrame {

    dataMusicList pl = new dataMusicList();

    ArrayList updateList = new ArrayList();

    javazoom.jl.player.Player player;
    File simpan;

    public frmMusic() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("music-icon.png")).getImage());
    }

    void updateList() {
        updateList = pl.getListSong();
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < updateList.size(); i++) {
            int j = i + 1;
            model.add(i, j + " | " + ((File) updateList.get(i)).getName());
        }
        jPlaylist.setModel(model);

    }

//panel kontrol
    void add() {
        pl.add(this);
        updateList();
    }

    void remove() {
        try {
            int akandihapus = jPlaylist.getLeadSelectionIndex();
            pl.musicList.remove(akandihapus);
            updateList();
        } catch (Exception e) {
        }
    }

    void up() {
        try {
            int s1 = jPlaylist.getLeadSelectionIndex();
            simpan = (File) pl.musicList.get(s1);
            pl.musicList.remove(s1);
            pl.musicList.add(s1 - 1, simpan);
            updateList();
            jPlaylist.setSelectedIndex(s1 - 1);
        } catch (Exception e) {
        }
    }

    void down() {
        try {
            int s1 = jPlaylist.getLeadSelectionIndex();
            simpan = (File) pl.musicList.get(s1);
            pl.musicList.remove(s1);
            pl.musicList.add(s1 + 1, simpan);
            updateList();
            jPlaylist.setSelectedIndex(s1 + 1);
        } catch (Exception e) {
        }
    }

    void open() {
        pl.openPls(this);
        updateList();
    }

    void save() {
        pl.saveAsPlaylist(this);
        updateList();
    }

    File play1;
    static int a = 0;

    void putar() {
        if (a == 0) {
            try {
                int p1 = jPlaylist.getSelectedIndex();
                play1 = (File) this.updateList.get(p1);
                FileInputStream fis = new FileInputStream(play1);
                BufferedInputStream bis = new BufferedInputStream(fis);
                player = new javazoom.jl.player.Player(bis);
                a = 1;
            } catch (Exception e) {
                System.out.println("Problem playing file");
                System.out.println(e);
            }

            new Thread() {
                @Override
                public void run() {
                    try {
                        player.play();

                    } catch (Exception e) {
                    }
                }
            }.start();
        } else {
            player.close();

            a = 0;
            putar();
        }
    }

    File sa;

    void next() {
        if (a == 0) {
            try {
                int s1 = jPlaylist.getSelectedIndex() + 1;
                sa = (File) this.pl.musicList.get(s1);
                FileInputStream fis = new FileInputStream(sa);
                BufferedInputStream bis = new BufferedInputStream(fis);
                player = new javazoom.jl.player.Player(bis);
                a = 1;
                jPlaylist.setSelectedIndex(s1);
            } catch (Exception e) {
                System.out.println("Problem playing file");
                System.out.println(e);
            }

            new Thread() {
                @Override
                public void run() {
                    try {
                        player.play();

                    } catch (Exception e) {
                    }
                }
            }.start();
        } else {
            player.close();
            a = 0;
            next();
        }

    }

    void previous() {
        if (a == 0) {
            try {
                int s1 = jPlaylist.getSelectedIndex() - 1;
                sa = (File) this.pl.musicList.get(s1);
                FileInputStream fis = new FileInputStream(sa);
                BufferedInputStream bis = new BufferedInputStream(fis);
                player = new javazoom.jl.player.Player(bis);
                a = 1;
                jPlaylist.setSelectedIndex(s1);
            } catch (Exception e) {
                System.out.println("Problem playing file");
                System.out.println(e);
            }

            new Thread() {
                @Override
                public void run() {
                    try {
                        player.play();

                    } catch (Exception e) {
                    }
                }
            }.start();

        } else {
            player.close();
            a = 0;
            previous();
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

        btnAdd = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnUp = new javax.swing.JButton();
        btnDown = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPlaylist = new javax.swing.JList<>();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        ply = new javax.swing.JButton();
        stop = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/p_add.png"))); // NOI18N
        btnAdd.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/p_add.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 80, -1));

        btnRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/p_remove.png"))); // NOI18N
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 59, 80, -1));

        btnUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/p_up.png"))); // NOI18N
        btnUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpActionPerformed(evt);
            }
        });
        getContentPane().add(btnUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 80, -1));

        btnDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/p_down.png"))); // NOI18N
        btnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownActionPerformed(evt);
            }
        });
        getContentPane().add(btnDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 80, -1));

        jScrollPane1.setViewportView(jPlaylist);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 127, 374, 100));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/stop-icon_1.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 80, -1));

        jButton6.setText("| <");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 257, 80, -1));

        jButton7.setText("> |");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 257, 80, -1));

        ply.setText("PLAY");
        ply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plyActionPerformed(evt);
            }
        });
        getContentPane().add(ply, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 257, 80, -1));

        stop.setText("[]");
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });
        getContentPane().add(stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 257, 80, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1e64d86c-c498-442c-95ba-6652785a04d9.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 313, -1, 116));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Music-Library-icon.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 313, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        add();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        remove();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpActionPerformed
        up();
    }//GEN-LAST:event_btnUpActionPerformed

    private void btnDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownActionPerformed
        down();
    }//GEN-LAST:event_btnDownActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        previous();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        next();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void plyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plyActionPerformed
        putar();

        ply.setBackground(Color.GREEN);
        stop.setBackground(Color.white);// TODO add your handling code here:
    }//GEN-LAST:event_plyActionPerformed

    private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
        player.close();
        stop.setBackground(Color.red);
        ply.setBackground(Color.white);// TODO add your handling code here:
    }//GEN-LAST:event_stopActionPerformed

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
            java.util.logging.Logger.getLogger(frmMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMusic().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDown;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUp;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jPlaylist;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton ply;
    private javax.swing.JButton stop;
    // End of variables declaration//GEN-END:variables
}
