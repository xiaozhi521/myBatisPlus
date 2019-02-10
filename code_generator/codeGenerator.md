### 代码生成器
1)  MybatisPlus 提供了大量的自定义设置，生成的代码完全能够满足各类型的需求
2)  MybatisPlus 的代码生成器 和 Mybatis MBG 代码生成器:
MybatisPlus 的代码生成器都是基于 java 代码来生成。MBG 基于 xml 文件进行代码生成
MyBatis 的代码生成器可生成: 实体类、Mapper 接口、Mapper 映射文件
MybatisPlus 的代码生成器可生成: 实体类(可以选择是否支持 AR)、Mapper 接口、Mapper 映射
文件、 Service 层、Controller 层.
3)  表及字段命名策略选择
在 MybatisPlus 中，我们建议数据库表名 和 表字段名采用驼峰命名方式， 如果采用下划
线命名方式 请开启全局下划线开关，如果表名字段名命名方式不一致请注解指定，我
们建议最好保持一致。
这么做的原因是为了避免在对应实体类时产生的性能损耗，这样字段不用做映射就能直
接和实体类对应。当然如果项目里不用考虑这点性能损耗，那么你采用下滑线也是没问
题的，只需要在生成代码时配置 dbColumnUnderline 属性就可以

#### 代码生成器依赖
##### 1) 模板引擎
MP 的代码生成器默认使用的是 Apache 的 Velocity 模板，当然也可以更换为别的模板
技术，例如 freemarker。
```xml
<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity-engine-core</artifactId>
    <version>2.0</version>
</dependency>
```
##### 2) 加入 slf4j ,查看日志输出信息
```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.7</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>1.7.7</version>
</dependency>
```