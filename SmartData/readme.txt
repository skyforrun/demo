证书：
    名字与姓氏：root
    组织单位名称：kxyt
    组织名称：kxyt
    城市：wuhan
    省市自治区：wuhan
    国家地区代码：china
    密钥：123456

===================================logstash捞日志=================================
进入bin目录下
logstash -f ../config/eslog.conf
===================================springboot集成OSS=================================
子key需要配置OSS相关权限
===================================springboot集成xxljob==============================
手动api操作xxljob，需要注释调度中心相关权限
    1、修改 JobGroupController.java
        // 在pageList方法上去除权限校验
        @PermissionLimit(limit = false)
    2、修改 JobInfoController.java
        // 分别在 pageList、add、update、remove、pause、start、triggerJob 方法上添加注解，去除权限校验
        @PermissionLimit(limit = false)