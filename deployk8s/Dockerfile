#基础镜像，如果本地仓库没有，会从远程仓库拉取
FROM openjdk:11
ENV TZ=Asia/Shanghai
# 指定维护者的名字
MAINTAINER autoML
COPY ./target/deployk8s.jar /app/deployk8s.jar
#暴露端口
EXPOSE 9900
#指定容器启动时要执行的命令
ENTRYPOINT ["java","-jar","/app/deployk8s.jar"]

