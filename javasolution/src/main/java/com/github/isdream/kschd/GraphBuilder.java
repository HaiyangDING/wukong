/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.isdream.kschd.GraphModel.Capacity;
import com.github.isdream.kschd.GraphModel.Vertex;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月30日
 */
public class GraphBuilder {

	/**
	 * graph
	 */
	protected final GraphModel graph = new GraphModel();
	
	/**
	 * @return Source Vertex
	 */
	protected Vertex createSourceVertex() {
		return new Vertex("SourceVertex");
	}
	
	/**
	 * @return Sink Vertex
	 */
	protected Vertex createSinkVertex() {
		return new Vertex("SinkVertex");
	}
	
	
	/**
	 * @return
	 */
	protected List<Vertex> createAppGroups() {
		return null;
	}
	
	protected List<Vertex> createTasks() {
		return null;
	}
	
	protected List<Vertex> createMachines() {
		return null;
	}
	
	public GraphModel build() {
		
		return graph;
	}
	
	
	/**
	 * @author wuheng@otcaix.iscas.ac.cn
	 * @since  2018年7月30日
	 */
	public static abstract class SystemEntity {

		/**
		 * name
		 */
		protected String name; //name
		
		/**
		 * Uuid
		 */
		protected String uuid; //uuid

		/**
		 * capacities
		 */
		protected Map<String, Capacity> capacities = new HashMap<String, Capacity>();
		
		/**
		 * tags
		 */
		protected Set<String> tags = new HashSet<String>();
		
		/**
		 * @return name
		 */
		public String getName() {
			return name;
		}

		
		/**
		 * @return
		 */
		public String getUuid() {
			return uuid;
		}

		/**
		 * @param uuid
		 */
		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		/**
		 * @param name
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * @param type
		 * @return
		 */
		public Capacity getCapacity(String type) {
			return capacities.get(type);
		}

		/**
		 * @return
		 */
		public Map<String, Capacity> getCapacities() {
			return capacities;
		}

		/**
		 * @param capacity
		 */
		public void addCapacity(String type, Capacity capacity) {
			if (type != null 
					|| capacity != null) {
				this.capacities.put(type, capacity);
			}
		}


		/**
		 * @return tags
		 */
		public Set<String> getTags() {
			return tags;
		}
		
		/**
		 * @param tag
		 */
		public void addTag(String tag) {
			if (tag != null) {
				this.tags.add(tag);
			}
		}
		
	}
	
	/**
	 * @author wuheng@otcaix.iscas.ac.cn
	 * @since  2018年7月30日
	 */
	public static class Machine extends SystemEntity {
		
	}
	
	/**
	 * @author wuheng@otcaix.iscas.ac.cn
	 * @since  2018年7月8日
	 */
	public static class Task extends SystemEntity {

		protected String appGroup;
		
		protected String hostName;

		public String getAppGroup() {
			return appGroup;
		}

		public void setAppGroup(String appGroup) {
			this.appGroup = appGroup;
		}

		public String getHostName() {
			return hostName;
		}

		public void setHostName(String hostName) {
			this.hostName = hostName;
		}
		
	}
}
