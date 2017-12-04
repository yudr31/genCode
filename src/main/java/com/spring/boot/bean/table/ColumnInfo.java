package com.spring.boot.bean.table;

/**
 * 封装表中一个字段的信息
 * @author yuderen
 *
 */
public class ColumnInfo {
	
	private String columnName;		//表字段名称
	private String fieldName;		//实体类属性名
	private String columnType;		//字段的数据类型
	private String filedType;		//实体类属性类型

	private String protoType;		//proto协议类型
	private String protoComment;	//协议注释
	private String columnComment;	//字段的注释
	private String tag;				//前端页面标签类型

	private boolean enumBool;		//是否是枚举类型
	private boolean amountBool;		//是否是金额
	private boolean dictBool;		//是否是字典
	private boolean searchColumn;	//是否是搜索项
	private boolean showColumn;		//是否是展示项

	private boolean batchColumn;	//是否是批量添加项
	private String enumType;		//枚举类型
	private String nullable;		//字段是否可为空
	private int keyType;			//字段的键类型(0:普通键，1：主键，2：外键)

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getFiledType() {
		return filedType;
	}

	public void setFiledType(String filedType) {
		this.filedType = filedType;
	}

	public int getKeyType() {
		return keyType;
	}

	public void setKeyType(int keyType) {
		this.keyType = keyType;
	}

	public String getProtoType() {
		return protoType;
	}

	public void setProtoType(String protoType) {
		this.protoType = protoType;
	}

	public String getProtoComment() {
		return protoComment;
	}

	public void setProtoComment(String protoComment) {
		this.protoComment = protoComment;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isAmountBool() {
		return amountBool;
	}

	public void setAmountBool(boolean amountBool) {
		this.amountBool = amountBool;
	}

	public boolean isDictBool() {
		return dictBool;
	}

	public void setDictBool(boolean dictBool) {
		this.dictBool = dictBool;
	}

	public boolean isSearchColumn() {
		return searchColumn;
	}

	public void setSearchColumn(boolean searchColumn) {
		this.searchColumn = searchColumn;
	}

	public boolean isShowColumn() {
		return showColumn;
	}

	public void setShowColumn(boolean showColumn) {
		this.showColumn = showColumn;
	}

	public boolean isBatchColumn() {
		return batchColumn;
	}

	public void setBatchColumn(boolean batchColumn) {
		this.batchColumn = batchColumn;
	}

	public boolean isEnumBool() {
		return enumBool;
	}

	public void setEnumBool(boolean enumBool) {
		this.enumBool = enumBool;
	}

	public String getEnumType() {
		return enumType;
	}

	public void setEnumType(String enumType) {
		this.enumType = enumType;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

}
