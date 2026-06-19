package com.github.sweettooth.viewSwing.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import com.github.sweettooth.model.api.gameSession.ScoreProvider.ScoreData;

public class ScoreTableModel extends AbstractTableModel {
	private static final long serialVersionUID = Long.valueOf(1L);
	
	private final Locale locale;
	private final List<ScoreData> scoreList = new ArrayList<ScoreData>();
    private final String[] columns = {"Rang", "Name", "Score"};

    public ScoreTableModel(Locale l) {
    	locale = l;
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
            case 2 -> toLocaleString(entry.score(), locale);
            default -> null;
        };
	}
	
	void updateScores(List<ScoreData> scores) {
		scoreList.clear();
		scoreList.addAll(scores);
		fireTableDataChanged();
	}
	
	/*
	 * %[flags][.precision]conversion
	 * Flag ',': The result will include locale-specific grouping separators.
	 * Conversion 'f': The result is formatted as a decimal number.
	 */
	
	/**
	 * The result contains locale-specific grouping separators and is formatted as a decimal number.
	 * @param l the currently valid local
	 * @return Double as a string with local formatting
	 */
	private String toLocaleString(Double d, Locale l) {
		if(d == 0) return "";
		
		return String.format(l, "%,.2f", Math.round(d*100)/100.0);
	}
}
