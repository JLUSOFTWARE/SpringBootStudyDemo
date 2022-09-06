# JreBel

## 前言

JRebel是一款JVM插件，它使得Java代码修改后不用重启系统，立即生效。IDEA上原生是不支持热部署的，一般更新了 Java 文件后要手动重启 Tomcat 服务器，才能生效，浪费时间浪费生命。

**目前对于idea热部署最好的解决方案就是安装JRebel插件。**

## 配置和使用流程

### 1 - 直接去plugins的Market中找JRebel & XRebel插件下载

![img](https://img-blog.csdnimg.cn/20210507174125963.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xjaG15aHVhODg=,size_16,color_FFFFFF,t_70)

### 2 - 破解

我们知道这个插件是收费的，不想交钱怎么办，下面推荐一个破解版本的，我看了网络上面有好多，破解的特别麻烦，烦躁，今天介绍这款方式，绝对让你用着爽，分分钟钟搞定，让你用着舒服，快捷，设置简单。废话不多说。

- **步骤1:生成一个GUID：**
  - **[在线生成GUID地址](https://www.guidgen.com/)**
- **步骤2: 根据反向代理服务器地址拼接激活地址**
  - **服务器地址： https://jrebel.qekang.com/{GUID}**

如果失效刷新GUID替换就可以！

**步骤3: 打开jrebel 激活面板 . 选择Connect to online licensing service .**

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.79fay1t5eks0.png)

**激活成功的界面：**

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.4xlrxxdo4sw0.png)



### 3 - 配置

#### 1、**为了防止失效，我们设置为work offline即离线运行**

![img](https://img-blog.csdnimg.cn/20210507174445276.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xjaG15aHVhODg=,size_16,color_FFFFFF,t_70)

#### 2、我们还可以自定义设置刷新时间

![img](https://img-blog.csdnimg.cn/2021050717473551.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xjaG15aHVhODg=,size_16,color_FFFFFF,t_70)

#### 3、IDEA一定要设置自动Build项目

![img](https://img-blog.csdnimg.cn/20210507174851831.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xjaG15aHVhODg=,size_16,color_FFFFFF,t_70)

#### ==4、**新版本IDEA中设置运行时允许编译器自动编译**==

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.5n7edfro1rs0.png)

### 4 - 使用

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.w42y6z2cf0g.png)

## 遇到的问题

### Jrebel报错但是依旧可以实现热部署的功能

==**核心报错信息：**==（监测不到配置文件的源）

```bash
2021-09-15 15:32:51 JRebel: ERROR Class 'org.springframework.data.rest.core.mapping.RepositoryResourceMappings' could not be processed by org.zeroturnaround.jrebel.integration.springdata.cbp.RepositoryResourceMappingsCBP@sun.misc.Launcher$AppClassLoader@18b4aac2: org.zeroturnaround.bundled.javassist.CannotCompileException: [source error] populateCache(org.springframework.data.repository.support.Repositories,org.springframework.data.rest.core.config.RepositoryRestConfiguration) not found in org.springframework.data.rest.core.mapping.RepositoryResourceMappings
```

```bash
2021-09-15 16:02:10 JRebel: Cannot monitor properties resources
```



## 参考资料

https://cloud.tencent.com/developer/article/1642800

https://blog.csdn.net/lchmyhua88/article/details/116483816
