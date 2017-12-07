INSERT INTO `ecsys_data_dict` VALUES (1, '记录状态为有效', '有效', NULL, 1, NULL, 'RecordStatusType', '0', 1511164072976, 1511164072976);
INSERT INTO `ecsys_data_dict` VALUES (2, '记录状态为无效', '无效', NULL, 2, NULL, 'RecordStatusType', '1', 1511164072976, 1511164072976);
INSERT INTO `ecsys_data_dict` VALUES (3, '记录状态为已删除', '已删除', NULL, 3, NULL, 'RecordStatusType', '2', 1511164072976, 1511164072976);
INSERT INTO `ecsys_data_dict` VALUES (4, '生成单表操作代码模板', '单表模板', 1, 1, 0, 'ModelType', '1', 1511164072976, 1511164072976);
INSERT INTO `ecsys_data_dict` VALUES (5, '生成主从表的代码模板', '主从模板', NULL, 2, NULL, 'ModelType', '2', 1511164072976, 1511164072976);
INSERT INTO `ecsys_data_dict` VALUES (6, '每页显示10条数据', '10', NULL, 1, 0, 'PageType', '10', 1511164072976, 1511164072976);
INSERT INTO `ecsys_data_dict` VALUES (7, '每页显示20条数据', '20', NULL, 2, 0, 'PageType', '20', 1511164072976, 1511164072976);
INSERT INTO `ecsys_data_dict` VALUES (8, '每页显示50条数据', '50', NULL, 4, 0, 'PageType', '50', 1511164072976, 1511164072976);
INSERT INTO `ecsys_data_dict` VALUES (9, '每页显示100条数据', '100', NULL, 5, 0, 'PageType', '100', 1511164072976, 1511164072976);
INSERT INTO `ecsys_data_dict` VALUES (10, '', '批量添加模板', NULL, NULL, 0, 'ModelType', '3', 1511164072976, 1511164072976);


INSERT INTO `ecsys_dict_type` VALUES (1, '数据记录的状态（0-有效，1-无效，2-已上传）', '记录状态', '无', 'RecordStatusType', 1509014000000, NULL, NULL);
INSERT INTO `ecsys_dict_type` VALUES (2, '生成代码的模板类型1', '模板类型', '无', 'ModelType', 1509014000000, NULL, NULL);
INSERT INTO `ecsys_dict_type` VALUES (3, '每页显示条数字典类型', '分页类型', '无', 'PageType', 1511244665609, NULL, 1511244665609);

