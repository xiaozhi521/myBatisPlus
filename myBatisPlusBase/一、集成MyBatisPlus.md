#### pom.xml 加入依赖
```xml
<!-- https://mvnrepository.com/artifact/com.baomidou/mybatis-plus -->
        <!-- mp依赖
  			 mybatisPlus 会自动的维护Mybatis 以及MyBatis-spring相关的依赖
  		 -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus</artifactId>
            <version>2.3</version>
        </dependency>
        <!--junit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
        </dependency>
        <!-- log4j -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
        <!-- c3p0 -->
        <dependency>
            <groupId>com.mchange</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.5.2</version>
        </dependency>
        <!-- mysql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.37</version>
        </dependency>
        <!-- spring -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>4.3.10.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-orm</artifactId>
            <version>4.3.10.RELEASE</version>
        </dependency>
```
#### 1、创建 applicationContext.xml
 ```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:mybatis-spring="http://mybatis.org/schema/mybatis-spring"
	xsi:schemaLocation="http://mybatis.org/schema/mybatis-spring http://mybatis.org/schema/mybatis-spring-1.2.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd">
	
	<!-- 数据源 -->
	<context:property-placeholder location="classpath:db.properties"/>
	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
		<property name="driverClass" value="${jdbc.driver}"></property>
		<property name="jdbcUrl" value="${jdbc.url}"></property>
		<property name="user" value="${jdbc.username}"></property>
		<property name="password" value="${jdbc.password}"></property>
	</bean>
	
	<!-- 事务管理器 -->
	<bean id="dataSourceTransactionManager" 
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"></property>
	</bean>
	<!-- 基于注解的事务管理 -->
	<tx:annotation-driven transaction-manager="dataSourceTransactionManager"/>
	
	
	<!--  配置SqlSessionFactoryBean 
		Mybatis提供的: org.mybatis.spring.SqlSessionFactoryBean
		MP提供的:com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean
		2.3 版本使用：com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean
		3.0.7.1版本使用：com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean
	 -->
	<bean id="sqlSessionFactoryBean" class="com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean">
		<!-- 数据源 -->
		<property name="dataSource" ref="dataSource"></property>
		<property name="configLocation" value="classpath:mybatis-config.xml"></property>
		<!-- 别名处理 -->
		<property name="typeAliasesPackage" value="com.mqf.study.beans"></property>		
		
		<!-- 注入全局MP策略配置 -->
		<property name="globalConfig" ref="globalConfiguration"></property>
	</bean>
	
	<!--
		定义MybatisPlus的全局策略配置
		2.3 版本使用：com.baomidou.mybatisplus.entity.GlobalConfiguration
		3.0.7.1版本使用：com.baomidou.mybatisplus.core.config.GlobalConfig
	-->
	<bean id ="globalConfiguration" class="com.baomidou.mybatisplus.entity.GlobalConfiguration">
		<!-- 在2.3版本以后，dbColumnUnderline 默认值就是true -->
		<property name="dbColumnUnderline" value="true"></property>
		
		<!-- 全局的主键策略 -->
		<property name="idType" value="0"></property>
		
		<!-- 全局的表前缀策略配置 -->
		<property name="tablePrefix" value="tbl_"></property>
		
	</bean>
	
	<!-- 
		配置mybatis 扫描mapper接口的路径
	 -->
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<property name="basePackage" value="com.mqf.study.mapper"></property>
	</bean>
	
</beans>
```
####  2、创建 db.properties
```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/mp
jdbc.username=root
jdbc.password=root

```

####  3、创建 mybatis-config.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	
</configuration>

```

####  4、创建 log4j.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/" >

    <appender name="STDOUT" class="org.apache.log4j.ConsoleAppender">
        <param name="Encoding" value="UTF-8"/>
        <layout class="org.apache.log4j.PatternLayout">
            <param name="ConversionPattern" value="%-5p %d{MM-dd HH:mm:ss,SSS} %m  (%F:%L) \n"/>
        </layout>
    </appender>
    <logger name="java.sql">
        <level value="debug"/>
    </logger>
    <logger name="org.apache.ibatis">
        <level value="info"/>
    </logger>
    <root>
        <level value="debug"/>
        <appender-ref ref="STDOUT"/>
    </root>
</log4j:configuration>

```

####  5、测试数据库连接
```java
    private ApplicationContext ioc =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    //测试数据库连接
    @Test
    public void testDataSource() throws Exception {
        DataSource ds  = ioc.getBean("dataSource",DataSource.class);
        System.out.println(ds);
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
```


