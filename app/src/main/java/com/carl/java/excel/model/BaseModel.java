package com.carl.java.excel.model;
/**
 * @author: He Dong
 */
public abstract class BaseModel {
	public abstract boolean exec(String configPath);
	
	public static BaseModel newModel(String configPath, Class< ? extends BaseModel> clazz) {
			
		return null;
	}
}
