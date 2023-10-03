package com.mathyan.view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ScheduleTable extends JTable {
	private static String[] columnNames = {
			"Name", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
	};
	private String[][] tableData;
	private Integer week;
	private DefaultTableModel tableModel;
	private boolean isEditable;

	public ScheduleTable(String[][] tableData, Integer week) {
		tableModel = new DefaultTableModel(tableData, columnNames);
		this.setModel(tableModel);
		this.tableData = tableData;
		this.week = week;
		this.setPreferredSize(new Dimension((FontWidth.getFontWidth() + 20) * 8, 600));
		this.setRowHeight(40);
		setEditable(false);

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
		this.tableModel.setDataVector(tableData, columnNames);
		this.tableData = tableData;
		this.week = week;
		this.repaint();
		this.revalidate();
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

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
		this.setRowSelectionAllowed(isEditable);
		this.setColumnSelectionAllowed(isEditable);
		this.setCellSelectionEnabled(isEditable);
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			for (int j = 0; j < tableModel.getColumnCount(); j++) {
				tableModel.isCellEditable(i, j);
				tableModel.setValueAt(tableModel.getValueAt(i, j), i, j);
			}
		}
	}

	public boolean isEditable() {
		return isEditable;
	}
}
