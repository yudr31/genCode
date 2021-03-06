package com.spring.boot.utils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.spring.boot.bean.table.ColumnInfo;
import com.spring.boot.bean.table.NnkTableInfo;
import com.spring.boot.bean.table.SingleTableInfo;
import com.spring.boot.bean.table.TableInfo;
import org.apache.commons.lang3.StringUtils;


import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataBaseUtil {

	private static Properties props = new Properties();
	private static Connection conn;
	private static Statement statement;
	private static ResultSet resultSet;
	
	private static String driveName;
	private static String url;
	private static String userName;
	private static String password;
	
	static{
		ResourceBundle resource = ResourceBundle.getBundle("application");
		driveName = resource.getString("spring.datasource.driver-class-name");
		url = resource.getString("spring.datasource.url");
		userName = resource.getString("spring.datasource.username");
		password = resource.getString("spring.datasource.password");

		props.setProperty("user",userName);
		props.setProperty("password",password);
		props.setProperty("remarks","true");
		props.setProperty("useInformationSchema","true");
	}
	
	public static NnkTableInfo getTableInfo(NnkTableInfo tableInfo){
		try {
			Class.forName(driveName);
			conn = (Connection) DriverManager.getConnection(url, props);
			DatabaseMetaData dbmd = conn.getMetaData();
			ResultSet tables = dbmd.getTables(null,"%","%",new String[]{"TABLE"});
			while (tables.next()){
				String tableName = tables.getString("TABLE_NAME");
				if(tableName.equals(tableInfo.getTableName().trim())){
					initTableInfo(tableInfo,tables);
					break;
				}
			}
			ResultSet set = dbmd.getColumns(null, "%", tableInfo.getTableName(), "%");	//查询表中的所有字段
			ResultSet set2 = dbmd.getPrimaryKeys(null, "%", tableInfo.getTableName()); //查询表中的主键信息
			getTableKeys(tableInfo,set2);
			addTableColumnInfo(tableInfo, set);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSource();
		}
		return tableInfo;
	}

	private static TableInfo getTableKeys(TableInfo tableInfo,ResultSet resultSet)throws SQLException{
		while (resultSet.next()){
			tableInfo.setPriKey(resultSet.getString("column_name"));
		}
		return tableInfo;
	}

	/**
	 * 初始化表名及表注释等信息
	 * @param tableInfo
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private static NnkTableInfo initTableInfo(NnkTableInfo tableInfo, ResultSet resultSet) throws SQLException {
		tableInfo.setTableComment(resultSet.getString("REMARKS"));
		String[] paramArr = tableInfo.getTableName().split("_");
		int len = paramArr.length;
		if(len >= 2){
			tableInfo.setModuleName(paramArr[len-2]);
			tableInfo.setInfoName(paramArr[len-1]);
		}
		return tableInfo;
	}
	
	private static TableInfo addTableColumnInfo(TableInfo tableInfo,ResultSet resultSet) throws SQLException{
		while(resultSet.next()){
			ColumnInfo columnInfo = initColumnInfo(resultSet);
			tableInfo.getColumns().add(columnInfo);
		}
		return tableInfo;
	}

	/**
	 * 初始化表字段相关信息
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private static ColumnInfo initColumnInfo(ResultSet resultSet) throws SQLException{
		String columnName = resultSet.getString("column_name");			//表字段的名字
		String columnType = resultSet.getString("type_name");				//表字段的类型
		String columnComment = resultSet.getString("remarks");				//表字段的注释
		String columnPrecision = resultSet.getString("column_size");		//表字段的精度

		String columnScale = resultSet.getString("decimal_digits");		//表字段的缩放
//			String charLength = resultSet.getString("char_octet_length");		//表字段值的长度
		String nullable = resultSet.getString("nullable");					//表字段是否可空
		String dataType = StringUtil.getPropertyType(columnType.toLowerCase(), columnPrecision, columnScale);
		String protoType = StringUtil.getProtoType(dataType);
//			String fieldName = StringUtil.formatField(columnName);

//		ColumnInfo columnInfo = new ColumnInfo(columnName,columnType,0);
		ColumnInfo columnInfo = new ColumnInfo();
		StringBuffer enumType = new StringBuffer();
		columnInfo.setColumnName(columnName);
		columnInfo.setColumnType(columnType);
		columnInfo.setProtoComment(columnComment);
		columnInfo.setColumnComment(filterComment(columnComment));

		columnInfo.setEnumBool(getColumnEnum(enumType,columnComment));
		columnInfo.setEnumType(enumType.toString().replace("enum",""));
		if (columnInfo.isEnumBool()){
			if (getColumnDict(columnInfo.getEnumType())){
				columnInfo.setDictBool(true);
				columnInfo.setEnumBool(false);
			}
		}
		columnInfo.setAmountBool(getColumnAmount("precision 4",columnComment));
		columnInfo.setNullable(nullable);
		columnInfo.setFiledType(dataType);
		columnInfo.setFieldName(columnName);
		protoType = columnInfo.isEnumBool() ? "common." + columnInfo.getEnumType() : protoType;
		columnInfo.setProtoType(protoType);


		return columnInfo;
	}

	/**
	 * 过滤注释
	 * @param columnComment
	 * @return
	 */
	private static String filterComment(String columnComment){
		String pattern = "[\u4e00-\u9fa5|\\w]+";
		String result = "";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(columnComment);
		if(m.find()){
			result += m.group();
		}
		return result;
	}

	/**
	 * 判断是否是枚举类型
	 * @param enumType
	 * @param columnComment
	 * @return 是-返回true，否-返回false
	 */
	private static boolean getColumnEnum(StringBuffer enumType,String columnComment){
		String pattern = "enum[a-zA-Z]+";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(columnComment.replace(" ",""));
		if(m.find()){
			enumType.append(m.group());
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是字典类型
	 * @param enumType
	 * @return 不包含status-返回true，否则-返回false
	 */
	private static boolean getColumnDict(String enumType){
		if (StringUtils.isNotBlank(enumType)){
			if (enumType.indexOf("Status") == -1 && enumType.indexOf("status") == -1){
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否是金额
	 * @param pattern	正则表达式字符串
	 * @param columnComment	当前列的注释
	 * @return	匹配返回true，否则返回false
	 */
	private static boolean getColumnAmount(String pattern,String columnComment){
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(columnComment);
		if(m.find()){
			return true;
		}
		return false;
	}

	private static void closeSource(){
		try{
			if(resultSet != null){
				resultSet.close();
				resultSet = null;
			}
	        if (statement != null){
	        	statement.close();
	          	statement = null;
	        }
	        if (conn != null){
	        	conn.close();
	        	conn = null;
	        }
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

}
