/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月27日
 */
public class AppRuleBase {

	/**
	 * m_logger
	 */
	protected final static Logger m_logger = Logger.getLogger(AppRuleBase.class);
	
	/**
	 * rules
	 */
	protected final Map<String, Map<String, Rule>> rules = 
					new LinkedHashMap<String, Map<String, Rule>>();
	

	/**
	 * @param rule rule
	 */
	public void addRule(String name, Rule rule) {
		if (CommonUtils.isNull(name) 
				|| CommonUtils.isNull(rule)) {
			m_logger.warn("Invalid parameters.");
			return;
		}
		
		Map<String, Rule> ruleMap = rules.get(name);
		if (ruleMap == null) {
			ruleMap = new LinkedHashMap<String, Rule>();
			this.rules.put(name, ruleMap);
		}
		
		ruleMap.put(rule.getName(), rule);
		
	}
	
	/**
	 * @return all rules
	 */
	public Map<String, Map<String, Rule>> getRules() {
		return rules;
	}

	/**
	 * @author wuheng@otcaix.iscas.ac.cn
	 * @since  2018年7月27日
	 */
	public static class Rule implements Cloneable {
		
		/**
		 * name
		 */
		protected final String name;
		
		/**
		 * cardinality
		 */
		protected final int cardinality;

		/**
		 * @param name name
		 * @param cardinality cardinality
		 */
		public Rule(String name, int cardinality) {
			super();
			this.name = name;
			this.cardinality = cardinality;
		}

		/**
		 * @return name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return cardinality
		 */
		public int getCardinality() {
			return cardinality;
		}

		@Override
		public Rule clone() throws CloneNotSupportedException {
			return new Rule(this.name, this.cardinality);
		}

	}
}
