package com.mathyan.view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ScheduleTable extends JTable {
    private static String[] columnNames = {
            "Name", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };
    private String[][] tableData;
    private Integer week;

    public ScheduleTable(String[][] tableData, Integer week) {
        super(tableData, columnNames);
        this.tableData = tableData;
        this.week = week;
        this.setPreferredSize(new Dimension((FontWidth.getFontWidth() + 20) * 8, 600));
        this.setRowHeight(40);

        for (int i = 0; i < getColumnModel().getColumnCount(); i++) {
            getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                        boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    int width = FontWidth.getFontWidth();
                    table.getColumnModel().getColumn(column).setPreferredWidth(width);
                    return c;
                }
            });
        }
    }

    public void updateTableData(String[][] tableData, Integer week) {
        this.tableData = tableData;
        this.week = week;
        this.repaint();
    }

    public String[][] getTableData() {
        return tableData;
    }

    public Integer getWeek() {
        return week;
    }

    public void setCurrentWeek(int currentWeek) {
        this.week = currentWeek;
    }
}
