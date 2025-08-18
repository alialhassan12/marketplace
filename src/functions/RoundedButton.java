package functions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RoundedButton extends JButton {
    private int radius;
    private Color originalColor = null;

    public RoundedButton(int radius) {
        super();
        this.radius = radius;

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (originalColor == null) {
                    originalColor = getBackground();
                }
                setBackground(getBackground().darker()); // hover effect
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (originalColor != null) {
                    setBackground(originalColor);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Use the button’s background color
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        g2.dispose();
        super.paintComponent(g);
    }

}
