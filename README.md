# genCode
代码生成工具
环境部署:
1. 安装docker环境，（ubuntu环境：sudo apt-get install docker.io，centOS环境：su yum -y install docker-io）
2. 安装maven环境 执行docker命令： docker pull hub.c.163.com/library/maven （注：可能存在超时或连接不上，建议多试几次）
3. 安装tomcat环境 执行docker命令： docker pull hub.c.163.com/library/tomcat （注：可能存在超时或连接不上，建议多试几次）
4. 安装mysql环境 执行docker命令：docker pull hub.c.163.com/library/mysql （注：可能存在超时或连接不上，建议多试几次）
5. 启动mysql数据库同时创建数据库 执行docker命令 docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=nnk_code hub.c.163.com/library/mysql
6. 修改genCode/src/main/resource/application.properties文件连接数据库的配置，将数据ip更改为docker宿主主机ip。
7. 进入genCode文件目录，执行步骤7和步骤8命令。（注：步骤7和步骤8需在genCode目录下执行）
8. 执行docker命令：docker run -it --rm -v "$PWD":"$PWD" -v "$HOME/.m2":/root/.m2 -w "$PWD" hub.c.163.com/library/maven mvn clean package -nsu -Dmaven.test.skip=true
9. 执行docker命令：docker run --name tomcat --privileged=true -v "$PWD"/target/genCode-0.0.1.war:/usr/local/tomcat/webapps/genCode.war -p 8081:8080 hub.c.163.com/library/tomcat
	注：如确认环境及服务无问题且需在后台运行时，可加-d参数执行命令。
	如：docker run --name tomcat --privileged=true -v "$PWD"/target/genCode-0.0.1.war:/usr/local/tomcat/webapps/genCode.war -d -p 8081:8080 hub.c.163.com/library/tomcat
10. 进入浏览器输入访问地址访问项目：localhost:8081/genCode/
11. 初始化数据库信息，执行db.sql,fragmentTemplate.sql和modelConfig.sql数据库脚本，将数据插入到已建好的nnk_code数据库中。

生成代码操作:
1. 执行demo.sql数据库脚本，创建ecsys_system_productInfo表。
2. 点击生成代码菜单，在请输入表名输入框输入：ecsys_system_productInfo，点击查询单表信息按钮。
3. 勾选对应信息，点击创建按钮即可生成代码。



使用的技术和框架：
1. 前端使用dwz框架。
2. 后台使用spring boot框架。
3. 数据持久层使用spring data做添加和修改。
4. 使用mabaties框架和JDBC技术做查询。
5.前端页面的渲染和代码模板采用了freemarker模板引擎。
