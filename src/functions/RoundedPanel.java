package functions;

import java.awt.*;
import javax.swing.*;

public class RoundedPanel extends JPanel {
    private int cornerRadius;
    private Image backgroundImage; // optional
    private boolean drawBorder;    // new flag

    // Constructor with only radius, default: draw border
    public RoundedPanel(int radius) {
        this(radius, true);
    }

    // Constructor with radius + drawBorder option
    public RoundedPanel(int radius, boolean drawBorder) {
        super();
        this.cornerRadius = radius;
        this.drawBorder = drawBorder;
        setOpaque(false);
    }

    // Overloaded constructor with radius + image path
    public RoundedPanel(int radius, String imagePath) {
        this(radius, true);
        this.backgroundImage = new ImageIcon(imagePath).getImage();
    }

    // Overloaded constructor with radius + image + drawBorder
    public RoundedPanel(int radius, String imagePath, boolean drawBorder) {
        this(radius, drawBorder);
        this.backgroundImage = new ImageIcon(imagePath).getImage();
    }

    // Overloaded constructor with radius + Image object
    public RoundedPanel(int radius, Image image) {
        this(radius, true);
        this.backgroundImage = image;
    }

    // Overloaded constructor with radius + Image + drawBorder
    public RoundedPanel(int radius, Image image, boolean drawBorder) {
        this(radius, drawBorder);
        this.backgroundImage = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded background
        if (backgroundImage != null) {
            Shape clip = new java.awt.geom.RoundRectangle2D.Float(0, 0, width, height, cornerRadius, cornerRadius);
            graphics.setClip(clip);
            graphics.drawImage(backgroundImage, 0, 0, width, height, this);
            graphics.setClip(null);
        } else {
            graphics.setColor(getBackground());
            graphics.fillRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);
        }

        // Draw border if enabled
        if (drawBorder) {
            graphics.setColor(getForeground());
            graphics.drawRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);
        }
    }

    // Optional: setter to change border visibility at runtime
    public void setDrawBorder(boolean drawBorder) {
        this.drawBorder = drawBorder;
        repaint();
    }

    // Setter for background image
    public void setBackgroundImage(Image image) {
        this.backgroundImage = image;
        repaint();
    }

    // Setter for background image from path
    public void setBackgroundImage(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
        repaint();
    }
}
