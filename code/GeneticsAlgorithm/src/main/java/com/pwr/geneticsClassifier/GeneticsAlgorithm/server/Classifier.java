package com.pwr.geneticsClassifier.GeneticsAlgorithm.server;

public interface Classifier {
	void train(DataSet dataset);

	double evaluate(double[] input);
}
