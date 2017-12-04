INSERT INTO `nnk_code`.`ecsys_data_dict` (`id`, `description`, `label`, `parent`, `sort`, `status`, `type`, `value`) VALUES ('1', '记录状态为有效', '有效', NULL, '1', NULL, 'RecordStatusType', '0');
INSERT INTO `nnk_code`.`ecsys_data_dict` (`id`, `description`, `label`, `parent`, `sort`, `status`, `type`, `value`) VALUES ('2', '记录状态为无效', '无效', NULL, '2', NULL, 'RecordStatusType', '1');
INSERT INTO `nnk_code`.`ecsys_data_dict` (`id`, `description`, `label`, `parent`, `sort`, `status`, `type`, `value`) VALUES ('3', '记录状态为已删除', '已删除', NULL, '3', NULL, 'RecordStatusType', '2');
INSERT INTO `nnk_code`.`ecsys_data_dict` (`id`, `description`, `label`, `parent`, `sort`, `status`, `type`, `value`) VALUES ('4', '生成单表操作代码模板', '单表模板', NULL, '1', NULL, 'ModelType', '1');
INSERT INTO `nnk_code`.`ecsys_data_dict` (`id`, `description`, `label`, `parent`, `sort`, `status`, `type`, `value`) VALUES ('5', '生成主从表的代码模板', '主从模板', NULL, '2', NULL, 'ModelType', '2');
INSERT INTO `nnk_code`.`ecsys_dict_type` (`id`, `description`, `label`, `scope`, `value`) VALUES ('1', '数据记录的状态（0-有效，1-无效，2-已上传）', '记录状态', '无', 'RecordStatusType');
INSERT INTO `nnk_code`.`ecsys_dict_type` (`id`, `description`, `label`, `scope`, `value`) VALUES ('2', '生成代码的模板类型1', '模板类型', '无', 'ModelType');
INSERT INTO `nnk_code`.`ecsys_model_config` (`id`, `file_name`, `model_content`, `model_name`, `model_type`, `save_path`, `status`) VALUES ('1', 'model.proto', '//${tableInfo.tableComment}\r\nmessage ${tableInfo.infoName?cap_first} {\r\n<#list tableInfo.columns as column>\r\n    optional ${column.protoType}  ${column.columnName} = ${column_index + 1};	//${column.protoComment}\r\n</#list>\r\n}', 'model.proto', '1', 'G://mycode/nnk/test/', '0');
INSERT INTO `nnk_code`.`ecsys_model_config` (`id`, `file_name`, `model_content`, `model_name`, `model_type`, `save_path`, `status`) VALUES ('2', '${tableInfo.moduleName}.proto', 'package com.nnk.ecsys.protocol.cms;\r\n\r\noption optimize_for = LITE_RUNTIME;\r\n\r\nimport \"common/enum.proto\";\r\nimport \"common/errorCode.proto\";\r\nimport \"common/utils.proto\";\r\nimport \"common/order.proto\";\r\nimport \"model.proto\";\r\n\r\n\r\n\r\n// ###############################################################################################\r\n// 功能: 创建${tableInfo.infoName?cap_first}\r\n// 路径: /${tableInfo.moduleName}/create${tableInfo.infoName?cap_first}\r\nmessage Create${tableInfo.infoName?cap_first}Request {\r\n    required common.${tableInfo.infoName?cap_first} ${tableInfo.infoName} = 1;\r\n    optional sint64 operateUserId = 2;\r\n}\r\nmessage Create${tableInfo.infoName?cap_first}Response {\r\n    required common.CommonErrorCodeResponse errorCode = 1;\r\n    optional sint64 ${tableInfo.priKey} = 2;	// 如果创建成功，返回记录的ID\r\n}\r\n\r\n\r\n// ###############################################################################################\r\n// 功能: 删除${tableInfo.infoName?cap_first}，只能假删除\r\n// 路径: /${tableInfo.moduleName}/delete${tableInfo.infoName?cap_first}\r\nmessage Delete${tableInfo.infoName?cap_first}Request {\r\n    repeated sint64 ${tableInfo.priKey} = 1;\r\n    optional sint64 operateUserId = 2;\r\n}\r\nmessage Delete${tableInfo.infoName?cap_first}Response {\r\n    required common.CommonErrorCodeResponse errorCode = 1;\r\n}\r\n\r\n\r\n// ###############################################################################################\r\n// 功能: 修改${tableInfo.infoName?cap_first}\r\n// 路径: /${tableInfo.moduleName}/update${tableInfo.infoName?cap_first}\r\nmessage Update${tableInfo.infoName?cap_first}Request {\r\n    optional common.${tableInfo.infoName?cap_first} ${tableInfo.infoName} = 1;\r\n    optional sint64 operateUserId = 2;\r\n}\r\nmessage Update${tableInfo.infoName?cap_first}Response {\r\n    required common.CommonErrorCodeResponse errorCode = 1;\r\n}\r\n\r\n\r\n// ###############################################################################################\r\n// 功能: 查询${tableInfo.infoName?cap_first}\r\n// 路径: /${tableInfo.moduleName}/query${tableInfo.infoName?cap_first}\r\nmessage Query${tableInfo.infoName?cap_first}Request {\r\n    optional common.CommonPaginationRequest pagination = 1;	// 翻页\r\n    optional common.${tableInfo.infoName?cap_first} ${tableInfo.infoName} = 2;	// 查询条件\r\n    optional common.CommonTimeRange updateTime = 3;		// 更新时间\r\n    optional common.CommonTimeRange createTime = 4;		// 创建时间\r\n    repeated sint64 ${tableInfo.priKey} = 5;		// 多个ID\r\n}\r\nmessage Query${tableInfo.infoName?cap_first}Response {\r\n    required common.CommonErrorCodeResponse errorCode = 1;\r\n    optional common.CommonPaginationResponse pagination = 2;	// 查询成功则返回\r\n    repeated common.${tableInfo.infoName?cap_first} result = 3;			// 结果集\r\n}', 'protobuf.proto', '1', 'G://mycode/nnk/test/', '0');
INSERT INTO `nnk_code`.`ecsys_model_config` (`id`, `file_name`, `model_content`, `model_name`, `model_type`, `save_path`, `status`) VALUES ('3', '${tableInfo.moduleName}Service.java', 'package com.nnk.ecsys.cms.service;\r\n\r\nimport com.nnk.ecsys.cms.model.common.PaginationResponse;\r\nimport com.nnk.ecsys.cms.model.common.ServiceResponse;\r\nimport com.nnk.ecsys.database.mapper.dataDict.entity.${tableInfo.moduleName?cap_first}${tableInfo.infoName?cap_first};\r\nimport com.nnk.ecsys.protocol.cms.${tableInfo.moduleName?cap_first};\r\nimport com.nnk.ecsys.utils.exception.CommonErrorException;\r\n\r\nimport java.util.List;\r\n\r\n/**\r\n * Created by Administrator on .\r\n */\r\npublic interface ${tableInfo.moduleName?cap_first}Service {\r\n\r\n    /**\r\n     * 创建${tableInfo.tableComment}\r\n     * @param ${tableInfo.infoName}\r\n     * @return\r\n     * @throws CommonErrorException\r\n     */\r\n    public ServiceResponse create${tableInfo.infoName?cap_first}(${tableInfo.moduleName?cap_first}${tableInfo.infoName?cap_first} ${tableInfo.moduleName}${tableInfo.infoName?cap_first});\r\n\r\n    /**\r\n     * 删除${tableInfo.tableComment}\r\n     * @param ${tableInfo.priKey}s\r\n     * @return\r\n     * @throws CommonErrorException\r\n     */\r\n    public ServiceResponse deleteSysDataDictClassify(List<Long> ${tableInfo.priKey}s);\r\n\r\n    /**\r\n     * 修改${tableInfo.tableComment}\r\n     * @param ${tableInfo.infoName}\r\n     * @return\r\n     * @throws CommonErrorException\r\n     */\r\n    public ServiceResponse update${tableInfo.infoName?cap_first}(${tableInfo.moduleName?cap_first}${tableInfo.infoName?cap_first} ${tableInfo.moduleName}${tableInfo.infoName?cap_first});\r\n\r\n    /**\r\n     * 查询${tableInfo.tableComment}\r\n     * @param request\r\n     * @return\r\n     */\r\n    public PaginationResponse<${tableInfo.moduleName?cap_first}DataDictClassify> query${tableInfo.infoName?cap_first}(${tableInfo.moduleName?cap_first}.Query${tableInfo.infoName?cap_first}Request request);\r\n\r\n}\r\n', 'service.java', '1', 'G://mycode/nnk/test/', '0');