/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd.capacities;

import com.github.isdream.kschd.GraphModel.Capacity;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月27日
 * 
 * It's used to record the capacity for the specified edge
 */
public class NumberCapacity extends Capacity {

	
	/**
	 * availableCapacity
	 */
	protected float availableCapacity = 0;
	
	/**
	 * totalCapacity
	 */
	protected float totalCapacity = 0;
	
	/**
	 * We would not check the parameters
	 * 
	 * @param capacity capacity
	 */
	public NumberCapacity(float capacity) {
		this(DEFAULT_TYPE, capacity);
	}
	
	/**
	 * @param type 
	 * @param capacity
	 */
	public NumberCapacity(String type, float capacity) {
		super(type);
		this.availableCapacity = capacity;
		this.totalCapacity = capacity;
	}

	/* (non-Javadoc)
	 * @see com.github.isdream.kschd.GraphModel.Capacity#lessThan(com.github.isdream.kschd.GraphModel.Capacity)
	 */
	@Override
	public boolean lessThan(Capacity other) {
		if (!(other instanceof NumberCapacity)) {
			throw new RuntimeException("Invalid parameter type");
		}
		
		if (this instanceof InfinityNumberCapacity) {
			return false;
		}
		
		if (other instanceof InfinityNumberCapacity) {
			return true;
		}
		
		NumberCapacity sc = (NumberCapacity) other;
		return availableCapacity < sc.availableCapacity;
	}

	/* (non-Javadoc)
	 * @see com.github.isdream.kschd.GraphModel.Capacity#addCapacity(com.github.isdream.kschd.GraphModel.Capacity)
	 */
	@Override
	public void addCapacity(Capacity other) {
		if (!(other instanceof NumberCapacity)) {
			throw new RuntimeException("Invalid parameter type");
		}
		
		if (this instanceof InfinityNumberCapacity) {
			return;
		}
		
		if (!(other instanceof InfinityNumberCapacity)) {
			NumberCapacity sc = (NumberCapacity) other;
			availableCapacity += sc.availableCapacity;
		}
	}

	/* (non-Javadoc)
	 * @see com.github.isdream.kschd.GraphModel.Capacity#subCapacity(com.github.isdream.kschd.GraphModel.Capacity)
	 */
	@Override
	public void subCapacity(Capacity other) {
		if (!(other instanceof NumberCapacity)) {
			throw new RuntimeException("Invalid parameter type");
		}
		
		if (this instanceof InfinityNumberCapacity) {
			return;
		}
		
		if (!(other instanceof InfinityNumberCapacity)) {
			NumberCapacity sc = (NumberCapacity) other;
			availableCapacity -= sc.availableCapacity;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.github.isdream.air17.core.ICapacity#isFree()
	 */
	public boolean isUnused() {
		return totalCapacity == availableCapacity;
	}

	/* (non-Javadoc)
	 * @see com.github.isdream.kschd.GraphModel.Capacity#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return availableCapacity == 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Capacity clone() {
		return new NumberCapacity(availableCapacity);
	}
	
	/**
	 * @return available capacity
	 */
	public float getAvailableCapacity() {
		return availableCapacity;
	}

	/**
	 * @return total capacity
	 */
	public float getTotalCapacity() {
		return totalCapacity;
	}
	
	@Override
	public String toString() {
		return String.valueOf(availableCapacity);
	}

}
