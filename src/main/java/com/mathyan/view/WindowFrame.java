package com.mathyan.view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class WindowFrame extends JFrame{
    public WindowFrame(){
        super();
        initialize();
    }

    private void initialize() {
        this.setTitle("Scheduler Project");
        this.setSize((FontWidth.getFontWidth() + 20) * 8 + 100, 800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(rootPaneCheckingEnabled);
    }

}
