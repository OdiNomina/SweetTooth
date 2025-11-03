package com.github.sweettooth.viewSwing.session;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import com.github.sweettooth.model.api.gameSession.ScoreProvider.ScoreData;

public class ScoreTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private final List<ScoreData> scoreList;
    private final String[] columns = {"Rang", "Name", "Score"};

    public ScoreTableModel() {
        scoreList = new ArrayList<ScoreData>();
    }
	
	@Override
	public int getRowCount() {
		return scoreList.size();
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
		ScoreData entry = scoreList.get(rowIndex);
        return switch(columnIndex) {
            case 0 -> rowIndex + 1;
            case 1 -> entry.name();
            case 2 -> entry.score();
            default -> null;
        };
	}
	
	void updateScores(List<ScoreData> scores) {
		scoreList.clear();
		scoreList.addAll(scores);
		fireTableDataChanged();
	}
}
