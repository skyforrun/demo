# 基础镜像
FROM java:8

# 作者信息
MAINTAINER "zengjun 1262345715@qq.com"

# 添加一个存储空间
VOLUME /tmp

# 暴露8080端口
EXPOSE 80

# 添加变量，如果使用dockerfile-maven-plugin，则会自动替换这里的变量内容
COPY target/*.jar app.jar

# 启动镜像自动运行程序
ENTRYPOINT ["java","-jar","/app.jar"]