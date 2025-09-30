package com.github.sweettooth.viewSwing.views;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import com.github.sweettooth.model.api.viewAPI.ScoreProvider.ScoreData;

public class ScoreTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private final List<ScoreData> scores;
    private final String[] columns = {"Rang", "Name", "Score"};

    public ScoreTableModel(List<ScoreData> scores) {
        this.scores = scores;
    }
	
	@Override
	public int getRowCount() {
		return scores.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
    public String getColumnName(int column) {
		return columns[column];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ScoreData entry = scores.get(rowIndex);
        return switch(columnIndex) {
            case 0 -> rowIndex + 1;
            case 1 -> entry.name();
            case 2 -> entry.score();
            default -> null;
        };
	}
}
