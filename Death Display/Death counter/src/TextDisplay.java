import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextDisplay {

    private static int deathcounter;
    private static final String file = "death_counter.txt";

    private static final String firstPart = "You have died ";

    private static final String lastPart = " times so far";

    public static void main(String[] args) {

        // sets the deathcounter to whatever number was saved in the file
        readDeathCounterToFile();

        // Create a JWindow instance
        JFrame window = new JFrame();

        // Create a JLabel with the text you want to display
        String htmlText = String.format("<html><font color='gray'>%s</font><font color='red'>%s</font><font color='gray'>%s</font></html>",
                firstPart,
                deathcounter,
                lastPart
                );
        JLabel label = new JLabel(htmlText);
        int labelWidth = 1000;  // Maximum width in pixels
        label.setPreferredSize(new Dimension(labelWidth, 50));
        label.setMaximumSize(new Dimension(labelWidth, 50));

        //removes window frame adn makes background see through
        window.setUndecorated(true);
        window.setBackground(new Color(0, 0, 0, 0));

        //Color of label
        label.setForeground(Color.GRAY);

        // KeyListener for Shift + P to increment DeathCounter
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                int key = e.getKeyCode();

                switch (key) {
                    case (KeyEvent.VK_P): {
                        deathcounter++;
                        String htmlText1 = String.format("<html><font color='gray'>%s</font><font color='red'>%s</font><font color='gray'>%s</font></html>",
                                firstPart,
                                deathcounter,
                                lastPart
                        );
                        label.setText(htmlText1);
                        break;
                    }
                    case (KeyEvent.VK_O): {
                        if(deathcounter == 0)
                        {
                            break;
                        }
                        deathcounter--;
                        String htmlText1 = String.format("<html><font color='gray'>%s</font><font color='red'>%s</font><font color='gray'>%s</font></html>",
                                firstPart,
                                deathcounter,
                                lastPart
                        );
                        label.setText(htmlText1);
                        break;
                    }
                    case (KeyEvent.VK_L): {
                        window.dispose();
                        writeDeathCounterToFile();
                        break;
                    }
                }
            }
        });

        // Add a window listener to reclaim focus when the window gains focus
        window.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                window.requestFocusInWindow();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                // Optionally, handle when window loses focus
            }
        });

        // Optionally, set the font and other properties of the label
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 50));
        label.setMaximumSize(new Dimension(1000, label.getHeight()));

        // Add the label to the window
        window.getContentPane().add(label);

        // Pack the window to fit the preferred size of the label
        window.pack();

        // Set the location of the window (x, y) on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xCoordinate = (int) screenSize.getWidth() - window.getWidth();
        window.setLocation(xCoordinate+230, 30); // This places the window in the top right corner
        // Make the window visible and request focus
        window.setFocusable(true);
        window.setVisible(true);


        //Put the window always on top
        window.setAlwaysOnTop(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * writes shit to the damn deathCounter file
     */
    private static void writeDeathCounterToFile()
    {
        //tries to make the writer and assign the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(Integer.toString(deathcounter));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * reads shit from the damn deathCounter file
     */
    private static void readDeathCounterToFile()
    {
        //tries to make the reader and assign it the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            deathcounter = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}