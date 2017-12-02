package com.pwr.geneticsClassifier.GeneticsAlgorithm;

import com.pwr.geneticsClassifier.GeneticsAlgorithm.server.DataSet;
import com.pwr.geneticsClassifier.GeneticsAlgorithm.server.dataAccess.CSVDataSetBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final String IRIS_DATASET_RELATIVE_PATH = "iris.data.txt";
	
    public static void main( String[] args )
    {
    	System.out.println("=================Beginning==================");
    	DataSet irisDataSet = new CSVDataSetBuilder(4).withRelativePath(IRIS_DATASET_RELATIVE_PATH).build();
    	System.out.println();
    	System.out.println(irisDataSet);
    	System.out.println("====================END=====================");
    }
}
