/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd.impl;

import com.github.isdream.kschd.GraphBuilderMocker;
import com.github.isdream.kschd.GraphModel;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月27日
 */
public class EdmondsKarpSolverTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GraphModel gm = GraphBuilderMocker.createEdmondsKarpGraph_wiki();
		EdmondsKarpSolver eks = new EdmondsKarpSolver(gm, 
					gm.getVertex("A"), gm.getVertex("G"));
		System.out.println(eks.getMaxFlow());
	}

}
