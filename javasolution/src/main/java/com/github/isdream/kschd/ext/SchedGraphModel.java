/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd.ext;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.github.isdream.kschd.GraphModel;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月28日
 */
public class SchedGraphModel extends GraphModel {

	/*****************************************************************
	 * In other word, a flow means the min capacity of the specified path
	 * 
	 * @author wuheng@otcaix.iscas.ac.cn
	 *
	 ******************************************************************/
	public static class SchdFlow extends Flow {

		/**
		 * size
		 */
		protected int size = 0;
		
		/**
		 * paths
		 */
		protected final Map<String, Path> paths = new LinkedHashMap<String, Path>();

		
		@Override
		public void addFlow(com.github.isdream.kschd.GraphModel.Path path, Capacity cap) {
			String name = path.getEdges().get(0).getTo().getName();
			this.paths.put(name , path);
			if (this.flow == null) {
				this.flow = cap;
				size = path.getEdges().size();
			} else {
				this.flow.addCapacity(cap);
			}
			this.capacities.put(path, cap.clone());
		}

		/**
		 * @param cap
		 */
		public void subFlow(Path path, Capacity cap) {
			this.flow.subCapacity(cap);
			this.paths.remove(path.getEdges().get(0).getTo().getName());
		}

		/**
		 * @return
		 */
		public Collection<Path> getPaths() {
			return paths.values();
		}

		
		/**
		 * @param key
		 * @return
		 */
		public Path getPath(String key) {
			return paths.get(key);
		}
		
		
		public int getSize() {
			return size;
		}


		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			final StringBuilder builder = new StringBuilder();
			for (String name : paths.keySet()) {
				builder.append("---Path ").append(getPathName(paths.get(name))).append("")
						.append(": flow = ").append(capacities.get(paths.get(name))).append("\n");
				builder.append(paths.get(name)).append("\n");;
			}
			return builder.toString();
		}
		
		/**
		 * @param path path
		 * @return path name
		 */
		protected String getPathName(Path path) {
			return path.getEdges().get(0).getTo().getName() + "-"
									+ path.getEdges().get(path.getEdges()
									.size() - 1).getFrom().getName();
		}
	}
}
