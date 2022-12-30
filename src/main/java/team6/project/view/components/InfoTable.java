package team6.project.view.components;

import javax.swing.*;

public class InfoTable extends JTable {

    public InfoTable(final int rows, final int columns) {
        super(rows, columns);
    }

    @Override
    public boolean isCellEditable(final int row, final int column) {
        return column == 1 || column == 3;
    }
}
