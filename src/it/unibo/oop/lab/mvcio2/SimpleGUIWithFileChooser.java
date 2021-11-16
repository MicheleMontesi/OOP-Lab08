package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     */
    private final JFrame frame = new JFrame("New simple GUI from Michi");
    /*
     * constructor
     */
    public SimpleGUIWithFileChooser(final Controller ctrl) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * 
         */
        final JPanel mainPanel = new JPanel();
        final JPanel secondPanel = new JPanel();
        final JTextArea text = new JTextArea();
        final JTextField filePath = new JTextField(ctrl.getFilePath());
        final JButton browse = new JButton("Browse...");
        final JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    ctrl.writeFile(text.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        final LayoutManager layout = new BorderLayout();
        mainPanel.setLayout(layout);
        secondPanel.add(filePath, BorderLayout.CENTER);
        secondPanel.add(browse, BorderLayout.LINE_END);
        mainPanel.add(secondPanel, BorderLayout.NORTH);
        mainPanel.add(text, BorderLayout.CENTER);
        mainPanel.add(save, BorderLayout.SOUTH);
    /*
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     */
        filePath.setEditable(false);
    /* 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     */
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e2) {
                final JFileChooser fileChooser = new JFileChooser("Choose where to save:");
                fileChooser.setSelectedFile(ctrl.getCurrentFile());
                final int result = fileChooser.showSaveDialog(frame);
                switch (result) {
                case JFileChooser.APPROVE_OPTION:
                    final File dest = fileChooser.getSelectedFile();
                    ctrl.setDestination(dest);
                    filePath.setText(dest.getPath());
                    break;
                case JFileChooser.CANCEL_OPTION:
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, result, "Meh!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    /* 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
        frame.setContentPane(mainPanel);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 4, sh / 4);
        frame.setLocationByPlatform(true);
    }
    /*
     * display the GUI
     */
    public void display() {
        frame.setVisible(true);
    }
    /*
     * main
     */
    public static void main(final String[] args) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();
    }
}
