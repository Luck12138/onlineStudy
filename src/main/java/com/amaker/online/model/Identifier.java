package com.amaker.online.model;

import java.io.Serializable;

/**
 * @param <KEY>
 */
public interface Identifier<KEY extends Serializable> {

	public KEY getId(); 
	
}
