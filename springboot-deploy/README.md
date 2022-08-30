# 使用Minikube部署Java服务

部署脚本维护在./deploy目录下. 按照以下指南在你本地Minikube上部署Java服务.

## Prerequisites

开始之前,请确保以下组件安装在你的机器上:

- Docker
- Minikube

ps：其他kubernetes集群启动方式可能不一样，具体以目标集群为准。

## 部署阶段:

### Step 1:

开始部署前,确保启动minikube和获取最新部署文件!!!

启动Minikube:

```bash
minikube start --driver=docker #使用 docker 驱动启动集群
```

在./deploy/springboot-deploy/deploy目录下执行命令:

```bash
kubectl apply -f deploy
```

查看pod,svc,deployment信息:

```bash
kubectl get pod,svc,deployment -n springboot-deploy
```

## 测试

获取 minikube的 IP 和service的NodePort

```bash
minikube service springboot-deploy -n springboot-deploy --url
```

本地访问链接：http://127.0.0.1:TUNNEL_PORT/test，预期输入：...Hello World...