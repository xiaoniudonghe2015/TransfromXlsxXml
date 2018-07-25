package com.carl.java.excel.model;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.carl.java.excel.Entry;
import com.carl.java.excel.ExcelUtil;
import com.carl.java.excel.XmlUtil;
import com.carl.java.excel.config.ConfigXlsx2Xml;

/**
 * @author: He Dong
 */
public class Xlsx2XmlModel extends BaseModel{

	public Xlsx2XmlModel() {

	}

	public boolean exec(String configPath) {
		List<ConfigXlsx2Xml> configList = ConfigXlsx2Xml.parse(configPath);
		if (configList == null || configList.size() <= 0) {
			System.out.println("Xlsx2XmlModel=null");
			return false;
		}

		try {
			for (ConfigXlsx2Xml config : configList) {
				List<Entry> entryList = ExcelUtil.ReadExcel(config.getXlsxPath(),
						config.getSheetName(), config.getValue(), config.getName());
				XmlUtil.writeXml(entryList, config.getXmlPath());
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

}
