package com.mathyan.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPanel extends JPanel {
    JScrollPane scrollPane;
    ScheduleTable scheduleTable;

    public MainPanel() {
        initialize();
        addItemsToPanel();
    }


    private void initialize() {
        this.setVisible(true);
        scheduleTable = new ScheduleTable(new String[][]{
            {"John Doe", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00"},
            {"Jane Smith", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "12:21"}
        }, 1);
        scheduleTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane = new JScrollPane(scheduleTable);
        scrollPane.setPreferredSize(new Dimension(scheduleTable.getPreferredSize().width + 20, 600));
        scheduleTable.getTableHeader().setReorderingAllowed(false);
    }

    private void addItemsToPanel() {
        JPanel centarPanel = new JPanel( new FlowLayout(FlowLayout.CENTER));
        centarPanel.add(scrollPane);
        this.setLayout(new BorderLayout());
        this.add(centarPanel, BorderLayout.CENTER);
    }
}

