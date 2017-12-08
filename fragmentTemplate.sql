NSERT INTO `nnk_code`.`ecsys_fragment_template` (`id`, `create_time`, `status`, `update_time`, `model_content`, `model_name`, `model_type`, `model_desc`) VALUES ('1', '1510910350909', '0', '1510910350909', '<sql id=\"timeWhere\">\r\n	<if test=\"params.hasUpdateTime()\">\r\n		<if test=\"params.updateTime.hasStartTime()\" >\r\n			<![CDATA[AND s0.updateTime >= #{params.updateTime.startTime,jdbcType=BIGINT}]]>\r\n		</if>\r\n		<if test=\"params.updateTime.hasEndTime()\" >\r\n			<![CDATA[AND s0.updateTime <= #{params.updateTime.endTime,jdbcType=BIGINT} ]]>\r\n		</if>\r\n	</if>\r\n	<if test=\"params.hasCreateTime()\">\r\n		<if test=\"params.createTime.hasStartTime()\" >\r\n			<![CDATA[AND s0.createTime >= #{params.createTime.startTime,jdbcType=BIGINT}]]>\r\n		</if>\r\n		<if test=\"params.createTime.hasEndTime()\" >\r\n			<![CDATA[AND s0.createTime <= #{params.createTime.endTime,jdbcType=BIGINT} ]]>\r\n		</if>\r\n	</if>\r\n</sql>', 'timeWhereFragment', '1', '公共时间区间查询条件');
INSERT INTO `nnk_code`.`ecsys_fragment_template` (`id`, `create_time`, `status`, `update_time`, `model_content`, `model_name`, `model_type`, `model_desc`) VALUES ('2', '1510910082936', '0', '1510910082936', '<%@ page import=\"com.nnk.ecsys.product.cmsweb.web.jstl.HtmlUtils\" %>\r\n<%@ page import=\"com.nnk.ecsys.utils.model.enums.enumer.*\" %>\r\n<%@ page contentType=\"text/html;charset=UTF-8\" pageEncoding=\"UTF-8\"%>\r\n<%@ include file=\"/WEB-INF/pages/template/include.jsp\" %>', 'jspHeadFragment', '1', 'jsp头部文件模板');
INSERT INTO `nnk_code`.`ecsys_fragment_template` (`id`, `create_time`, `status`, `update_time`, `model_content`, `model_name`, `model_type`, `model_desc`) VALUES ('3', '1511244852498', '0', '1511245747898', '/**\r\n * @author : yuderen\r\n * @version: ${.now}\r\n */', 'classCommentFragment', '1', '类文件注释1');
INSERT INTO `nnk_code`.`ecsys_fragment_template` (`id`, `create_time`, `status`, `update_time`, `model_content`, `model_name`, `model_type`, `model_desc`) VALUES ('4', '1511252546268', '0', '1511252546268', '    <sql id=\"find${table.entityName?cap_first}Where\">\r\n        <trim prefix=\"WHERE\" prefixOverrides=\"AND|OR\">\r\n            <if test=\"params.has${table.infoName?cap_first}()\">\r\n                <bind name=\"${table.entityName}\" value=\"params.get${table.infoName?cap_first}()\" ></bind>\r\n                <include refid=\"${table.entityName}Sql\" ></include>\r\n            </if>\r\n            <!-- 扩展条件 -->\r\n            <!-- 其他查询条件 -->\r\n            <include refid=\"timeWhere\"></include>\r\n            <if test=\"params.get${table.priKey?cap_first}Count()>0\" >\r\n                AND s0.${table.priKey} in\r\n                <foreach item=\"item\" index=\"index\" collection=\"params.get${table.priKey?cap_first}List()\"\r\n                         open=\"(\" separator=\",\" close=\")\">\r\n                    ${\'#\'}{item}\r\n                </foreach>\r\n            </if>\r\n        </trim>\r\n    </sql>', 'whereCommonQueryFragment', '1', '对象公共查询条件语句碎片');
INSERT INTO `nnk_code`.`ecsys_fragment_template` (`id`, `create_time`, `status`, `update_time`, `model_content`, `model_name`, `model_type`, `model_desc`) VALUES ('5', '1511252739070', '0', '1511252739070', '    <sql id=\"${table.entityName}Sql\">\r\n<#list table.columns as search>\r\n	<#if search.searchColumn && search.columnName != \'updateTime\' && search.columnName != \'createTime\'>\r\n        <if test=\"${table.entityName}.has${search.columnName?cap_first}()\">\r\n            <#if search.columnType == \"INT\">\r\n            AND s0.${search.columnName} = ${\'#\'}{${table.entityName}.${search.columnName}<#if search.enumBool>.number</#if>,jdbcType=INTEGER}\r\n            <#elseif search.columnType == \"VARCHAR\" || search.columnType == \"TEXT\">\r\n            AND s0.${search.columnName} LIKE CONCAT(\'%\',${\'#\'}{${table.entityName}.${search.columnName}<#if search.enumBool>.number</#if>,jdbcType=VARCHAR},\'%\')\r\n            <#else>\r\n            AND s0.${search.columnName} = ${\'#\'}{${table.entityName}.${search.columnName}<#if search.enumBool>.number</#if>,jdbcType=${search.columnType?upper_case}}\r\n            </#if>\r\n        </if>\r\n	</#if>\r\n</#list>\r\n    </sql>', 'objectParamQueryFragment', '1', '对象参数查询条件碎片');
INSERT INTO `nnk_code`.`ecsys_fragment_template` (`id`, `create_time`, `status`, `update_time`, `model_content`, `model_name`, `model_type`, `model_desc`) VALUES ('6', '1511253476116', '0', '1511253476116', '	  <#assign searchCount = 0/>\r\n    <div class=\"pageHeader\">\r\n        <div class=\"searchBar\">\r\n<#list table.columns as search>\r\n    <#if search.searchColumn && search.columnName != \'updateTime\' && search.columnName != \'createTime\'>\r\n	<#if searchCount % 4 == 0>\r\n            <ul class=\"searchContent\">\r\n	</#if>\r\n        <#if search.protoType == \"double\"><#assign paramType = \"number\"><#elseif search.protoType ==\"string\"><#assign paramType = \"\"><#else><#assign paramType = \"digits\"></#if>\r\n                <li>\r\n                    <label>${search.columnComment}：</label>\r\n                <#if search.enumBool == true || search.dictBool == true >\r\n                    <select name=\"${search.columnName}\" class=\"combox\" class=\"editable\" initValue=\"${\'$\'}{param.${search.columnName}}\">\r\n                        <%=HtmlUtils.getSelectorForEnumerWithNone(${search.enumType}Enumer.class)%>\r\n                    </select>\r\n                <#else>\r\n                    <input type=\"text\" name=\"${search.columnName}\" class=\"${paramType} unSubmitBlank\" value=\"${\'$\'}{param.${search.columnName}}\"/>\r\n                </#if>\r\n                </li>\r\n	<#if searchCount % 4 == 3 || !search_has_next>\r\n            </ul>\r\n	</#if>\r\n	<#assign searchCount = searchCount + 1/>\r\n    </#if>\r\n</#list>\r\n	<#if searchCount % 4 == 0>\r\n            <ul class=\"searchContent\">\r\n	</#if>\r\n                <c:import url=\"/WEB-INF/pages/template/search-create-li.jsp\"></c:import>\r\n            </ul>\r\n            <c:import url=\"/WEB-INF/pages/template/search-bar-action.jsp\" ></c:import>\r\n        </div>\r\n    </div>', 'listQueryConditionFragment', '1', 'list查询条件碎片');
INSERT INTO `nnk_code`.`ecsys_fragment_template` (`id`, `create_time`, `status`, `update_time`, `model_content`, `model_name`, `model_type`, `model_desc`) VALUES ('7', '1511253812400', '0', '1511253812400', '<#list table.columns as search>\r\n    <#if search.enumBool>\r\n<c:set var=\"${search.enumType}Enumer\" value=\"<%=${search.enumType}Enumer.class.getName()%>\" />\r\n    </#if>\r\n</#list>', 'setEnumTypeEnumerVarFragment', '1', '设置枚举Enumer类变量');
INSERT INTO `nnk_code`.`ecsys_fragment_template` (`id`, `create_time`, `status`, `update_time`, `model_content`, `model_name`, `model_type`, `model_desc`) VALUES ('8', '1511254124227', '0', '1511254484371', '<#list table.columns as show>\r\n<#if show.showColumn>\r\n<#if show.columnName == table.priKey>\r\n    <th class=\"table-data-id\">${show.columnComment}</th>\r\n<#elseif show.columnName == \'createTime\'>\r\n    <th class=\"table-data-date ${\'$\'}{orderField eq \'createTime\' ? orderDirection : \'nnk-default-asc\'}\" orderField=\"createTime\">创建时间</th>\r\n<#else>\r\n    <th>${show.columnComment}</th>\r\n</#if>\r\n</#if>\r\n</#list>', 'showTableTheadInfoFragment', '1', '显示表头相关属性信息碎片');
INSERT INTO `nnk_code`.`ecsys_fragment_template` (`id`, `create_time`, `status`, `update_time`, `model_content`, `model_name`, `model_type`, `model_desc`) VALUES ('9', '1511254308405', '0', '1511261495538', '<#list table.columns as show>\r\n<#if show.showColumn>\r\n    <#if show.columnName == table.priKey>\r\n        <td><a href=\"${table.infoName}/${table.infoName}ViewForViewer?${table.priKey}=${\'$\'}{itemId}\" target=\"navTab\" rel=\"tab${table.infoName?cap_first}Viewer${\'$\'}{itemId}\" title=\"等级：${\'$\'}{itemId}\">${\'$\'}{itemId}</a></td>\r\n    <#elseif show.columnName == \'updateTime\' || show.columnName == \'createTime\'>\r\n		<td>${\'$\'}{nnk:convertMillisecondToLongDateString(item.${show.columnName})}</td>\r\n	<#else>\r\n        <#if show.enumBool || show.dictBool>\r\n        <td>${\'$\'}{nnk:getEnumStringForNumber(${show.enumType}Enumer, item.${show.columnName}<#if show.enumBool>.number</#if>)}</td>\r\n        <#elseif show.amountBool>\r\n        <td>${\'$\'}{nnk:formatAmountDef(item.${show.columnName})} 元</td>\r\n        <#else>\r\n        <td>${\'$\'}{item.${show.columnName}}</td>\r\n        </#if>\r\n    </#if>\r\n</#if>\r\n</#list>', 'showTableTrInfoFragment', '1', '显示表相关属性的数据值碎片');

