/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.isdream.kschd.capacities;

import com.github.isdream.kschd.GraphModel.Capacity;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2018年7月27日
 */
public class InfinityNumberCapacity extends NumberCapacity {

	/**
	 * 
	 */
	public InfinityNumberCapacity() {
		super(Float.MAX_VALUE);
	}
	
	/* (non-Javadoc)
	 * @see com.github.isdream.kschd.GraphModel.Capacity#clone()
	 */
	@Override
	public Capacity clone() {
		return this;
	}

}
