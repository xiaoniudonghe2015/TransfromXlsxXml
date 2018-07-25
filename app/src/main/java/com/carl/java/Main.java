package com.carl.java;

import java.io.IOException;

import com.carl.java.excel.config.ConfigUtil;
import com.carl.java.excel.model.BaseModel;
import com.carl.java.excel.model.Xlsx2XmlModel;
import com.carl.java.excel.model.Xml2XlsxModel;

/**
 * @author: He Dong
 */
public class Main {

	private static final int TYPE = 1;//1:xml2xlsx; 2:xlsx2xml
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			selectXmlAndXlsx();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private static void selectXmlAndXlsx() throws IOException {
		int xml_xlsx = TYPE;
		switch (xml_xlsx) {
		case 1:
			String configPath = ConfigUtil.PATH_CONFIG_XML2XLSX;
			System.out.println("start");
			BaseModel model = new Xml2XlsxModel();
			boolean succ = model.exec(configPath);
			System.out.println("end:" + (succ ? "success" : "fail"));
			
			break;

		case 2:
			String configPath2 = ConfigUtil.PATH_CONFIG_XLSX2XML;
			System.out.println("start");
			BaseModel model2 = new Xlsx2XmlModel();
			boolean succ2 = model2.exec(configPath2);
			System.out.println("end:" + (succ2 ? "success" : "fail"));
			break;
		}
	}
}

