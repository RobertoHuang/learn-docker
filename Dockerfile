# 使用 openjdk:8u171-alpine 作为基础镜像
FROM openjdk:8u171-alpine

# 设置工作目录
WORKDIR /app

# 复制源代码
COPY src/main/java ./src

# 创建编译输出目录
RUN mkdir -p ./classes

# 编译 Java 文件
RUN javac -d ./classes ./src/roberto/growth/process/Main.java

# 运行 Main 类
ENTRYPOINT ["java", "-cp", "./classes", "roberto.growth.process.Main"]

