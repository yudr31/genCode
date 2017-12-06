# genCode
代码生成工具
docker 环境部署
1. 安装docker 环境
2. 安装maven环境 执行docker命令安装maven环境： docker pull hub.c.163.com/library/maven
3. 安装tomcat环境 执行docker命令安装tomcat环境： docker pull hub.c.163.com/library/tomcat
4. 进入genCode文件目录
5. 执行docker命令：docker run -it --rm -v "$PWD":"$PWD" -w "$PWD" hub.c.163.com/library/maven mvn clean package -nsu -Dmaven.test.skip=true
6. 执行docker命令：docker run --privileged=true -v "$PWD"/target/genCode-0.0.1.war:/usr/local/tomcat/webapps/genCode.war  -p 8081:8080 hub.c.163.com/library/tomcat
7. 进入浏览器输入访问地址访问项目：localhost:8081/genCode/


前端使用 dwz框架，后台使用spring boot框架！数据持久层使用spring data做添加和修改，使用mabaties做查询。
