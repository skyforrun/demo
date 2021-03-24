##https证书
    证书：
    名字与姓氏：root
    组织单位名称：kxyt
    组织名称：kxyt
    城市：wuhan
    省市自治区：wuhan
    国家地区代码：china
    密钥：123456

##logstash捞日志
    进入bin目录下
    logstash -f ../config/eslog.conf
##springboot集成OSS
    子key需要配置OSS相关权限
##springboot集成xxljob
    手动api操作xxljob，需要注释调度中心相关权限
    1、修改 JobGroupController.java
        // 在pageList方法上去除权限校验
        @PermissionLimit(limit = false)
    2、修改 JobInfoController.java
        // 分别在 pageList、add、update、remove、pause、start、triggerJob 方法上添加注解，去除权限校验
        @PermissionLimit(limit = false)
##docker maven 打包
    ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix://var/run/docker.sock
    systemctl daemon-reload
    service docker restart

    1.登录阿里云docker registry
    sudo docker login --username=谁又谁了的 registry.cn-shenzhen.aliyuncs.com
    2.从registry中拉去镜像
    sudo docker pull registry.cn-shenzhen.aliyuncs.com/dockerspacetest/dockerdemo:[镜像版本号]
    3.将镜像推送至registry
    sudo docker login --username=谁又谁了的 registry.cn-shenzhen.aliyuncs.com
    sudo docker tag [imageId] registry.cn-shenzhen.aliyuncs.com/dockerspacetest/dockerdemo:[镜像版本号]
    docker tag [imageId] registry.cn-shenzhen.aliyuncs.com/dockerspacetest/dockerdemo:[镜像版本号]
    sudo docker push registry.cn-shenzhen.aliyuncs.com/dockerspacetest/dockerdemo:[镜像版本号]
