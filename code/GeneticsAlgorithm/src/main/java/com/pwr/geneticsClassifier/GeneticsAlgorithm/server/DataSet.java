package com.pwr.geneticsClassifier.GeneticsAlgorithm.server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DataSet {
	private List<DataRow> datarows = new ArrayList<>();
	
	public DataRow getDataRow(int position) {
		return datarows.get(position);
	}
	
	public void addDataRow(DataRow row) {
		datarows.add(row);
	}
	
	public int getNumberOfDataRows() {
		return datarows.size();
	}

	@Override
	public String toString() {
		List<String> rowsAsStrings = datarows.stream().map(m -> m.toString()).collect(Collectors.toList());
		return String.join("\n", rowsAsStrings);
	}
	
}
