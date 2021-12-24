package vn.edu.hcmus.student.sv19127048.lab05.Game;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * vn.edu.hcmus.student.sv19127048.lab05.Game<br> Created by 19127048 - Nguyen Duc Nam<br> Date
 * 12/13/2021 - 7:52 PM<br> Description: JDK16<br>
 */
public class GameView extends JFrame implements ActionListener {

  /**
   * Creates new form GameFrame
   */
  public GameView() {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">
  private void initComponents() {

    slangWordLabel = new JLabel();
    answer1 = new JButton();
    answer2 = new JButton();
    answer3 = new JButton();
    answer4 = new JButton();

    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Guess Game");

    slangWordLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
    slangWordLabel.setHorizontalAlignment(SwingConstants.CENTER);

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(answer2, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
                            .addComponent(answer1, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
                            .addComponent(answer3, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
                            .addComponent(answer4, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(slangWordLabel, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137))))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(slangWordLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(answer1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(answer2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(answer3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(answer4, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>

  /**
   * Render game window
   */
  public void renderGameWindow() {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(GameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    EventQueue.invokeLater(() -> setVisible(true));
  }

  public JButton getAnswer1() {
    return answer1;
  }

  public JButton getAnswer2() {
    return answer2;
  }

  public JButton getAnswer3() {
    return answer3;
  }

  public JButton getAnswer4() {
    return answer4;
  }

  public JLabel getSlangWordLabel() {
    return slangWordLabel;
  }

  // Variables declaration - do not modify
  private JButton answer1;
  private JButton answer2;
  private JButton answer3;
  private JButton answer4;
  private JLabel slangWordLabel;

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == answer1) {
      System.out.println("Clicked answer 1");
    } else if (e.getSource() == answer2) {
      System.out.println("Clicked answer 2");
    } else if (e.getSource() == answer3) {
      System.out.println("Clicked answer 3");
    } else {
      System.out.println("Clicked answer 4");
    }
  }
  // End of variables declaration
}