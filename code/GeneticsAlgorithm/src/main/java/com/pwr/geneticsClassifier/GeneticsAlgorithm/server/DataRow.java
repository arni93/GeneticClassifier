package com.pwr.geneticsClassifier.GeneticsAlgorithm.server;

import java.util.ArrayList;
import java.util.List;

public class DataRow {

	private final double[] input;
	private final double output;

	public DataRow(double[] input, double output) {
		this.input = input;
		this.output = output;
	}

	public double[] getInput() {
		return input;
	}

	public double getOutput() {
		return output;
	}

	public String getOutputLabel() {
		// TODO
		return null;
	}

	@Override
	public String toString() {
		List<String> inputAsStrings = new ArrayList<>();
		for (int i = 0; i < input.length; i++) {
			inputAsStrings.add(Double.toString(input[i]));
		}
		String joinedInput = String.join(",", inputAsStrings);
		return joinedInput + "," + Double.toString(output);
	}
}
