package com.pwr.geneticsClassifier.GeneticsAlgorithm;

import com.pwr.geneticsClassifier.GeneticsAlgorithm.server.DataSet;
import com.pwr.geneticsClassifier.GeneticsAlgorithm.server.dataAccess.CSVDataSetBuilder;

public class Fuzzy 
{
	private static final String IRIS_DATASET_RELATIVE_PATH = "iris.data.txt";
	
	private static String[][] getMatrix(DataSet data)
	{
		String s = data.toString();
    	String[] rows = s.split("\n");
    	String[][] matrix = new String[rows.length][];
    	int r=0;
    	for(String row : rows)
    		matrix[r++] = row.split(",");
    	return matrix;
	}
	
	public static void main( String[] args )
    {
    	System.out.println("=================Beginning==================");
    	DataSet irisDataSet = new CSVDataSetBuilder(4).withRelativePath(IRIS_DATASET_RELATIVE_PATH).build();
    	System.out.println();
    	//System.out.println(irisDataSet);
    	String[][] matrix = getMatrix(irisDataSet);
    	System.out.println(matrix[1][0]);
    	System.out.println("====================END=====================");
    }
}
