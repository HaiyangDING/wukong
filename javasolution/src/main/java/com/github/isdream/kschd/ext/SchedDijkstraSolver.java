/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd.ext;

import com.github.isdream.kschd.GraphModel;
import com.github.isdream.kschd.GraphModel.Edge;
import com.github.isdream.kschd.GraphModel.Path;
import com.github.isdream.kschd.GraphModel.Vertex;
import com.github.isdream.kschd.impl.DijkstraSolver;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月27日
 */
public class SchedDijkstraSolver extends DijkstraSolver {

	/**
	 * deleted
	 */
	protected Path deleted = null;
	
	public SchedDijkstraSolver(GraphModel graphModel, Vertex sourceVertex, Vertex sinkVertex) {
		super(graphModel, sourceVertex, sinkVertex);
	}

	@Override
	public Path getMinCost() {
		deleted = null;
		paths.clear();
		unvisited.clear();
		unvisited.add(sourceVertex);
		paths.put(getPathName(
				sourceVertex), new Path());
		while (!unvisited.isEmpty()) {
			Vertex vertex = unvisited.remove(); // extract-min
			
			// delete the task which resource requirement is large than 
			// node resource provisioning
			if (deleted == null && vertex != sourceVertex) {
				deleted = getPath(vertex);
			}
			
			for (Edge edge : vertex.getOutgoingEdges()) {
				relax(vertex, edge);
			}
		}
		return getPath(sinkVertex) == null 
				? deleted : getPath(sinkVertex);
	}

	@Override
	protected void relax(Vertex vertex, Edge edge) {
		Path thisPath = getPath(vertex);
		int minCost = (getPath(edge.getTo()) == null) ?
				Integer.MAX_VALUE :  getPath(edge.getTo()).getCost();

		if (minCost > thisPath.getCost() + edge.getCost()) {
			// ignore the vertex if task resource requirement is large than 
			// node resource provisioning
			if (thisPath.getEdges().size() > 1 
					&& edge.getCapacity().lessThan(
							thisPath.getEdges().get(1).getCapacity())) {
				return;
			}
			
			Path newPath = new Path(thisPath);
			newPath.addEdge(edge);
			paths.put(getPathName(edge.getTo()), newPath);
		}
		
		unvisited.add(edge.getTo());
	}
	
}
