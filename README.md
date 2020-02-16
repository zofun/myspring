# 手写Spring


## IOC容器的实现
类的作用解释：

BeanDefinition（接口）：提供创建一个bean的所需信息
BeanFactory（接口）:存放bean，需要使用Bean从BeanFactory中取即可。
BeanFactoryRegister（接口）:用于注册bean到BeanFactory，获取bean对应的BeanDefinition。


![IOC容器类图](https://s2.ax1x.com/2020/02/16/3SfRRf.md.png)


[参考的博客](https://juejin.im/post/5c11b1e06fb9a04a0d56b787)


## DI的实现
需要依赖注入的有两个地方，一个是构造参数的依赖注入，一个是属性的依赖注入。


类的作用解释：
BeanReference：标识bean的类型。

![DI类图](https://s2.ax1x.com/2020/02/16/3Sfgit.md.png)



## AOP的实现

### AOP中的一些概念梳理
通知advice：通知定义了一个切面在什么时候完成什么事情。
切点pointCut：切点定义了通知作用在什么地方。
切面Aspect/Advisor：通知和切点组成了切面，表示在指定的时间点什对指点的地方进行一些额外的操作。
链接点join points：连接点表示可以被选择用来增强的位置，连接点是一组集合，在程序运行中的整个周期中都存在。
织入weaving：在不改变代码的情况下，对功能进行增强。


![AOP类图](https://s2.ax1x.com/2020/02/16/3SRmes.md.png)
