package com.spring.boot.utils;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
	/**
	 * 通过表字段属性类型获取实体对应的属性类型
	 * @param dataType
	 * @param precision
	 * @param scale
	 * @return
	 */
	public static String getPropertyType(String dataType, String precision, String scale) {
		if (dataType.contains("char") || dataType.contains("text")) {
			dataType = "String";
		} else if (dataType.contains("bigint")) {
			dataType = "Long";
		} else if(dataType.contains("int")){
			dataType = "Integer";
		} else if (dataType.contains("float")) {
			dataType = "Float";
		} else if (dataType.contains("double") || dataType.contains("decimal")) {
			dataType = "Double";
		} else if (dataType.contains("number")) {
			if ((StringUtils.isNotBlank(scale))
					&& (Integer.parseInt(scale) > 0)) {
				dataType = "BigDecimal";
			} else if ((StringUtils.isNotBlank(precision))
					&& (Integer.parseInt(precision) > 10)) {
				dataType = "Long";
			} else {
				dataType = "Integer";
			}
		} else if (dataType.contains("decimal")) {
			dataType = "BigDecimal";
		} else if (dataType.contains("date") || dataType.contains("time")) {
			dataType = "Date";
		} else if (dataType.contains("blob")) {
			dataType = "byte[]";
		} else if (dataType.contains("clob")) {
			dataType = "Clob";
		} else if (dataType.contains("numeric")) {
			dataType = "BigDecimal";
		} else {
			dataType = "Object";
		}
		return dataType;
	}

	/**
	 * 获取proto协议类型
	 * @param dataType
	 * @return
	 */
	public static String getProtoType(String dataType){
		String protoType = "";
		if (dataType.contains("String")) {
			protoType = "string";
		} else if (dataType.contains("Long")) {
			protoType = "sint64";
		} else if(dataType.contains("Integer")){
			protoType = "sint32";
		} else if (dataType.contains("Double") || dataType.contains("decimal")) {
			protoType = "double";
		} else {
			protoType = "float";
		}
		return protoType;
	}

	/**
	 * 格式化表字段名对应实体属性名
	 * @param cloumnName
	 * @return
	 */
	public static String formatField(String cloumnName) {
		String[] arrayOfString = cloumnName.split("_");
		cloumnName = "";
		int i = 0;
		int length = arrayOfString.length;
		while (i < length) {
			if (i > 0) {
				String str = arrayOfString[i].toLowerCase();
				str = str.substring(0, 1).toUpperCase()
						+ str.substring(1, str.length());
				cloumnName = cloumnName + str;
			} else {
				cloumnName = cloumnName + arrayOfString[i].toLowerCase();
			}
			++i;
		}
		return cloumnName;
	}

	/**
	 * 将首字母和下划线后的字母转换为大写
	 * @param paramString
	 * @return
	 */
	public static String formatFieldCapital(String paramString) {
		String tempStr = paramString.indexOf("_") > -1 ? formatField(paramString) : paramString;
		String result = tempStr.substring(0, 1).toUpperCase()
				+ tempStr.substring(1);
		return result;
	}

	public static void main(String[] args) {
		String str = "UserName";
		String str1 = "pkg";
		System.out.println(addFileSeparator(str));
	}

	/**
	 * 将字符首字母转换为小写
	 * @param str
	 * @return
	 */
	public static String lowerCaseStringFirst(String str) {
		String b = str.replace(str.charAt(0), (char) (str.charAt(0) + ' '));
		return b;
	}

	/**
	 * 将字符串转成utf-8编码
	 * @param str
	 * @return
	 */
	public static String decodeString(String str){
		String decode = "";
		try {
			decode = new String(str.getBytes("iso-8859-1"),"utf-8");
		} catch (Exception e){
			e.printStackTrace();
		}
		return decode;
	}

	/**
	 * 给字符添加分隔符
	 * 判断字符串是否以文件分隔符（/）结束，否-则加上文件分隔符（/）
	 * @param strArr
	 * @return
	 */
	public static String addFileSeparator(String... strArr){
		String result = "";
		for (String str : strArr){
			if (StringUtils.isNotBlank(str) && !str.endsWith("/")){
				result += str + "/";
			} else {
				result += str;
			}

		}
		return result;
	}

}