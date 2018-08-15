/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd.ext;

import java.util.HashMap;
import java.util.Map;

import com.github.isdream.kschd.GraphModel;
import com.github.isdream.kschd.GraphModel.Capacity;
import com.github.isdream.kschd.GraphModel.Edge;
import com.github.isdream.kschd.GraphModel.Flow;
import com.github.isdream.kschd.GraphModel.Path;
import com.github.isdream.kschd.GraphModel.Vertex;
import com.github.isdream.kschd.ext.SchedGraphModel.SchdFlow;
import com.github.isdream.kschd.impl.EdmondsKarpSolver;


/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月27日
 */
public class SchedEdmondsKarpSolver extends EdmondsKarpSolver {

	/**
	 * resched
	 */
	protected Map<String, Vertex> resched = new HashMap<String, Vertex>();
	
	/**
	 * unsched
	 */
	protected Map<String, Vertex> unsched = new HashMap<String, Vertex>();
	
	/**
	 * unsched
	 */
	protected Map<String, Integer> schedost = new HashMap<String, Integer>();
	
	/**
	 * flow
	 */
	public final SchdFlow sflow = new SchdFlow();
	
	
	public SchedEdmondsKarpSolver(GraphModel graphModel, Vertex sourceVertex, Vertex sinkVertex) {
		super(graphModel, sourceVertex, sinkVertex);
	}

	/* (non-Javadoc)
	 * @see com.github.isdream.air17.core.AbsSolver.MaxFlowSolver#getMaxFlow()
	 */
	@Override
	public Flow getMaxFlow() {
		Path path = solver.getMinCost();
		while (moreTask(path)) {
			if (path.getEdges().size() > 1) {
				if (sflow.getSize() == 0 || path.getEdges().size() == sflow.getSize()) {
					Capacity min = addFlow(path);
					updateGraph(path, min.clone());
				} else {
					rollback(path);
				}
			} else {
				// delete the task which resource requirement is large than 
				// node resource provisioning
				Edge edge = path.getEdges().get(0); 
				graphModel.removeEdge(edge);
				edge.getFrom().removeOutgoingEdge(edge);
				unsched.put(edge.getTo().getName(), edge.getTo());
			}
			path = solver.getMinCost();
		}
		return sflow;
	}
	
	protected void rollback(Path path) {
		
		int idx = path.getEdges().size();
		while (--idx >= 0) {
			Edge thisEdge = path.getEdges().get(idx);
			String name = thisEdge.getTo().getName();
			Path thisPath = sflow.getPath(name);
			if (thisPath != null) {
				
				int exCost = 0;
				Edge dedge = thisPath.getEdges().get(0);
				if (resched.containsKey(dedge.getTo().getName())) {
					graphModel.removeEdge(dedge);
					dedge.getFrom().removeOutgoingEdge(dedge);
					unsched.put(dedge.getTo().getName(), dedge.getTo());
					break;
				} 
				
				resched.put(dedge.getTo().getName(), dedge.getTo());
				
				Capacity capacitiy = sflow.getCapacitiy(thisPath).clone();
				sflow.subFlow(thisPath, capacitiy);
				
				for (Edge rollbackEdge : thisPath.getEdges()) {
					Edge edge = graphModel.getEdge(
									rollbackEdge.getFrom().getName() + "-" 
									+ rollbackEdge.getTo().getName());
					if (edge == null) {
						rollbackEdge.getCapacity().addCapacity(capacitiy);
						graphModel.addEdge(rollbackEdge);
					} else {
						edge.getCapacity().addCapacity(capacitiy);
					}
					
					if (rollbackEdge.getFrom() == sourceVertex) {
						exCost = rollbackEdge.getCost();
						rollbackEdge.setCost(path.getEdges().get(0).getCost());
					}
					
					Edge reversedEdge = graphModel.getEdge(rollbackEdge.getTo().getName() 
							+ "-" + rollbackEdge.getFrom().getName());
					
					if (reversedEdge != null) {
						reversedEdge.getCapacity().subCapacity(capacitiy);
						if (reversedEdge.getCapacity().isEmpty()) {
							graphModel.removeEdge(reversedEdge);
							reversedEdge.getFrom().removeOutgoingEdge(reversedEdge);
						}
					}
					
				}
				
				Edge edge = graphModel.getEdge(sourceVertex.getName() 
						+ "-" + path.getEdges().get(0).getTo().getName());
				edge.setCost(exCost);
			}
		}
	}
	
	@Override
	protected void createMinCostSolver() {
		solver = new SchedDijkstraSolver(graphModel, sourceVertex, sinkVertex);
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
		sflow.addFlow(path, min);
		return min;
	}
	
	
	/**
	 * @param path path
	 * @return
	 */
	protected boolean moreTask(Path path) {
		return !sourceVertex.getOutgoingEdges().isEmpty();
	}

	/**
	 * @return all vertices
	 */
	public Map<String, Vertex> getResched() {
		return resched;
	}

	/**
	 * @return all unsched
	 */
	public Map<String, Vertex> getUnsched() {
		return unsched;
	}
	
}
