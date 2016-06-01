package gol;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;

public class GameOfLife extends javax.swing.JFrame {

    final int wid = 100, hei = 50;
    boolean[][] currentMove = new boolean[hei][wid], nextMove = new boolean[hei][wid];
    boolean play;
    Image offScrImg;
    Graphics offScrGraph;

    public GameOfLife() {
        initComponents();
        offScrImg = createImage(mainScr.getWidth(), mainScr.getHeight());
        offScrGraph = offScrImg.getGraphics();
        Timer time = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                if (play) {
                    for (int i = 0; i < hei; i++) {
                        for (int j = 0; j < wid; j++) {
                            nextMove[i][j] = decide(i, j);
                        }
                    }
                    for (int i = 0; i < hei; i++) {
                        for (int j = 0; j < wid; j++) {
                            currentMove[i][j] = nextMove[i][j];
                        }
                    }
                    repain();
                }
            }
        };
        time.scheduleAtFixedRate(task, 0, 50);
        repain();
    }
    
    private boolean decide(int i, int j){
        int neighbors = 0;
        if(j > 0){
            if(currentMove[i][j-1]) neighbors++;
            if(i>0) if(currentMove[i-1][j-1]) neighbors++;
            if(i<hei-1) if(currentMove[i+1][j-1]) neighbors++;
        }
        if(j < wid-1){
            if(currentMove[i][j+1]) neighbors++;
            if(i>0) if(currentMove[i-1][j+1]) neighbors++;
            if(i<hei-1) if(currentMove[i+1][j+1]) neighbors++;
        }
        if(i>0) if(currentMove[i-1][j]) neighbors++;
        if(i<hei-1) if(currentMove[i+1][j]) neighbors++;
        if(neighbors == 3) return true;
        if(currentMove[i][j] && neighbors == 2) return true;
        return false;
    }
            

    private void repain() {
        offScrGraph.setColor(mainScr.getBackground());
        offScrGraph.fillRect(0, 0, mainScr.getWidth(), mainScr.getHeight());

        for (int i = 0; i < hei; i++) {
            for (int j = 0; j < wid; j++) {
                if (currentMove[i][j]) {
                    offScrGraph.setColor(Color.YELLOW);
                    int x = j * mainScr.getWidth() / wid;
                    int y = i * mainScr.getHeight() / hei;
                    offScrGraph.fillRect(x, y, mainScr.getWidth() / wid, mainScr.getHeight() / hei);

                }
            }
        }

        offScrGraph.setColor(Color.RED);
        for (int i = 1; i < hei; i++) {
            int y = i * mainScr.getHeight() / hei;
            offScrGraph.drawLine(0, y, mainScr.getWidth(), y);
        }
        for (int j = 1; j < wid; j++) {
            int x = j * mainScr.getWidth() / wid;
            offScrGraph.drawLine(x, 0, x, mainScr.getHeight());
        }

        mainScr.getGraphics().drawImage(offScrImg, 0, 0, mainScr);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainScr = new javax.swing.JPanel();
        playBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainScr.setBackground(new java.awt.Color(102, 102, 102));
        mainScr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainScrMouseClicked(evt);
            }
        });
        mainScr.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                mainScrComponentResized(evt);
            }
        });

        javax.swing.GroupLayout mainScrLayout = new javax.swing.GroupLayout(mainScr);
        mainScr.setLayout(mainScrLayout);
        mainScrLayout.setHorizontalGroup(
            mainScrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mainScrLayout.setVerticalGroup(
            mainScrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );

        playBtn.setText("Play");
        playBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playBtnActionPerformed(evt);
            }
        });

        resetBtn.setText("Reset");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainScr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(playBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 351, Short.MAX_VALUE)
                        .addComponent(resetBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainScr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playBtn)
                    .addComponent(resetBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mainScrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainScrMouseClicked
        int j = wid * evt.getX() / mainScr.getWidth();
        int i = hei * evt.getY() / mainScr.getHeight() ;
        currentMove[i][j] = !currentMove[i][j];
        repain();
    }//GEN-LAST:event_mainScrMouseClicked

    private void mainScrComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_mainScrComponentResized
        offScrImg = createImage(mainScr.getWidth(), mainScr.getHeight());
        offScrGraph = offScrImg.getGraphics();
        repain();
    }//GEN-LAST:event_mainScrComponentResized

    private void playBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playBtnActionPerformed
       play = !play;
       if(play) playBtn.setText("Pause");
       else playBtn.setText("Play");
       repain();
    }//GEN-LAST:event_playBtnActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        currentMove = new boolean[hei][wid]; 
        repain();
    }//GEN-LAST:event_resetBtnActionPerformed

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
            java.util.logging.Logger.getLogger(GameOfLife.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameOfLife.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameOfLife.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameOfLife.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameOfLife().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainScr;
    private javax.swing.JButton playBtn;
    private javax.swing.JButton resetBtn;
    // End of variables declaration//GEN-END:variables
}
