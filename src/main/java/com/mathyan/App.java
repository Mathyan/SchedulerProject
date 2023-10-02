package com.mathyan;

import com.mathyan.controller.Controller;
import com.mathyan.model.Model;
import com.mathyan.view.FontWidth;
import com.mathyan.view.View;

/**
 * The main class of the Scheduler application.
 * Initializes the Model, View, and Controller objects and starts the application.
 */
public class App {
    /**
     * The main method initializes the Model, View and Controller objects and starts the application.
     *
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
        FontWidth.calculateFontWidth();
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.start();
    }
}