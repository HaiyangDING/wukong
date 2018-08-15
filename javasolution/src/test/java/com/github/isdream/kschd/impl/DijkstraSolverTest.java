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
public class DijkstraSolverTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GraphModel gm = GraphBuilderMocker.createDijkstraGraph_wiki();
		DijkstraSolver ds = new DijkstraSolver(gm, 
					gm.getVertex("1"), gm.getVertex("5"));
		System.out.println(ds.getMinCost());
	}

}
