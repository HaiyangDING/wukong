/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd.ext;

import com.github.isdream.kschd.GraphBuilderMocker;
import com.github.isdream.kschd.GraphModel;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月29日
 */
public class SchedQueueSolverTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		GraphModel gm = GraphBuilderMocker.createSchedScenarioT3T1T2N1N2();
//		GraphModel gm = GraphBuilderMocker.createSchedScenarioT1T2T3N1N2();
		GraphModel gm = GraphBuilderMocker.createSchedScenarioT3T1T2N2N1();
		SchedQueueSolver ds = new SchedQueueSolver(gm, 
					gm.getVertex("S"), gm.getVertex("E"));
		System.out.println(ds.getMaxFlow());
	}

}
