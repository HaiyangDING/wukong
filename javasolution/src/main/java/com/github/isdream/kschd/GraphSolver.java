/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.github.isdream.kschd.GraphModel.Flow;
import com.github.isdream.kschd.GraphModel.Path;
import com.github.isdream.kschd.GraphModel.Vertex;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月27日
 */
public abstract class GraphSolver {

	/**
	 * m_logger
	 */
	protected final static Logger m_logger = Logger.getLogger(MinCostSolver.class);
	
	/**
	 * graphModel
	 */
	protected final GraphModel graphModel;
	
	/**
	 * startVertex
	 */
	protected final Vertex sourceVertex;
	
	/**
	 * endVertex
	 */
	protected final Vertex sinkVertex;

	/**
	 * @param graphModel graph
	 * @param startVertex start vertex
	 * @param endVertex end vertex
	 */
	public GraphSolver(GraphModel graphModel, Vertex startVertex, Vertex endVertex) {
		if (CommonUtils.isNull(graphModel) ||
			 	 CommonUtils.isNull(startVertex) ||
			 	 CommonUtils.isNull(endVertex)) {
			m_logger.error("Includes null parameters");
		}
		
		this.graphModel = graphModel;
		this.sourceVertex = startVertex;
		this.sinkVertex = endVertex;
	}

	
	/**
	 * @return source vertex
	 */
	public Vertex getSourceVertex() {
		return sourceVertex;
	}

	/**
	 * @return sink vertex
	 */
	public Vertex getSinkVertex() {
		return sinkVertex;
	}

	/**
	 * @author wuheng@otcaix.iscas.ac.cn
	 * @since  2018年7月7日
	 */
	public static abstract class MinCostSolver extends GraphSolver {

		/**
		 * paths
		 */
		public final Map<String, Path> paths = new LinkedHashMap<String, Path>();
		
		/**
		 * @param graphModel
		 * @param startVertex
		 * @param endVertex
		 */
		public MinCostSolver(GraphModel graphModel, Vertex startVertex, Vertex endVertex) {
			super(graphModel, startVertex, endVertex);
		}

		/**
		 * @return path
		 */
		public abstract Path getMinCost();

	}
	
	/**
	 * @author wuheng@otcaix.iscas.ac.cn
	 * @since  2018年7月7日
	 */
	public static abstract class MaxFlowSolver extends GraphSolver {

		/**
		 * @param graphModel
		 * @param startVertex
		 * @param endVertex
		 */
		public MaxFlowSolver(GraphModel graphModel, Vertex startVertex, Vertex endVertex) {
			super(graphModel, startVertex, endVertex);
		}
		
		/**
		 * @return flow
		 */
		public abstract Flow getMaxFlow();

	}
}
