/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd.ext;

import com.github.isdream.kschd.GraphModel;
import com.github.isdream.kschd.GraphModel.Capacity;
import com.github.isdream.kschd.GraphModel.Edge;
import com.github.isdream.kschd.GraphModel.Flow;
import com.github.isdream.kschd.GraphModel.Path;
import com.github.isdream.kschd.GraphModel.Vertex;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月25日
 * 
 * If we don't add the reversed edge, it is a queueing model
 */
public class SchedQueueSolver extends SchedEdmondsKarpSolver {

	/**
	 * @param graphModel
	 * @param sourceVertex
	 * @param sinkVertex
	 */
	public SchedQueueSolver(GraphModel graphModel, Vertex sourceVertex, Vertex sinkVertex) {
		super(graphModel, sourceVertex, sinkVertex);
	}
	
	/* (non-Javadoc)
	 * @see com.github.isdream.air17.core.schd.SchdEdmondsKarpSolver#getMaxFlow()
	 */
	@Override
	public Flow getMaxFlow() {
		Path path = solver.getMinCost();
		while (moreTask(path)) {
			if (canSchd(path)) {
				Capacity min = addFlow(path);
				updateGraph(path, min.clone());
			} else {
				recordUnschd(path);
			}
			path = solver.getMinCost();
		}
		return flow;
	}

	/**
	 * @param path
	 */
	protected void recordUnschd(Path path) {
		// delete the task which resource requirement is large than 
		// node resource provisioning
		Edge edge = path.getEdges().get(0); 
		graphModel.removeEdge(edge);
		edge.getFrom().removeOutgoingEdge(edge);
		unsched.put(edge.getTo().getName(), edge.getTo());
	}

	/* (non-Javadoc)
	 * @see com.github.isdream.air17.core.std.EdmondsKarpSolver#updateGraph(com.github.isdream.air17.core.BasicGraph.Path, com.github.isdream.air17.core.IConstraintCapacity)
	 */
	protected void updateGraph(Path path, Capacity min) {
		int idx = path.getEdges().size();
		while (--idx >= 0) {
			removeEdge(path, min, idx);
		}
	}

	/* (non-Javadoc)
	 * @see com.github.isdream.air17.core.std.EdmondsKarpSolver#addFlow(com.github.isdream.air17.core.BasicGraph.Path)
	 */
	protected Capacity addFlow(Path path) {
		Capacity min = null;
		for (Edge edge : path.getEdges()) {
			if (min == null || edge.getCapacity().lessThan(min)) {
				min = edge.getCapacity().clone();
			}
		}
		flow.addFlow(path, min);
		return min;
	}
	
	/* (non-Javadoc)
	 * @see com.github.isdream.air17.core.schd.SchdEdmondsKarpSolver#createMinCostSolver()
	 */
	protected void createMinCostSolver() {
		solver = new SchedDijkstraSolver(graphModel, sourceVertex, sinkVertex);
	}
	
	/* (non-Javadoc)
	 * @see com.github.isdream.air17.core.schd.SchdEdmondsKarpSolver#uncompleted(com.github.isdream.air17.core.BasicGraph.Path)
	 */
	protected boolean moreTask(Path path) {
		return !sourceVertex.getOutgoingEdges().isEmpty();
	}
	
	/**
	 * Check that the task is schedulable
	 * 
	 * @param path
	 * @return
	 */
	protected boolean canSchd(Path path) {
		return path.getEdges().size() > 1;
	}

}
