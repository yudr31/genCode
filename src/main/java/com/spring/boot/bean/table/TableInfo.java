package com.spring.boot.bean.table;

import com.spring.boot.bean.ModelConfig;
import com.spring.boot.bean.table.ColumnInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储表结构的信息
 * @author yuderen
 *
 */
public class TableInfo {
	
	private String tableName;								//表名: ecsys_orderMonitor_autoConfirmOrderRuleGroup
	private String tableComment;							//表注释
	private String priKey;									//主键
	private List<ColumnInfo> columns = new ArrayList<ColumnInfo>();	//所有字段的信息

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public List<ColumnInfo> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnInfo> columns) {
		this.columns = columns;
	}

	public String getPriKey() {
		return priKey;
	}

	public void setPriKey(String priKey) {
		this.priKey = priKey;
	}

}
