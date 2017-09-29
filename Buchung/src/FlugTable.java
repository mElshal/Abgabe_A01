import java.util.List;

import javax.swing.table.AbstractTableModel;

class FlugTable extends AbstractTableModel {
	private static final int AIRLINE_COL = 0;
	private static final int FLIGHTNR_COL = 1;
	private static final int DEPATURE_COL = 2;
	private static final int DEPATURE_AIRPORT_COL = 3;
	private static final int DESTINATION_COL = 4;
	private static final int DESTINATION_AIRPORT_COL = 5;
	
	private String[] columnNames = {"Airline", "Flightnr", "Depature Time", "Depature Airport", 
			"Destination Time", "Destination Airport"};
	private List liste;
	public FlugTable(List liste) {
		this.liste = liste;
	}
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return liste.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		
		return null;
	}

}
