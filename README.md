# 手写Spring


## IOC容器的实现
类的作用解释：

BeanDefinition（接口）：提供创建一个bean的所需信息
BeanFactory（接口）:存放bean，需要使用Bean从BeanFactory中取即可。
BeanFactoryRegister（接口）:用于注册bean到BeanFactory，获取bean对应的BeanDefinition。


![IOC容器类图](https://github.com/zofun/myspring/blob/master/img/ioc.png)


[参考的博客](https://juejin.im/post/5c11b1e06fb9a04a0d56b787)


## DI的实现
需要依赖注入的有两个地方，一个是构造参数的依赖注入，一个是属性的依赖注入。


类的作用解释：
BeanReference：标识bean的类型。

![DI类图](https://github.com/zofun/myspring/blob/master/img/di.png)



## AOP的实现

### AOP中的一些概念梳理
通知advice：通知定义了一个切面在什么时候完成什么事情。
切点pointCut：切点定义了通知作用在什么地方。
切面Aspect/Advisor：通知和切点组成了切面，表示在指定的时间点什对指点的地方进行一些额外的操作。
链接点join points：连接点表示可以被选择用来增强的位置，连接点是一组集合，在程序运行中的整个周期中都存在。
织入weaving：在不改变代码的情况下，对功能进行增强。

### JDK动态代理和CGlib动态代理
1. JDK动态代理的实现原理
JDK动态代理是面向接口的代理模式。
实现过程：
- 通过实现InvocationHandler接口创建自己的调用处理器；
- 通过为Proxy类指定ClassLoader对象和一组interface来创建动态代理；
- 通过反射机制获取动态代理类的构造函数，其唯一参数类型就是调用处理器接口类型；
- 通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数参入。

2. CGlib动态代理的实现原理
CGLib是一个强大、高性能的Code生产类库，可以实现运行期动态扩展java类，Spring在运行期间通过CGlib**继承**（因此final修饰的类无能为力）要被动态代理的类，重写父类的方法，实现AOP面向切面编程呢。CGlib是面向底层字节码的。

![AOP类图](https://github.com/zofun/myspring/blob/master/img/aop.png)



## ApplicationContext的实现
![类图](https://github.com/zofun/myspring/blob/master/img/a1.png)
![3iV02Q.png](https://github.com/zofun/myspring/blob/master/img/a2.png)



## MVC的实现
一个请求的处理过程
![mvc请求的处理露出](https://github.com/zofun/myspring/blob/master/img/mvc.png)



