package com.carl.java.excel.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: He Dong
 */
public class ConfigXml2Xlsx {
	
	private String xmlPath;
	private String otherLanXmlPath;
	private String xlsxPath;
	private String sheetName;
	private String[] language;
	private String value;
	
	public String getXmlPath() {
		return xmlPath;
	}
	public void setXmlPath(String path) {
		this.xmlPath = path;
	}

	public String getOtherLanXmlPath() {
		return otherLanXmlPath;
	}

	public void setOtherLanXmlPath(String otherLanXmlPath) {
		this.otherLanXmlPath = otherLanXmlPath;
	}

	public String getXlsxPath() {
		return xlsxPath;
	}
	public void setXlsxPath(String xlsxPath) {
		this.xlsxPath = xlsxPath;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String[] getLanguage() {
		return language;
	}
	public void setLanguage(String[] language) {
		this.language = language;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("--------------CONFIG-------------")
		.append("\nxmlPath="+ xmlPath)
		.append("\nxlsxPath=" + xlsxPath)
		.append("\notherLanXmlPath=" + otherLanXmlPath)
		.append("\nsheetName=" + sheetName)
		.append("\nlanguage=" + language)
		.append("\nvalue=" + value)
		.append("\n--------------END-------------");
		return sb.toString();
	}
	
	public static List<ConfigXml2Xlsx> parse(String configPath) {
		File f = new File(configPath);
		if (!f.exists()) {
			return null;
		}
		List<ConfigXml2Xlsx> list = new ArrayList<ConfigXml2Xlsx>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(configPath), "UTF-8"));
			String line = null;
			ConfigXml2Xlsx config = null;
			while((line = br.readLine()) != null) {
				line = line.trim();
				
				if (line.startsWith("<xml2xlsx>")) {
					config = new ConfigXml2Xlsx();
				} else if (line.startsWith("</xml2xlsx>")) {
					if (config != null) {
						list.add(config);
					}
				} else {
					if (config == null) {
						continue;
					}
					String[] array = line.split("=");
					if (array.length != 2) {
						continue;
					}
					array[0] = array[0].trim();
					if (array[0].equalsIgnoreCase("xmlPath")) {
						config.setXmlPath(array[1]);
					} else if (array[0].equalsIgnoreCase("xlsxPath")) {
						config.setXlsxPath(array[1]);
					} else if (array[0].equalsIgnoreCase("sheetName")) {
						config.setSheetName(array[1]);
					}  else if (array[0].equalsIgnoreCase("value")) {
						config.setValue(array[1]);
					}  else if (array[0].equalsIgnoreCase("otherLanXmlPath")) {
						config.setOtherLanXmlPath(array[1]);
					}
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				br = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
