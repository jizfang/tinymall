# Tinymall

一个小型电商网站，包括微信小程序、管理后台

## swagger接口文档

http://localhost:8090/swagger-ui.html

## 技术点
- mybatis
- springboot
- 通用mapper


## 接口响应体格式统一封装
1. Result 是返回格式类的父接口（所有返回格式类都需要继承它）
2. PlatformResult 通用返回结果格式（我们上面说的第二种返回结果）
3. DefaultErrorResult 全局错误返回结果（我们上面说的第一种错误时的返回结果）
4. GlobalExceptionHandler全局异常处理
5. ResponseResult 注解类（用于在Controller上指定返回值格式类）
6. ResponseResultInterceptor 拦截器（主要用于将ResponseResult注解类的标记信息传入ResponseResultHandler中）
7. ResponseResultHandler 响应体格式处理器（主要转换逻辑都在这里）

# 踩的坑
## 1. 通用mapper生成代码使用lombok
1. 在pom文件添加依赖
```xml
<dependency>
   <groupId>org.projectlombok</groupId>
   <artifactId>lombok</artifactId>
   <version>1.18.12</version>
   <scope>provided</scope>
</dependency>
```
2. 在generatorConfig plugin中配置lombok插件
```xml
<!--lombok-->
<property name="lombok" value="Getter,Setter,ToString"/>
```
## 2.swagger在配置了静态资源后无法访问
```java
registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
```
## 3.查询出现mysql关键字
```java
@Column(name = "`level`")
private String level;
```
## 4. No typehandler found for property
```java
@ColumnType(typeHandler = JsonStringArrayTypeHandler.class)
private String[] gallery;
```
## 参考博客
https://blog.csdn.net/aiyaya_/