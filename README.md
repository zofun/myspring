# 手写Spring


## IOC容器的实现
类的作用解释：

BeanDefinition（接口）：提供创建一个bean的所需信息
BeanFactory（接口）:存放bean，需要使用Bean从BeanFactory中取即可。
BeanFactoryRegister（接口）:用于注册bean到BeanFactory，获取bean对应的BeanDefinition。


![IOC容器类图](https://s2.ax1x.com/2020/02/14/1jCitO.png)


[参考的博客](https://juejin.im/post/5c11b1e06fb9a04a0d56b787)
