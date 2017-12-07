# genCode
代码生成工具
docker 环境部署
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
	如：docker run --privileged=true -v "$PWD"/target/genCode-0.0.1.war:/usr/local/tomcat/webapps/genCode.war -d -p 8081:8080 hub.c.163.com/library/tomcat
10. 进入浏览器输入访问地址访问项目：localhost:8081/genCode/


前端使用 dwz框架，后台使用spring boot框架！数据持久层使用spring data做添加和修改，使用mabaties做查询。
