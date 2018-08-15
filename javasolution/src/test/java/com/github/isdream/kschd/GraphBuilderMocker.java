/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd;

import com.github.isdream.kschd.GraphModel.Edge;
import com.github.isdream.kschd.GraphModel.Vertex;
import com.github.isdream.kschd.capacities.NumberCapacity;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月7日
 */
public class GraphBuilderMocker {

	/**
	 * @see <a href=
	 *      "https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm">Dijkstra's
	 *      Algorithm (Wikipedia)</a> <br>
	 * 
	 * @return graph
	 */
	public static GraphModel createDijkstraGraph_wiki() {
		
		GraphModel graphModel = new GraphModel();
		
		{
			graphModel.addVertex(new Vertex("1"));
			graphModel.addVertex(new Vertex("2"));
			graphModel.addVertex(new Vertex("3"));
			graphModel.addVertex(new Vertex("4"));
			graphModel.addVertex(new Vertex("5"));
			graphModel.addVertex(new Vertex("6"));
		}

		{
			graphModel.addEdge(new Edge(7, graphModel.getVertex("1"), graphModel.getVertex("2")));
			graphModel.addEdge(new Edge(9, graphModel.getVertex("1"), graphModel.getVertex("3")));
			graphModel.addEdge(new Edge(14, graphModel.getVertex("1"), graphModel.getVertex("6")));
			
			graphModel.addEdge(new Edge(10, graphModel.getVertex("2"), graphModel.getVertex("3")));
			graphModel.addEdge(new Edge(15, graphModel.getVertex("2"), graphModel.getVertex("4")));
			
			graphModel.addEdge(new Edge(11, graphModel.getVertex("3"), graphModel.getVertex("4")));
			graphModel.addEdge(new Edge(2, graphModel.getVertex("3"), graphModel.getVertex("6")));
			
			graphModel.addEdge(new Edge(9, graphModel.getVertex("6"), graphModel.getVertex("5")));
			
			graphModel.addEdge(new Edge(6, graphModel.getVertex("4"), graphModel.getVertex("5")));
		}
		
		return graphModel;
	}
	
	
	
	/**
	 * @see <a href=
	 *      "https://en.wikipedia.org/wiki/Edmonds%E2%80%93Karp_algorithm">Edmonds-Karp
	 *      Algorithm (Wikipedia)</a> <br>
	 * 
	 * @return graph
	 */
	public static GraphModel createEdmondsKarpGraph_wiki() {
		
		GraphModel GraphModel = new GraphModel();
		
		{
			GraphModel.addVertex(new Vertex("A"));
			GraphModel.addVertex(new Vertex("B"));
			GraphModel.addVertex(new Vertex("C"));
			GraphModel.addVertex(new Vertex("D"));
			GraphModel.addVertex(new Vertex("E"));
			GraphModel.addVertex(new Vertex("F"));
			GraphModel.addVertex(new Vertex("G"));
		}

		{
			GraphModel.addEdge(new Edge(0, new NumberCapacity(3), GraphModel.getVertex("A"), GraphModel.getVertex("B")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(3), GraphModel.getVertex("A"), GraphModel.getVertex("D")));
			
			GraphModel.addEdge(new Edge(0, new NumberCapacity(4), GraphModel.getVertex("B"), GraphModel.getVertex("C")));
			
			GraphModel.addEdge(new Edge(0, new NumberCapacity(3), GraphModel.getVertex("C"), GraphModel.getVertex("A")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(1), GraphModel.getVertex("C"), GraphModel.getVertex("D")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(2), GraphModel.getVertex("C"), GraphModel.getVertex("E")));

			GraphModel.addEdge(new Edge(0, new NumberCapacity(6), GraphModel.getVertex("D"), GraphModel.getVertex("F")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(2), GraphModel.getVertex("D"), GraphModel.getVertex("E")));
			
			GraphModel.addEdge(new Edge(0, new NumberCapacity(1), GraphModel.getVertex("E"), GraphModel.getVertex("B")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(1), GraphModel.getVertex("E"), GraphModel.getVertex("G")));
			
			GraphModel.addEdge(new Edge(0, new NumberCapacity(9), GraphModel.getVertex("F"), GraphModel.getVertex("G")));
		}
		
		return GraphModel;
	}
	
	public static GraphModel createSchedScenarioT3T1T2N1N2() {
		
		GraphModel GraphModel = new GraphModel();
		
		{
			GraphModel.addVertex(new Vertex("S"));
			GraphModel.addVertex(new Vertex("T1"));
			GraphModel.addVertex(new Vertex("T2"));
			GraphModel.addVertex(new Vertex("T3"));
			GraphModel.addVertex(new Vertex("N1"));
			GraphModel.addVertex(new Vertex("N2"));
			GraphModel.addVertex(new Vertex("E"));
		}

		{
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T3")));
			GraphModel.addEdge(new Edge(2, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T1")));
			GraphModel.addEdge(new Edge(2, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T2")));

			GraphModel.addEdge(new Edge(0, new NumberCapacity(1), GraphModel.getVertex("T3"), GraphModel.getVertex("N1")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(1), GraphModel.getVertex("T3"), GraphModel.getVertex("N2")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(1), GraphModel.getVertex("T1"), GraphModel.getVertex("N1")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(1), GraphModel.getVertex("T2"), GraphModel.getVertex("N1")));
		
			GraphModel.addEdge(new Edge(0, new NumberCapacity(2), GraphModel.getVertex("N1"), GraphModel.getVertex("E")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(2), GraphModel.getVertex("N2"), GraphModel.getVertex("E")));
			
		}
		
		return GraphModel;
	}
	
	public static GraphModel createSchedScenarioT1T2T3N1N2() {
		
		GraphModel GraphModel = new GraphModel();
		
		{
			GraphModel.addVertex(new Vertex("S"));
			GraphModel.addVertex(new Vertex("T1"));
			GraphModel.addVertex(new Vertex("T2"));
			GraphModel.addVertex(new Vertex("T3"));
			GraphModel.addVertex(new Vertex("N1"));
			GraphModel.addVertex(new Vertex("N2"));
			GraphModel.addVertex(new Vertex("E"));
		}

		{
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T1")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T2")));
			GraphModel.addEdge(new Edge(2, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T3")));
			
			GraphModel.addEdge(new Edge(0, new NumberCapacity(1), GraphModel.getVertex("T1"), GraphModel.getVertex("N1")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(1), GraphModel.getVertex("T2"), GraphModel.getVertex("N1")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(1), GraphModel.getVertex("T3"), GraphModel.getVertex("N2")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(1), GraphModel.getVertex("T3"), GraphModel.getVertex("N1")));
					
			GraphModel.addEdge(new Edge(0, new NumberCapacity(2), GraphModel.getVertex("N1"), GraphModel.getVertex("E")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(2), GraphModel.getVertex("N2"), GraphModel.getVertex("E")));
			
		}
		
		return GraphModel;
	}
	
	
	public static GraphModel createSchedScenarioT3T1T2N2N1() {
		
		GraphModel GraphModel = new GraphModel();
		
		{
			GraphModel.addVertex(new Vertex("S"));
			GraphModel.addVertex(new Vertex("T1"));
			GraphModel.addVertex(new Vertex("T2"));
			GraphModel.addVertex(new Vertex("T3"));
			GraphModel.addVertex(new Vertex("N1"));
			GraphModel.addVertex(new Vertex("N2"));
			GraphModel.addVertex(new Vertex("E"));
		}

		{
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T3")));
			GraphModel.addEdge(new Edge(2, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T1")));
			GraphModel.addEdge(new Edge(2, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T2")));

			GraphModel.addEdge(new Edge(0, new NumberCapacity(1), GraphModel.getVertex("T3"), GraphModel.getVertex("N2")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(1), GraphModel.getVertex("T3"), GraphModel.getVertex("N1")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(1), GraphModel.getVertex("T1"), GraphModel.getVertex("N1")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(1), GraphModel.getVertex("T2"), GraphModel.getVertex("N1")));
		
			GraphModel.addEdge(new Edge(0, new NumberCapacity(2), GraphModel.getVertex("N1"), GraphModel.getVertex("E")));
			GraphModel.addEdge(new Edge(0, new NumberCapacity(2), GraphModel.getVertex("N2"), GraphModel.getVertex("E")));
			
		}
		
		return GraphModel;
	}
	
	//------------------------------------------------------------------
	public static GraphModel createEdmondsKarpGraph1() {
		
		GraphModel GraphModel = new GraphModel();
		
		{
			GraphModel.addVertex(new Vertex("S"));
			GraphModel.addVertex(new Vertex("T1"));
			GraphModel.addVertex(new Vertex("T2"));
			GraphModel.addVertex(new Vertex("T3"));
			GraphModel.addVertex(new Vertex("N1"));
			GraphModel.addVertex(new Vertex("N2"));
			GraphModel.addVertex(new Vertex("E"));
		}

		{
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T1")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T2")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(2), GraphModel.getVertex("S"), GraphModel.getVertex("T3")));
			
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("T1"), GraphModel.getVertex("N1")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("T2"), GraphModel.getVertex("N1")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(2), GraphModel.getVertex("T3"), GraphModel.getVertex("N2")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(2), GraphModel.getVertex("T3"), GraphModel.getVertex("N1")));
			
			
			GraphModel.addEdge(new Edge(1, new NumberCapacity(2), GraphModel.getVertex("N1"), GraphModel.getVertex("E")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(2), GraphModel.getVertex("N2"), GraphModel.getVertex("E")));
			
		}
		
		return GraphModel;
	}
	
	public static GraphModel createEdmondsKarpGraph2() {
		
		GraphModel GraphModel = new GraphModel();
		
		{
			GraphModel.addVertex(new Vertex("S"));
			GraphModel.addVertex(new Vertex("T1"));
			GraphModel.addVertex(new Vertex("T2"));
			GraphModel.addVertex(new Vertex("T3"));
			GraphModel.addVertex(new Vertex("N1"));
			GraphModel.addVertex(new Vertex("N2"));
			GraphModel.addVertex(new Vertex("E"));
		}

		{
			GraphModel.addEdge(new Edge(2, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T1")));
			GraphModel.addEdge(new Edge(2, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T2")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T3")));
			
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("T1"), GraphModel.getVertex("N1")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("T2"), GraphModel.getVertex("N1")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("T3"), GraphModel.getVertex("N2")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("T3"), GraphModel.getVertex("N1")));
			
			
			GraphModel.addEdge(new Edge(1, new NumberCapacity(2), GraphModel.getVertex("N1"), GraphModel.getVertex("E")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(2), GraphModel.getVertex("N2"), GraphModel.getVertex("E")));
			
		}
		
		return GraphModel;
	}
	
	public static GraphModel createEdmondsKarpGraph3() {
		
		GraphModel GraphModel = new GraphModel();
		
		{
			GraphModel.addVertex(new Vertex("S"));
			GraphModel.addVertex(new Vertex("T1"));
			GraphModel.addVertex(new Vertex("T2"));
			GraphModel.addVertex(new Vertex("T3"));
			GraphModel.addVertex(new Vertex("N1"));
			GraphModel.addVertex(new Vertex("N2"));
			GraphModel.addVertex(new Vertex("E"));
		}

		{
			GraphModel.addEdge(new Edge(2, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T1")));
			GraphModel.addEdge(new Edge(2, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T2")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(2), GraphModel.getVertex("S"), GraphModel.getVertex("T3")));
			
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("T1"), GraphModel.getVertex("N1")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("T2"), GraphModel.getVertex("N1")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(2), GraphModel.getVertex("T3"), GraphModel.getVertex("N2")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(2), GraphModel.getVertex("T3"), GraphModel.getVertex("N1")));
			
			
			GraphModel.addEdge(new Edge(1, new NumberCapacity(2), GraphModel.getVertex("N1"), GraphModel.getVertex("E")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(2), GraphModel.getVertex("N2"), GraphModel.getVertex("E")));
			
		}
		
		return GraphModel;
	}
	
	public static GraphModel createEdmondsKarpGraph4() {
		
		GraphModel GraphModel = new GraphModel();
		
		{
			GraphModel.addVertex(new Vertex("S"));
			GraphModel.addVertex(new Vertex("T1"));
			GraphModel.addVertex(new Vertex("T2"));
			GraphModel.addVertex(new Vertex("T3"));
			GraphModel.addVertex(new Vertex("N1"));
			GraphModel.addVertex(new Vertex("N2"));
			GraphModel.addVertex(new Vertex("E"));
		}

		{
			GraphModel.addEdge(new Edge(502, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T1")));
			GraphModel.addEdge(new Edge(502, new NumberCapacity(1), GraphModel.getVertex("S"), GraphModel.getVertex("T2")));
			GraphModel.addEdge(new Edge(501, new NumberCapacity(3), GraphModel.getVertex("S"), GraphModel.getVertex("T3")));
			
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("T1"), GraphModel.getVertex("N1")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(1), GraphModel.getVertex("T2"), GraphModel.getVertex("N1")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(3), GraphModel.getVertex("T3"), GraphModel.getVertex("N2")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(3), GraphModel.getVertex("T3"), GraphModel.getVertex("N1")));
			
			
			GraphModel.addEdge(new Edge(1, new NumberCapacity(2), GraphModel.getVertex("N1"), GraphModel.getVertex("E")));
			GraphModel.addEdge(new Edge(1, new NumberCapacity(2), GraphModel.getVertex("N2"), GraphModel.getVertex("E")));
			
		}
		
		return GraphModel;
	}
	
}
