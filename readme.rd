
1.创建数据库。
创建语句		   ：tables.sql

2.插入初始化数据
插入初始化数据：init.data.sql





管理员帐号：admin
密码：admin             backup_db.sql


Cache配置修改。

配置文件（spring-cache.xml ）中已经修改为如下配置：

    <!-- redis 配置,也可以把配置挪到properties配置文件中,再读取 -->
    <bean id="jedisPool" class="redis.clients.jedis.JedisPool">
    	<constructor-arg index="0" ref="jedisPoolConfig" />
        <constructor-arg index="2" value="6379"  name="port" type="int"/>
        <constructor-arg index="3" value="5000"  name="timeout" type="int"/>
        <constructor-arg index="1" value="127.0.0.1" name="host" type="java.lang.String"/>
    </bean>




DB环境：
    可以直接导入
