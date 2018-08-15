/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

/******************************************************************************************************
 * 
 * A graph G = (V, E) consists of a set of vertices, V, and a set of edges, E. Each edge is a pair
 * (v, w), where v, w ∈ V. Edges are sometimes referred to as arcs. If the pair is ordered, then the 
 * graph is directed. Directed graphs are sometimes referred to as digraphs. Vertex w is adjacent to 
 * v if and only if (v, w) ∈ E. In an undirected graph with edge (v, w), and hence (w, v), w is adjacent 
 * to v and v is adjacent to w. Sometimes an edge has a third component, known as either a weight or 
 * a cost.
 *
 * A path in a graph is a sequence of vertices w1, w2, w3, ... , wN such that (wi, wi+1) ∈ E
 * for 1 ≤ i < N. The length of such a path is the number of edges on the path, which is
 * equal to N − 1. We allow a path from a vertex to itself; if this path contains no edges, then
 * the path length is 0. This is a convenient way to define an otherwise special case. If the
 * graph contains an edge (v, v) from a vertex to itself, then the path v, v is sometimes referred
 * to as a loop. The graphs we will consider will generally be loopless. A simple path is a
 * path such that all vertices are distinct, except that the first and last could be the same
 * 
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月27日
 * 
 /******************************************************************************************************/
public class GraphModel {

	/**
	 * m_logger
	 */
	protected final static Logger m_logger = Logger.getLogger(GraphModel.class);
	
	/**
	 * DEFAULT_VERTEX
	 */
	public final static Vertex DEFAULT_VERTEX = null;
	
	/**
	 * DEFAULT_EDGE
	 */
	public final static Edge DEFAULT_EDGE = null;
	
	
	/**
	 * DEFAULT_NAME
	 */
	public final static String DEFAULT_NAME = null;
	
	/**
	 * all vertices
	 * here, the key is the vertex name
	 */
	protected final Map<String, Vertex> vertices = new LinkedHashMap<String, Vertex>();
	
	/**
	 * all edges
	 * here, the key consists of the from vertex and the to vertex names  
	 */
	protected final Map<String, Edge> edges = new LinkedHashMap<String, Edge>();
	
	
	/********************************************************
	 * 
	 *                    Vertices and Edges
	 * 
	 **********************************************************/
	
	/**
	 * @param vertex vertex
	 */
	public void addVertex(Vertex vertex) {
		
		if (invalid(vertex)) {
			m_logger.warn("Vertex is null, ignore.");
			return;
		}
		
		vertices.put(vertex.getName(), vertex);
	}
	
	/**
	 * @param edge edge
	 */
	public void addEdge(Edge edge) {
		
		if (invalid(edge)) { 
			m_logger.warn("Edge is null, ignore.");
			return;
		}
		
		edge.getFrom().addOutgoingEdge(edge);
		edge.getTo().addIncomingEdge(edge);
		edges.put(getEdgeName(edge.getFrom(), 
						edge.getTo()), edge);
	}
	
	/**
	 * @return all vertices
	 */
	public Map<String, Vertex> getVertices() {
		return vertices;
	}
	
	/**
	 * @return all edges
	 */
	public Map<String, Edge> getEdges() {
		return edges;
	}

	/**
	 * @param name name
	 * @return vertex with the specified name
	 */
	public Vertex getVertex(String name) {
		try {
			return vertices.get(name);
		} catch (Exception e) {
			return DEFAULT_VERTEX;
		}
	}
	
	/**
	 * @param name
	 * @return edge with the specified name
	 */
	public Edge getEdge(String name) {
		try {
			return edges.get(name);
		} catch (Exception e) {
			return DEFAULT_EDGE;
		}
	}
	
	
	/**
	 * @param vertex vertex
	 */
	public void removeVertex(Vertex vertex) {
		if (invalid(vertex) 
				|| !vertices.containsValue(vertex)) { 
			m_logger.warn("Vertex is null, ignore.");
			return;
		}
		
		vertex.getIncomingEdges().clear();
		vertex.getOutgoingEdges().clear();
		vertices.remove(vertex.getName());
	}
	
	/**
	 * @param edge edge
	 */
	public void removeEdge(Edge edge) {
		String edgeName = getEdgeName(
				edge.getFrom(), edge.getTo());
		if (invalid(edge) || !edges.containsKey(
				edgeName)) { 
			m_logger.warn("Edge is null, ignore.");
			return;
		}
		
		edge.getFrom().removeOutgoingEdge(
						getEdge(edgeName));
		edge.getTo().removeIncomingEdge(
						getEdge(edgeName));
		edges.remove(edgeName);
	}

	/*******************************************************
	 * 
	 *                       Utils
	 *
	 *********************************************************/
	
	/**
	 * @param from vertex
	 * @param to vertex
	 * @return name
	 */
	public static String getEdgeName(Vertex from, Vertex to) {
		try {
			return from.getName() + "-" + to.getName();
		} catch (Exception e) {
			return DEFAULT_NAME;
		}
	}
	
	/**
	 * @param vertex vertex
	 * @return return true if vertex is null, otherwise return false
	 */
	public static boolean invalid(Vertex vertex) {
		return vertex == null 
				|| vertex.getName() == null;
	}
	
	/**
	 * @param edge edge 
	 * @return return true if edge is null, otherwise return false
	 */
	public static boolean invalid(Edge edge) {
		return edge == null 
				|| invalid(edge.getFrom()) 
				|| invalid(edge.getTo());
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static boolean invalid(String str) {
		return str == null || str.equals("");
	}
	
	/********************************************************************
	 * 
	 * A vertex contains some incoming edges and some outgoing edges 
	 * 
	 * @author wuheng@otcaix.iscas.ac.cn
	 *
	 *********************************************************************/
	public static class Vertex {

		/**
		 * outgoingEdges
		 */
		protected final Set<Edge> outgoingEdges = new LinkedHashSet<Edge>();
		
		/**
		 * incomingEdges
		 */
		protected final Set<Edge> incomingEdges = new LinkedHashSet<Edge>();
		
		/**
		 * name
		 */
		protected final String name;

        /**
		 * @param name
		 */
		public Vertex(String name) {
			if (invalid(name)) {
				m_logger.error("Vertex name is null, ignore.");
			}
			this.name = name;
		}

		/**
		 * @return
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * @return
		 */
		public Set<Edge> getOutgoingEdges() {
			return outgoingEdges;
		}

		/**
		 * @return
		 */
		public Set<Edge> getIncomingEdges() {
			return incomingEdges;
		}

		/**
		 * @param edge
		 */
		public void addOutgoingEdge(Edge edge) {
			if (invalid(edge)) {
				return;
			}
			this.outgoingEdges.add(edge);
		}
		
		/**
		 * @param edge
		 */
		public void addIncomingEdge(Edge edge) {
			if (invalid(edge)) {
				return;
			}
			this.incomingEdges.add(edge);
		}
		
		/**
		 * @param edge
		 */
		public void removeOutgoingEdge(Edge edge) {
			if (invalid(edge)) {
				return;
			}
			this.outgoingEdges.remove(edge);
		}


		/**
		 * @param edge
		 */
		public void removeIncomingEdge(Edge edge) {
			if (invalid(edge)) {
				return;
			}
			this.incomingEdges.remove(edge);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			final StringBuilder builder = new StringBuilder();
			builder.append("Vertex name=").append(name).append("\n");
			for (Edge oe : outgoingEdges)
				builder.append("\t").append(oe.toString());
			return builder.toString();
		}
	}

	/***************************************************************************
	 * 
	 * An edge is used to represent a connection from the vertex x to the vertex y
	 * 
	 * @author wuheng@otcaix.iscas.ac.cn
	 *
	 ***************************************************************************/
	
	public static class Edge{

		/**
		 * DEFAULT_CAPACITY
		 */
		protected final static Capacity DEFAULT_CAPACITY = new Capacity() {
			
			@Override
			public void subCapacity(Capacity other) {
				
			}
			
			@Override
			public boolean lessThan(Capacity other) {
				return false;
			}
			
			@Override
			public boolean isUnused() {
				return false;
			}
			
			@Override
			public boolean isEmpty() {
				return false;
			}
			
			@Override
			public Capacity clone() {
				return null;
			}
			
			@Override
			public void addCapacity(Capacity other) {
				
			}
		};
		
		/**
		 * DEFAULT_CAPACITY
		 */
		protected final static int DEFAULT_COST = 0;
		
		/**
		 * from
		 */
		protected final Vertex from;

		/**
		 * to
		 */
		protected final Vertex to;

		/**
		 * cost
		 */
		protected int cost; 
		
		/**
		 * capacitiy
		 */
		protected Capacity capacity = null;
		
		/**
		 * @param cost
		 * @param from
		 * @param to
		 */
		public Edge(int cost, Vertex from, Vertex to) {
			this(cost, DEFAULT_CAPACITY, from, to);
		}
		
		/**
		 * @param from
		 * @param to
		 */
		public Edge(Vertex from, Vertex to) {
			this(DEFAULT_COST, DEFAULT_CAPACITY, from, to);
		}
		
		/**
		 * @param capacities
		 * @param from
		 * @param to
		 */
		public Edge(Capacity capacity, Vertex from, Vertex to) {
			this(DEFAULT_COST, capacity, from, to);
		}
		
		/**
		 * @param cost
		 * @param capacity
		 * @param from
		 * @param to
		 */
		public Edge(int cost, Capacity capacity, Vertex from, Vertex to) {
			super();
			if (invalid(from) || invalid(to) 
					|| CommonUtils.isNull(capacity)) {
				m_logger.error("Vertex is null, ignore");
			}
			
			this.from = from;
			this.to = to;
			this.cost = cost;
			this.capacity = capacity;
		}
		
		/**
		 * @return
		 */
		public Vertex getFrom() {
			return from;
		}

		/**
		 * @return
		 */
		public Vertex getTo() {
			return to;
		}
		
		/**
		 * @return
		 */
		public int getCost() {
			return cost;
		}

		
		/**
		 * @return capacity
		 */
		public Capacity getCapacity() {
			return capacity;
		}

		/**
		 * @param capacity capacity
		 */
		public void setCapacity(Capacity capacity) {
			if (CommonUtils.isNull(capacity)) {
				return;
			}
			this.capacity = capacity;
		}

		/**
		 * @param cost
		 */
		public void setCost(int cost) {
			this.cost = cost;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("[ ").append(from.name).append("]")
					.append(" -> ").append("[ ").append(to.name).append("]")
					.append(" = ").append(cost);
			return builder.append("\n").toString();
		}

	}

	/**
	 * @author wuheng@otcaix.iscas.ac.cn
	 * @since  2018年7月27日
	 */
	public static abstract class Capacity {
	
		/**
		 * DEFAULT_TYPE
		 */
		protected final static String DEFAULT_TYPE = "ignore.type";
		
		/**
		 * type
		 */
		protected final String type;
		
		/**
		 * 
		 */
		public Capacity() {
			this(DEFAULT_TYPE);
		}
		
		/**
		 * @param type type
		 */
		public Capacity(String type) {
			super();
			this.type = type;
		}
		
		/**
		 * @return type
		 */
		public String getType() {
			return type;
		}

		/**
		 * We would not check the parameter
		 * Please try ... catch yourself 
		 * 
		 * @param other other
		 * @return return true if this object is less than the parameter, otherwise return false
		 */
		public abstract boolean lessThan(Capacity other);
		
		/**
		 * We would not check the parameter
		 * Please try ... catch yourself 
		 * 
		 * @param other other
		 */
		public abstract void addCapacity(Capacity other);
		
		/**
		 * We would not check the parameter
		 * Please try ... catch yourself 
		 * 
		 * @param other other
		 */
		public abstract void subCapacity(Capacity other);

		/**
		 * @return return true if capacity is empty, otherwise return false
		 */
		public abstract boolean isEmpty();

		/**
		 * @return return true if capacity is unused, otherwise return false
		 */
		public abstract boolean isUnused();
		
		/**
		 * clone
		 * 
		 * @return object
		 */
		public abstract Capacity clone();
		
	}
	
	/*************************************************************************
	 * 
	 * A path contains some adjacent edges
	 * 
	 * @author wuheng@otcaix.iscas.ac.cn
	 *
	 **************************************************************************/
	public static class Path {

		/**
		 * cost
		 */
		protected int cost = 0;
		
		/**
		 * edges
		 */
		protected List<Edge> edges = new LinkedList<Edge>();

		/**
		 * 
		 */
		public Path() {
			super();
		}
		
		/**
		 * @param path path
		 */
		public Path(Path path) {
			for (Edge edge : path.getEdges()) {
				addEdge(edge);
			}
		}

		/**
		 * @return cost
		 */
		public int getCost() {
			return cost;
		}


		/**
		 * @return all edges
		 */
		public List<Edge> getEdges() {
			return edges;
		}
		
		/**
		 * @param edge edge
		 */
		public void addEdge(Edge edge) {
			if(CommonUtils.isNull(edge)) {
				m_logger.warn("Edge is null.");
				return;
			}
			this.edges.add(edge);
			this.cost += edge.getCost();
		}

		/**
		 * @param edges edges
		 */
		public void addEdges(List<Edge> edges) {
			if(CommonUtils.isNull(edges)) {
				m_logger.warn("Edge is null.");
				return;
			}
			for (Edge edge : edges) {
				this.addEdge(edge);
			}
			for (Edge edge : edges) {
				this.cost += edge.getCost();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			final StringBuilder builder = new StringBuilder();
			for (Edge e : edges)
				builder.append("\t").append(e);
			return builder.toString();
		}

	}
	
	/*****************************************************************
	 * In other word, a flow means the min capacity of the specified path
	 * 
	 * @author wuheng@otcaix.iscas.ac.cn
	 *
	 ******************************************************************/
	public static class Flow {
		
		/**
		 * flow
		 */
		protected Capacity flow;
		
		/**
		 * paths
		 */
		protected final List<Path> paths = new LinkedList<Path>();

		
		/**
		 * capacities
		 */
		protected final Map<Path, Capacity> capacities = new LinkedHashMap<Path, Capacity>();

		/**
		 * @return flow
		 */
		public Capacity getFlow() {
			return flow;
		}

		
		/**
		 * @param path path
		 * @param cap capacity
		 */
		public void addFlow(Path path, Capacity cap) {
			if(CommonUtils.isNull(path)
					|| CommonUtils.isNull(cap)) {
				m_logger.warn("Null parameters.");
				return;
			}
			this.paths.add(path);
			if (this.flow == null) {
				this.flow = cap;
			} else {
				this.flow.addCapacity(cap);
			}
			this.capacities.put(path, cap.clone());
		}
		
		
		/**
		 * @return all paths
		 */
		public Collection<Path> getPaths() {
			return paths;
		}

		
		/**
		 * @return all capacities
		 */
		public Map<Path, Capacity> getCapacities() {
			return capacities;
		}
		
		/**
		 * @return capacity
		 */
		public Capacity getCapacitiy(Path path) {
			return capacities.remove(path);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			final StringBuilder builder = new StringBuilder();
			for (Path path : paths) {
				builder.append("---Path ").append("")
						.append(": flow = ").append(capacities.get(path)).append("\n");
				builder.append(path).append("\n");;
			}
			return builder.toString();
		}
	}
	
}
