# genCode
代码生成工具
环境部署:
1. 安装docker环境，（ubuntu环境：sudo apt-get install docker.io，centOS环境：su yum -y install docker-io）
2. 安装maven环境 执行docker命令： docker pull hub.c.163.com/library/maven （注：可能存在超时或连接不上，建议多试几次）
3. 安装tomcat环境 执行docker命令： docker pull hub.c.163.com/library/tomcat （注：可能存在超时或连接不上，建议多试几次）
4. 安装mysql环境 执行docker命令：docker pull hub.c.163.com/library/mysql （注：可能存在超时或连接不上，建议多试几次）
5. 进入genCode文件目录，执行后续操作。（注：步骤6、步骤7、步骤8和步骤9均需在genCode目录下执行）
6. 给sql.sh文件添加执行权限：chmod +x sql.sh
7. 启动mysql数据库同时创建数据库 执行docker命令 docker run --name mysql --net=host -e LANG=C.UTF-8 -v "$PWD":/opt/genCode/ -d -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=nnk_code hub.c.163.com/library/mysql --character-set-server=utf8 --collation-server=utf8_general_ci
8. 执行docker命令：docker run -it --rm -v "$PWD":"$PWD" -v "$HOME/.m2":/root/.m2 -w "$PWD" hub.c.163.com/library/maven mvn clean package -nsu -Dmaven.test.skip=true
9. 执行docker命令：docker run --name tomcat --net=host --privileged=true -d -v "$PWD"/target/genCode-0.0.1.war:/usr/local/tomcat/webapps/genCode.war hub.c.163.com/library/tomcat
	注：如等待1分钟左右仍无法访问项目，可执行命令将tomcat容器删除：docker rm -f tomcat 再执行如下命令重新启动tomcat服务，并查看日志。
	docker run -it --restart=on-failure:3 --name tomcat --net=host --privileged=true -v "$PWD"/target/genCode-0.0.1.war:/usr/local/tomcat/webapps/genCode.war hub.c.163.com/library/tomcat
	（注：一般为数据库配置错误导致启动异常，可通过修改src/main/resource/appliction.properties文件修改数据库配置）
	如确定无任何问题，可按ctrl+c退出查看日志操作，执行后续步骤。
10. 进入浏览器输入访问地址访问项目：localhost:8080/genCode/ （注：因tomcat服务启动需要1分钟左右，需等待片刻）
11. 执行docker命令进入mysql容器：docker exec -it mysql /bin/bash
12. 执行sql脚本初始化数据库：./opt/genCode/sql.sh

生成代码操作:
1. 点击生成代码菜单，在请输入表名输入框输入：ecsys_system_productInfo，点击查询单表信息按钮。
2. 勾选对应信息，点击创建按钮即可生成代码。



使用的技术和框架：
1. 前端使用dwz框架。
2. 后台使用spring boot框架。
3. 数据持久层使用spring data做添加和修改。
4. 使用mabaties框架和JDBC技术做查询。
5. 前端页面的渲染和代码模板采用了freemarker模板引擎。
