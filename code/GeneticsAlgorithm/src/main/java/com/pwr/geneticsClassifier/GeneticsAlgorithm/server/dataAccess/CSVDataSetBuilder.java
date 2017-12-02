package com.pwr.geneticsClassifier.GeneticsAlgorithm.server.dataAccess;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.pwr.geneticsClassifier.GeneticsAlgorithm.server.BasicDataSet;
import com.pwr.geneticsClassifier.GeneticsAlgorithm.server.DataRow;
import com.pwr.geneticsClassifier.GeneticsAlgorithm.server.DataSet;

public class CSVDataSetBuilder {
	private static final String COMMA = ",";
	private DataSet dataSet = new BasicDataSet();
	private Path pathToFile;
	private boolean isSupervisedData = true;
	private int numberOfFeatures;
	private int outputPosition = -1;
	private Set<Integer> labeledColumns = new HashSet<Integer>();
	private Map<String, Double> outputMappedLabels = new HashMap<>();
	
	public CSVDataSetBuilder(int numberOfFeatures) {
		this.numberOfFeatures = numberOfFeatures;
	}
	
	public CSVDataSetBuilder withRelativePath(String filename) {
		pathToFile = Paths.get(System.getProperty("user.dir"), "resources", filename);
		return this;
	}
	
	public CSVDataSetBuilder withDataLabeled(int columnNr) {
		labeledColumns.add(columnNr);
		return this;
	}
	
	public CSVDataSetBuilder withOutputOnPosition(int columnNr) {
		Preconditions.checkElementIndex(columnNr, numberOfFeatures + 1, "index of output must be in range between 0 and number of features");
		outputPosition = columnNr;
		return this;
	}
	
	public CSVDataSetBuilder asUnsupervisedData() {
		this.isSupervisedData = false;
		return this;
	}
	
	
	public DataSet build() {
		Preconditions.checkNotNull(this.pathToFile);
		outputPosition = outputPosition < 0 ? numberOfFeatures : outputPosition;
		outputPosition = isSupervisedData ? outputPosition : -1;
		try (Stream<String> stream = Files.lines(this.pathToFile)) {
			stream.forEach(this::resolveLineIntoDataRow);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataSet;
	}
	
	private void resolveLineIntoDataRow(String line) {
		if (!Strings.isNullOrEmpty(line)) {
			String[] splittedLine = line.split(COMMA);
			int expectedNrOfColumns = isSupervisedData == Boolean.TRUE ? numberOfFeatures + 1 : numberOfFeatures;
			Preconditions.checkArgument(splittedLine.length == expectedNrOfColumns);
			//TODO wczytywanie nie tylko double'i ale też stringów z użyciem labeledColumns
			double[] input = new double[numberOfFeatures];
			double output = -1;
			int counter=0;
			for (int i = 0; i < splittedLine.length; i++) {
				String data = splittedLine[i];
				if (i != outputPosition) {
					Double valueOfData = Double.valueOf(data);
					input[counter++] = valueOfData.doubleValue();
				} else {
					String outputLabel = data;
					if (outputMappedLabels.get(outputLabel) == null) {
						outputMappedLabels.put(outputLabel, (double) outputMappedLabels.keySet().size());
					}
					output = outputMappedLabels.get(outputLabel);
				}
			}
			dataSet.addDataRow(new DataRow(input, output));
		}

	}
}
