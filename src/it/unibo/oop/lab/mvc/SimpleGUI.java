package it.unibo.oop.lab.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();

    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) I has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextField in the upper part of the frame, 
     * a JTextArea in the center and two buttons below it: "Print", and "Show history". 
     * SUGGESTION: Use a JPanel with BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The behavior of the program is that, if "Print" is pressed, the
     * controller is asked to show the string contained in the text field on standard output. 
     * If "show history" is pressed instead, the GUI must show all the prints that
     * have been done to this moment in the text area.
     * 
     */

    /**
     * builds a new {@link SimpleGUI}.
     * 
     * @param ctrl
     *              controller for the GUI.
     */
    public SimpleGUI(final ControllerImpl ctrl) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final List<String> list = new ArrayList<>();
        /*
         * creating frame's element's
         */
        final JPanel mainPanel = new JPanel();
        final JPanel southernPanel = new JPanel();
        final JTextField upperTextField = new JTextField();
        final JTextArea centerTextArea = new JTextArea();
        final JButton printButton = new JButton("Print");
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    ctrl.setNewString(upperTextField.getText());
                    if (!"".equals(ctrl.getNextString())) {
                        list.add(ctrl.getNextString());
                    }
                    centerTextArea.setText(ctrl.getNextString());
                    ctrl.printString();
                    upperTextField.setText(null);
                } catch (IllegalStateException e1) {
                    JOptionPane.showMessageDialog(frame, "You must insert a non null sentence!");
                }
            }
        });
        final JButton historyButton = new JButton("Show History");
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                centerTextArea.setText(list.toString());
            }
        });
        final LayoutManager layout = new BorderLayout();
        /*
         * setting up the frame
         */
        mainPanel.setLayout(layout);
        mainPanel.add(southernPanel, BorderLayout.SOUTH);
        mainPanel.add(upperTextField, BorderLayout.NORTH);
        mainPanel.add(centerTextArea, BorderLayout.CENTER);
        southernPanel.add(printButton, BorderLayout.CENTER);
        southernPanel.add(historyButton, BorderLayout.SOUTH);
        centerTextArea.setEditable(false);
        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        frame.setContentPane(mainPanel);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 4, sh / 4);

        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
    }
    /*
     * 
     */
    public void display() {
        frame.setVisible(true);
    }
    /*
     * 
     */
    public static void main(final String[] args) {
        final SimpleGUI gui = new SimpleGUI(new ControllerImpl());
        gui.display();
    }
}
