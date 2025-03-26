
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class TextListener implements DocumentListener {
    private JTextArea outfield;
    private JTextArea infield;
    private int i = 0;
    private int incorrect;
    private int previousIncorrect;
    private String userInput;
    private int incorrectImport;

    public void setIncorrectImport(int incorrectImport) {
        this.incorrectImport = incorrectImport;
    }

    public TextListener(JTextArea outfield, JTextArea infield) {
        this.outfield = outfield;
        this.infield = infield;
        this.incorrect = 0;
    }

    public void insertUpdate(DocumentEvent e) {
        this.colorTextChange();
    }

    public void removeUpdate(DocumentEvent e) {
        this.colorTextChange();
    }

    public void changedUpdate(DocumentEvent e) {
        this.colorTextChange();
    }

    public void colorTextChange() {
        String mainText = this.outfield.getText();
        this.userInput = this.infield.getText();
        this.incorrect = 0;
        Highlighter highlighter = this.outfield.getHighlighter();
        highlighter.removeAllHighlights();

        for(this.i = 0; this.i < this.userInput.length() && this.i < mainText.length(); ++this.i) {
            if (this.userInput.charAt(this.i) != mainText.charAt(this.i)) {
                ++this.incorrect;

                try {
                    highlighter.addHighlight(this.i, this.i + 1, new DefaultHighlighter.DefaultHighlightPainter(Color.RED));
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }

            if (this.userInput.charAt(this.i) == mainText.charAt(this.i)) {
                try {
                    highlighter.addHighlight(this.i, this.i + 1, new DefaultHighlighter.DefaultHighlightPainter(MainGui.getMYColor()));
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (this.incorrect > this.previousIncorrect) {
            ++this.incorrectImport;
        }

        this.previousIncorrect = this.incorrect;
    }

    public int getIncorrectImport() {
        return this.incorrectImport;
    }
}
