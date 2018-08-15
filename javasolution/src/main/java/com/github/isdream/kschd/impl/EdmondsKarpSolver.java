/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd.impl;

import com.github.isdream.kschd.GraphModel;
import com.github.isdream.kschd.GraphModel.Capacity;
import com.github.isdream.kschd.GraphModel.Edge;
import com.github.isdream.kschd.GraphModel.Flow;
import com.github.isdream.kschd.GraphModel.Path;
import com.github.isdream.kschd.GraphModel.Vertex;
import com.github.isdream.kschd.GraphSolver.MaxFlowSolver;

/**
 * The algorithm is identical to the Ford–Fulkerson algorithm, except that the search order
 * when finding the augmenting path is defined. The path found must be a shortest path that
 * has available scapacity. This can be found by a breadth-first search, where we apply a weight
 * of 1 to each edge. The running time of O(V E2) is found by showing that each augmenting path
 * can be found in O(E) time, that every time at least one of the E edges becomes saturated (an
 * edge which has the maximum possible flow), that the distance from the saturated edge to the source
 * along the augmenting path must be longer than last time it was saturated, and that the length is
 * at most V. Another property of this algorithm is that the length of the shortest augmenting path
 * increases monotonically. There is an accessible proof in Introduction to Algorithms.
 *
 * https://en.wikipedia.org/wiki/Edmonds%E2%80%93Karp_algorithm
 * 
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月27日
 */
public class EdmondsKarpSolver extends MaxFlowSolver {
	
	/**
	 * solver
	 */
	protected DijkstraSolver solver = null;
	
	/**
	 * flow
	 */
	public final Flow flow = new Flow();
	
	/**
	 * @param graphModel
	 * @param sourceVertex
	 * @param sinkVertex
	 */
	public EdmondsKarpSolver(GraphModel graphModel, Vertex sourceVertex, Vertex sinkVertex) {
		super(graphModel, sourceVertex, sinkVertex);
		createMinCostSolver();
	}

	/* (non-Javadoc)
	 * @see com.github.isdream.air17.core.AbsSolver.MaxFlowSolver#getMaxFlow()
	 */
	@Override
	public Flow getMaxFlow() {
		Path path = solver.getMinCost();
		while (validPath(path)) {
				Capacity min = addFlow(path);
				updateGraph(path, min.clone());
			path = solver.getMinCost();
		}
		return flow;
	}

	/**
	 * @param path path 
	 * @param min min capacity
	 */
	protected void updateGraph(Path path, Capacity min) {
		int idx = path.getEdges().size();
		while (--idx >= 0) {
			Edge edge = removeEdge(path, min, idx);
			addReversedEdge(min, edge);
		}
	}

	/**
	 * @param min min capacity
	 * @param edge edge 
	 */
	protected void addReversedEdge(Capacity min, Edge edge) {
		if (edge.getFrom() == sourceVertex 
				|| edge.getTo() == sinkVertex) {
			return;
		}
		
		Edge reversedEdge = graphModel.getEdge(
						edge.getTo().getName() 
						+ "-" + edge.getFrom().getName());
		
		if (reversedEdge == null) {
			reversedEdge = new Edge(edge.getCost(), 
					min, edge.getTo(), edge.getFrom());
			graphModel.addEdge(reversedEdge);
			reversedEdge.getFrom().addOutgoingEdge(reversedEdge);
		} else {
			reversedEdge.getCapacity().addCapacity(min);
		}
	}

	/**
	 * @param path path
	 * @param min  min capacity
	 * @param idx  index
	 * @return edge
	 */
	protected Edge removeEdge(Path path, Capacity min, int idx) {
		Edge edge = path.getEdges().get(idx);
		edge.getCapacity().subCapacity(min);
		if (edge.getCapacity().isEmpty() || 
				(edge.getFrom() == sourceVertex && 
				edge.getTo().getOutgoingEdges().isEmpty())) {
			graphModel.removeEdge(edge);
			edge.getFrom().removeOutgoingEdge(edge);
		}
		return edge;
	}

	/**
	 * @param path path 
	 * @return capacity
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
	
	/**
	 * Here, we use DijkstraSolver
	 */
	protected void createMinCostSolver() {
		solver = new DijkstraSolver(graphModel, sourceVertex, sinkVertex);
	}
	
	/**
	 * path is null means there are no 
	 *  
	 * @param path
	 * @return return true if there still exist unscheduled task
	 */
	protected boolean validPath(Path path) {
		return path != null;
	}
	
}
