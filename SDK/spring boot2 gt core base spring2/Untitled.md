# 多源数据库切换

# 配置说明

默认DataSource 为：com.zaxxer.hikari.HikariDataSource

    gt:
      datasource:
        multi:
    			#开启数据库切换配置
          enabled: true
          master:
            username: ***
            password: ***
            # 初始化配置
            initial-size: 3
            # 最小连接数
            min-idle: 3
            # 最大连接数
            max-active: 15
            # 获取连接超时时间
            max-wait: 5000
            # 连接有效性检测时间
            time-between-eviction-runs-millis: 90000
            # 最大空闲时间
            min-evictable-idle-time-millis: 1800000
            test-while-idle: true
            test-on-borrow: false
            test-on-return: false
            validation-query: select 1
            driver-class-name: com.mysql.jdbc.Driver
            url: jdbc:mysql://***:3306/eladmin?useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Shanghai
          slave:
            yili:
              username: ***
              password: ***
              driver-class-name: com.mysql.jdbc.Driver
              url: jdbc:mysql://****:3306/yili?useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Shanghai

# 注解

@GDataSource

value 数据源名称

showLog 是否显示日志