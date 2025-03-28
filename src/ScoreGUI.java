
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class ScoreGUI {
    private static JTextArea input = new JTextArea();
    private static float spm;
    public ScoreGUI() {
    }

    public static void score() {
        TextListener h = MainGui.getTextListener();
        int incorrect = h.getIncorrectImport();
        int totalTimeSeconds = MainGui.getInputTime();
        float te = (float)(MainGui.getInfield().getText().length() - incorrect);
        spm = te / (float)totalTimeSeconds * 60.0F;
        spm = (float)Math.round(spm * 100.0F) / 100.0F;
        UserJDBS.record();
        float wpm = spm / 5.0F;

        //Frame and Esc Taste work
        final JFrame frame = new JFrame();
        frame.setSize(1000, 850);
        frame.getContentPane().setBackground(new Color(72, 72, 72));
        input.setBounds(10, 10, 2, 2);
        input.requestFocusInWindow();
        InputMap inputMap = input.getInputMap(2);
        ActionMap actionMap = input.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(27, 0), "ecs");
        actionMap.put("ecs", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        //All Show configuration
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.setBackground(new Color(72, 72, 72));

        //Score Panel with all text
        JPanel scoreSection = new JPanel();
        scoreSection.setLayout(new BoxLayout(scoreSection, 1));
        scoreSection.setBackground(new Color(72, 72, 72));

        JLabel scoreLabel = new JLabel("SCORE", 0);
        scoreLabel.setFont(new Font("Serif", 1, 60));
        scoreLabel.setAlignmentX(0.5F);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout(1, 50, 20));
        scorePanel.setBackground(new Color(72, 72, 72));
        scorePanel.add(createSquarePanel("SPM:  " + spm + "<br>WPM: " + wpm));
        scorePanel.add(createSquarePanel("Incorrect: " + incorrect + "<br>Correct: " + (MainGui.getInfield().getText().length() - incorrect)));
        scorePanel.add(createSquarePanel("Time: " + MainGui.getTimers()));
        scoreSection.add(Box.createVerticalStrut(20));
        scoreSection.add(scoreLabel);
        scoreSection.add(Box.createVerticalStrut(50));
        scoreSection.add(scorePanel);

        //Record field with spm from UserJDBS Class
        JPanel recordSection = new JPanel();
        recordSection.setLayout(new BoxLayout(recordSection, 1));
        recordSection.setBackground(new Color(72, 72, 72));
        JLabel recordLabel = new JLabel("RECORD", 0);
        recordLabel.setFont(new Font("Serif", 1, 60));
        recordLabel.setAlignmentX(0.5F);

        JPanel recordPanel = new JPanel();
        recordPanel.setLayout(new FlowLayout(1, 50, 20));
        recordPanel.setBackground(new Color(72, 72, 72));
        recordPanel.add(createSquarePanel("Best spm  " + UserJDBS.default_spm));
        recordPanel.add(createSquarePanel("The least number of Incorrect: <br>Best Correct: "));
        recordPanel.add(createSquarePanel("Best Time: "));
        recordSection.add(Box.createVerticalStrut(20));
        recordSection.add(recordLabel);
        recordSection.add(Box.createVerticalStrut(50));
        recordSection.add(recordPanel);

        panel.add(scoreSection);
        panel.add(recordSection);

        frame.add(input);
        frame.add(panel);
        frame.setVisible(true);
    }
    //Create Square with Score text
    private static JPanel createSquarePanel(String text) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(166, 166, 166));
        panel.setPreferredSize(new Dimension(200, 100));
        panel.setLayout(new GridBagLayout());
        JLabel label = new JLabel("<html>" + text + "</html>", 0);
        panel.add(label);
        return panel;
    }
    public static float getSpm() {
        return spm;
    }

}
