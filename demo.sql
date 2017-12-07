CREATE TABLE `ecsys_system_productInfo` (
	`gid`  bigint(20) NOT NULL COMMENT '产品ID。[globalId]' ,
	`productNo`  varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '产品编号' ,
	`productName`  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '产品名称' ,
	`productType`  int(11) NOT NULL DEFAULT 0 COMMENT '产品类型。[enum ProductType]' ,
	`presentAmount`  bigint(20) NOT NULL DEFAULT 0 COMMENT '产品展示的总金额，也即是面值。例如10元70M流量卡，面值就是10元。[precision 4]' ,
	`presentExtend`  text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '标准价扩展信息。[dataRef calculationType]' ,
	`saleEquationType`  int(11) NOT NULL DEFAULT 0 COMMENT '售价算式类型。[enum DiscountEquationType]' ,
	`saleAmount`  bigint(20) NOT NULL DEFAULT 0 COMMENT '产品销售的应售价格。例如10元70M流量卡，面值就是10元。但市场上普遍卖5元，那么售价就是5元。[precision 4]' ,
	`recordStatus`  int(11) NOT NULL DEFAULT 0 COMMENT '产品的状态。[enum CommonRecordStatus]' ,
	`updateTime`  bigint(20) NOT NULL COMMENT '更新时间。[datetime]' ,
	`createTime`  bigint(20) NOT NULL COMMENT '创建时间。[datetime]' ,
	PRIMARY KEY (`gid`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
COMMENT='产品信息表'
ROW_FORMAT=COMPACT
;
