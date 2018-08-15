/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd.capacities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.github.isdream.kschd.AppRuleBase;
import com.github.isdream.kschd.AppRuleBase.Rule;
import com.github.isdream.kschd.GraphModel.Capacity;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月27日
 */
public class AntiAffinityCapacity extends Capacity {

	/**
	 * 
	 */
	public final static AppRuleBase DEFAULT_RULEBASE = new AppRuleBase();

	
	/**
	 * 
	 */
	public final static String DEFAULT_NAME = "Anti-Affinity";
	
	/**
	 * logger
	 */
	protected final static Logger m_logger = Logger.getLogger(AntiAffinityCapacity.class);
	
	/**
	 * name
	 */
	protected final String name;
	
	/**
	 * ruleBase
	 */
	protected final AppRuleBase ruleBase;
	
	
	/**
	 * black list
	 */
	protected final Set<String> blacklist = new HashSet<String>();
	
	
	/**
	 * cached
	 */
	protected final Map<String, Integer> cached = new HashMap<String, Integer>();
	
	/**
	 * @param ruleBase
	 */
	public AntiAffinityCapacity(AppRuleBase ruleBase) {
		this(DEFAULT_TYPE, DEFAULT_NAME, ruleBase);
	}

	/**
	 * @param type
	 * @param ruleBase
	 */
	public AntiAffinityCapacity(String type, AppRuleBase ruleBase) {
		this(type, DEFAULT_NAME, ruleBase);
		
	}
	
	/**
	 * @param type
	 * @param ruleBase
	 */
	public AntiAffinityCapacity(String type, String name) {
		this(type, name, DEFAULT_RULEBASE);
	}
	
	/**
	 * @param type
	 * @param ruleBase
	 */
	public AntiAffinityCapacity(String type, String name, AppRuleBase ruleBase) {
		super(type);
		this.name = name;
		this.ruleBase = ruleBase;
	}
	
	/* (non-Javadoc)
	 * @see com.github.isdream.kschd.GraphModel.Capacity#lessThan(com.github.isdream.kschd.GraphModel.Capacity)
	 */
	@Override
	public boolean lessThan(Capacity other) {
		if (!(other instanceof AntiAffinityCapacity)) {
			throw new RuntimeException("Invalid parameter type");
		}
		
		if (this instanceof InfinityNonNumericCapacity) {
			return false;
		}
		
		if (other instanceof InfinityNonNumericCapacity) {
			return true;
		}
		
		AntiAffinityCapacity ac = (AntiAffinityCapacity) other;
		
		if (!ruleBase.getRules().containsKey(ac.getName())
				|| !blacklist.contains(ac.getName())) {
			return true;
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.github.isdream.kschd.GraphModel.Capacity#addCapacity(com.github.isdream.kschd.GraphModel.Capacity)
	 */
	@Override
	public void addCapacity(Capacity other) {
		if (!(other instanceof AntiAffinityCapacity)) {
			throw new RuntimeException("Invalid parameter type");
		}
		
		if (this instanceof InfinityNonNumericCapacity) {
			return;
		}
		
		if (!(other instanceof InfinityNonNumericCapacity)) {
			
			AntiAffinityCapacity ac = (AntiAffinityCapacity) other;
			
			Map<String, Rule> rules = ruleBase.getRules().get(ac.getName());
			rules = (rules == null) ? new HashMap<String, Rule>() : rules;
			for (String name : rules.keySet()) {
				
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.github.isdream.kschd.GraphModel.Capacity#subCapacity(com.github.isdream.kschd.GraphModel.Capacity)
	 */
	@Override
	public void subCapacity(Capacity other) {
		if (!(other instanceof AntiAffinityCapacity)) {
			throw new RuntimeException("Invalid parameter type");
		}
		
		if (this instanceof InfinityNonNumericCapacity) {
			return;
		}
		
		if (!(other instanceof InfinityNonNumericCapacity)) {
			
			AntiAffinityCapacity ac = (AntiAffinityCapacity) other;
		}
	}

	/* (non-Javadoc)
	 * @see com.github.isdream.kschd.GraphModel.Capacity#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.github.isdream.kschd.GraphModel.Capacity#isUnused()
	 */
	@Override
	public boolean isUnused() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.github.isdream.kschd.GraphModel.Capacity#clone()
	 */
	@Override
	public Capacity clone() {
		return new AntiAffinityCapacity(getType(), getName(), getRuleBase());
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return ruleBase
 	 */
	public AppRuleBase getRuleBase() {
		return ruleBase;
	}

}
