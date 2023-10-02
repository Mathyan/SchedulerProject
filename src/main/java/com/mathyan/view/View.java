package com.mathyan.view;

/**
 * This class represents the view of the application.
 * <p>
 * App is JSwing application.
 * <p>
 */
public class View {

    WindowFrame windowFrame;
    /**
     * Constructs a View object.
     */
    public View() {
        initialize();
    }

    /**
     * Initializes the swing window.
     */
    private void initialize() {
        windowFrame = new WindowFrame();
    }




}
