
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainGui extends JFrame {
    //Combobox output
    final static String[] languages = { "English", "Deutsch", "Java" };
    final static String[] mode = { "Text", "Random"};
    final static String[] add = { "Ponctuation", "Numbers", "Symbols"};
    final static String[] time = { "15s", "30s", "60s"};
    final static String[] words = { "25", "50", "100"};

    //Varibels
    private static Color MYColor = Color.GRAY;
    private static JTextArea outfield = new JTextArea();
    private static JTextArea infield = new JTextArea();
    private static String selectWords;
    private static String selectLanguage;
    private static java.util.List<String> selectAdd;
    private static TextListener textListener;
    private static int inputTime = 0;
    private static Timer timer;
    private static InputMap inputMap = infield.getInputMap(JComponent.WHEN_FOCUSED);
    private static ActionMap actionMap = infield.getActionMap();
    public static int wordCount;
    private static boolean isStartGenerated = false;
    //Combobox create
    private static JComboBox<String> languagesComboBox = cBox(languages, "language");
    private static JRadioButton greenColor = new JRadioButton();
    private static JRadioButton defaultColor = new JRadioButton();
    //    public static JComboBox<String> modeComboBox = cBox(mode, "mode");
    //public static JComboBox<String> addComboBox = cBox(add, "add");
    private static JComboBox<String> timeComboBox = cBox(time, "time");
    private static JComboBox<String> wordsComboBox = cBox(words, "words");


    private static int min = 0;
    private static int sec = inputTime;

    private static JLabel label = new JLabel( min +" : "+ sec + "s");

    private static String timers = String.format("%02d:%02d", (inputTime % 3600) / 60,inputTime % 60);



    public static void main (String[] args) {

        //Databank inout imitation
        //String text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

        //Main Frame and panel
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        //JPanel mpanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));
        panel.setBackground(new Color(72, 72, 72));

        JPanel radpanel = new JPanel();
        radpanel.setLayout(new BoxLayout(radpanel, BoxLayout.Y_AXIS));

        frame.setLocationRelativeTo(null);

        JList<String> list = new JList<>(add);
        list.setBounds(50,50,50,50);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setBackground(new Color(128,123,123));
        //Save Combobox Value
        //String selectMode = (String) modeComboBox.getSelectedItem();

        String selectTime = (String) timeComboBox.getSelectedItem();


        greenColor.setText("Green");
        defaultColor.setText("Grau");
        greenColor.setBackground(new Color(128,123,123));
        defaultColor.setBackground(new Color(128,123,123));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(greenColor);
        buttonGroup.add(defaultColor);

        greenColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(greenColor.isSelected()){
                    MYColor = Color.GREEN;
                }
            }
        });
        defaultColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(defaultColor.isSelected()){
                    MYColor = Color.GRAY;
                }
            }
        });

        JButton accountButton = new JButton("Reset Score");
        accountButton.setBounds(750, 70, 100,20);
        accountButton.setBackground(new Color(128,123,123));
        accountButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                UserJDBS.reset();
            }
        });

        //Text Fields
        //Output field, where Databank outputs the content
        outfield.setBounds(34,100,900,550);
        outfield.setBackground(new Color(171,171,171));
        outfield.setFont(new Font("Mangal",Font.BOLD,28 ));
        outfield.setLineWrap(true);
        outfield.setWrapStyleWord(true);
        outfield.setTabSize(50);
        outfield.setEditable(false);
        outfield.setBorder(new EmptyBorder(10,10,10,10));
        JScrollPane scrollPane = new JScrollPane(outfield);
        scrollPane.setBounds(34, 100, 900, 600);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //Combobox Words Listener
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                wordsComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        wordsComboBox.requestFocusInWindow();

                        selectWords = (String) wordsComboBox.getSelectedItem();
                        selectAdd = list.getSelectedValuesList();
                        infield.setText("");

                        MyJDBS.base();

                    }
                });

                languagesComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        languagesComboBox.requestFocusInWindow();
                        infield.setText("");

                        selectLanguage = (String) languagesComboBox.getSelectedItem();

                        MyJDBS.base();

                    }
                });
                list.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        selectAdd = list.getSelectedValuesList();
                        infield.setText("");

                        MyJDBS.base();
                    }
                });

            }
        });

        //outfield.setText(text);

        //ScrollPanel

        //
        //String[] temp;
        //User input field
        infield.setBounds(34,450,0,0);
        infield.setBackground(new Color(82,82,82));
        infield.setFont(new Font("Serif",Font.BOLD,20));
        infield.setLineWrap(true);
        infield.getDocument().addDocumentListener(new TextListener(outfield, infield));
        infield.setText("");

        //BUTTONS
        //Start time
        JButton startButton = new JButton("START");
        startButton.setBounds(750, 10, 100,20);
        startButton.setBackground(new Color(128,123,123));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infield.requestFocusInWindow();
            }
        });

        //End timer and Score Frame open
        JButton endButton = new JButton("END");
        endButton.setBounds(750, 40, 100,20);
        endButton.setBackground(new Color(100,71,71));

        // move to score menu
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreGUI.score();
                timer.stop();
            }

        });


        //By pressing 'S' turn on UserInputMode
        InputMap inputMap = infield.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = infield.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "focus");
        actionMap.put("focus", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infield.requestFocusInWindow();


            }
        });

        //https://www.programcreek.com/java-api-examples/javax.swing.ActionMap
        //Forced termination by pressing Esc
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ecs");
        actionMap.put("ecs", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreGUI.score();
                infield.setText("");
                outfield.setText("");
                MyJDBS.base();
                textListener.setIncorrectImport(0);

                timer.stop();
                timers = "00:00";

            }
        });

        //time
        label.setBounds(10,10,10,10);


        infield.getDocument().addDocumentListener(new DocumentListener() {
            //if there is one symbol in infield, timer start
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (infield.getText().length() == 1){
                    inputTime = 0;
                    timer = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            inputTime++;
//                            sec = inputTime;
//                            if (sec > 60) {
//                                sec -= 60;
//                                min++;
//
//                            }

                            timers = String.format("%02d:%02d", (inputTime % 3600) / 60,inputTime % 60);
                            label.setText( timers);
                        }
                    });
                    timer.start();
                    //End of input if all text is entered, Text Update, Timer stop
                } else if ( infield.getText().length() == outfield.getText().length()) {
                    SwingUtilities.invokeLater(new Runnable() {   //https://stackoverflow.com/questions/6567870/what-does-swingutilities-invokelater-do //https://www.delftstack.com/howto/java/java-swingutilities.invokelater/ //chatGPT
                        @Override
                        public void run() {
                            timer.stop();
                            ScoreGUI.score();
                            infield.setText("");
                            MyJDBS.base();
                            timers = "0:0";
                            textListener.setIncorrectImport(0);

                        }
                    });


                }

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (infield.getText().isEmpty()) {
                    timer.stop();
                    timers = "0:0";
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });


        //timer


        //Class Example for ScoreGUI, for correct input from TextListener
        textListener = new TextListener(outfield, infield);
        infield.getDocument().addDocumentListener(textListener);

        //Adding
        panel.add(label);
        panel.add(languagesComboBox);
        //panel.add(modeComboBox);
        //panel.add(addComboBox);
        panel.add(timeComboBox);
        panel.add(wordsComboBox);
        radpanel.add(greenColor);
        radpanel.add(defaultColor);
        panel.add(radpanel);
        panel.add(list);


        frame.add(scrollPane);
        frame.add(infield);
        frame.add(startButton);
        frame.add(endButton);
        frame.add(accountButton);



        frame.add(panel);

        frame.setVisible(true);

    }
    //Combobox create function
    public static JComboBox<String> cBox(String[] items, String defaul){

        JComboBox<String> comboBox = new JComboBox(items);
        comboBox.setBounds(520,120,200,30);
        comboBox.setBackground(new Color(128,123,123));

        comboBox.insertItemAt(defaul, 0);
        comboBox.setSelectedItem(defaul);

        ///////////////

        //////////////

        return comboBox;



    }



    public static int getInputTime() {
        return inputTime;
    }

    public static TextListener getTextListener() {
        return textListener;
    }

//    public static void SUtilites(JComboBox CBinstance, String Sinstance){
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                CBinstance.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        String Sinstance = (String) CBinstance.getSelectedItem();
//                        MyJDBS.base();
//                    }
//                });
//            }
//        });
//
//    }


    public static java.util.List<String> getSelectAdd() {
        return selectAdd;
    }

    public static Color getMYColor() {
        return MYColor;
    }

    public static JTextArea getOutfield() {
        return outfield;
    }

    public static JTextArea getInfield() {
        return infield;
    }

    public static String getSelectWords() {
        return selectWords;
    }

    public static String getSelectLanguage() {
        return selectLanguage;
    }

    public static String getTimers() {
        return timers;
    }
}

