
# Docker快速上手

- 获取docker版本信息

  ```
  docker version
  ```



- docker命令的基础格式

  ```shell
  docker [manager command] [command] [arg...]
  ```

  

- 镜像相关命令

  https://docs.docker.com/reference/cli/docker/image/

  ```shell
  // 查看镜像列表
  docker image ls
  
  // 查看镜像信息
  docker image inspect imageId
  
  // 删除指定镜像
  docker image rm [OPTIONS] IMAGE [IMAGE...]
  
  // 删除所有未被任何容器使用的镜像
  docker image prune -a
  ```

​	

- 容器相关命令

  https://docs.docker.com/reference/cli/docker/container/

  ```shell
  // 后台启动容器
  docker container run -d nginx
  
  // 停止容器
  docker container stop containerId
  
  // 在容器上执行交互式命令
  docker exec -it sh
  
  // 移除所有已停止容器
  docker container prune
  
  // 查看容器TOP信息
  docker container top containerId
  ```



- 镜像的构建[Docker File]  

  Docker可以读取Dockerfile中的指令自动构建镜像

  ```shell
  # 基础镜像尽可能选择官方镜像
  # 尽可能选择比较小的镜像，如:alpine。手动指定版本，不要使用latest版本
  FROM 基础镜像
  
  # 工作目录
  WORKDIR 目录
  
  # 构建参数和环境变量
  # ARG和ENV指定最大的区别在于它们的作用域，ARG指定定义的参数仅在构建镜像期间可用，而ENV指定定义的环境变量在容器运行时可用
  # ARG指定可以由--build-arg选项在构建时进行设置，而EVN指定在构建时无法修改，因此如果你需要再构建时传递某些参数应该使用ARG
  ARG
  ENV
  
  # 主要用于在Image里执行命令，如安装软件，下载文件等
  # 每一行的RUN命令都会产生一层image layer，尽量避免过多的RUN导致镜像臃肿
  RUN 执行指令
  
  # COPY和ADD都可以把local的一个文件复制到镜像里，如果目标目录不存在则会自动创建
  # ADD比COPY高级一点的是 支持下载远程文件和自动解压，绝大多数场景下推荐使用COPY
  ADD
  COPY
  
  # 对外暴露端口
  EXPOSE
  
  # 容器启动时默认执行的命令
  # 如果docker container run启动容器时执行了其他命令，则CMD命令会被忽略
  # 如果定义了多个CMD只有最后一个会被执行
  CMD
  
  # CMD设置的命令可以在docker container run时传入其他命令，覆盖掉CMD的命令
  # 但是ENTRYPOINT所设置的命令是一定会被执行的，除非用户在启动时显式使用--entrypoint参数
  # ENTRYPOINT和CMD可以联合使用，ENTRYPOINT设置执行的命令，CMD传递参数，以下是使用示例
  # ENTRYPOINT ["python", "app.py"] \ CMD ["--help"]
  # 需要注意的是: 在使用ENTRYPOINT时，你必须使用数组格式（Exec 格式），否则CMD的联合使用会失效
  ENTRYPOINT
  ```

  小技巧:

  - 在构建镜像的时候应该尽可能使用cache(变动的往后靠，不变的往前)
  - 将多个命令合并到一个RUN中执行，可以减少镜像的层数
  - Docker镜像中尽量使用非root用户，降低安全风险

  - 可以使用.dockerignore文件来防止不必要的文件进入Docker上下文，从而可能间接得显著减少镜像大小
  - 多阶段构建。如将编译环境和运行环境分离，可以极大的精简镜像体积。【使用场景有限，现在都先编译完再打镜像】

  

  