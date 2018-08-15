/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd.impl;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import com.github.isdream.kschd.GraphModel;
import com.github.isdream.kschd.GraphModel.Edge;
import com.github.isdream.kschd.GraphModel.Path;
import com.github.isdream.kschd.GraphModel.Vertex;
import com.github.isdream.kschd.GraphSolver.MinCostSolver;


/**
 * Dijkstra's algorithm is an algorithm for finding the shortest paths between
 * nodes in a graph. It was conceived by computer scientist Edsger W. Dijkstra
 * in 1956 and published three years later.
 * <p>
 * Worst case: O(|E| + |V| log |V|)
 * <p>
 *
 * @see <a href=
 *      "https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm">Dijkstra's
 *      Algorithm (Wikipedia)</a> <br>
 *
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月27日
 */
public class DijkstraSolver extends MinCostSolver {

	/**
	 * heap, it' used to record the distance information from sourceVertex to any
	 * others vertices in ascending order 
	 */
	protected PriorityQueue<Vertex> unvisited = new PriorityQueue<Vertex>(
												new Comparator<Vertex>() {
		public int compare(Vertex o1, Vertex o2) {
			return getPath(o1).getCost() - getPath(o2).getCost();
		}
		
	}) {
		private static final long serialVersionUID = 1L;
		
		/**
		 * It's used to prevent the loopback
		 */
		protected Set<Vertex> visited = new HashSet<Vertex>();

		@Override
		public boolean add(Vertex e) {
			if (visited.contains(e)) {
				return false;
			}
			visited.add(e);
			return super.add(e);
		}

		@Override
		public void clear() {
			super.clear();
			visited.clear();
		}
		
	};
	

	/**
	 * @param graphModel
	 * @param sourceVertex
	 * @param sinkVertex
	 */
	public DijkstraSolver(GraphModel graphModel, Vertex sourceVertex, Vertex sinkVertex) {
		super(graphModel, sourceVertex, sinkVertex);
	}

	/* (non-Javadoc)
	 * @see com.github.isdream.air17.core.solvers.MinCostSolver#getMinCost()
	 */
	@Override
	public Path getMinCost() {
		init();
		while (!unvisited.isEmpty()) {
			Vertex vertex = unvisited.remove(); // extract-min
			for (Edge edge : vertex.getOutgoingEdges()) {
				relax(vertex, edge);
			}
		}
		return getPath(sinkVertex);
	}

	/**
	 * We would support increased graph algorithm latter
	 */
	protected void init() {
		paths.clear();
		unvisited.clear();
		unvisited.add(sourceVertex);
		paths.put(getPathName(
				sourceVertex), new Path());
	}

	/**
	 * @param vertex vertex
	 * @param edge edge
	 */
	protected void relax(Vertex vertex, Edge edge) {
		Path thisPath = getPath(vertex);
		int minCost = (getPath(edge.getTo()) == null) ?
				Integer.MAX_VALUE :  getPath(edge.getTo()).getCost();
		if (minCost > thisPath.getCost() + edge.getCost()) {
			Path newPath = new Path(thisPath);
			newPath.addEdge(edge);
			paths.put(getPathName(edge.getTo()), newPath);
		}
		unvisited.add(edge.getTo());
	}

	/**
	 * @param vertex vertex
	 * @return path path
	 */
	public Path getPath(Vertex vertex) {
		return paths.get(getPathName(vertex));
	}
	
	/**
	 * @return path name
	 */
	protected String getPathName(Vertex vertex) {
		return sourceVertex.getName() + "-" + vertex.getName();
	}
	
}
