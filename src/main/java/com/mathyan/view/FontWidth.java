package com.mathyan.view;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This is helper class that determines the width of the font
 * for table cells.
 */
public class FontWidth {
    private static String testString = "12:20 13:59 03";
    private static int calculatedFontWidth = 0;

    /**
     * This method returns the width of the font.
     */
    public static int getFontWidth() {
        return calculatedFontWidth;
    }

    /**
     * This method calculates the width of the font.
     */
    public static void calculateFontWidth() {
        Font font = new Font("Arial", Font.PLAIN, 12);

        // Create a temporary BufferedImage to obtain a Graphics context
        BufferedImage tempImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = tempImage.createGraphics();

        FontMetrics metrics = g.getFontMetrics(font);
        calculatedFontWidth = metrics.stringWidth(testString);

        // Dispose of the Graphics context to release resources
        g.dispose();
    }
}
