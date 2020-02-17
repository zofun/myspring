package aop.advisor;

/**
 * @ClassName : Advisor
 * @Author : TCW
 * @Date: 2020-02-16 11:08
 * 切面。即通知和切点的集合。表示在什么地方，做什么事情。
 */
public interface Advisor {

    /**
     * 获取通知bean的名称
     * @return
     */
    String getAdviceBeanName();

    /**
     * 获取切点表达式（增强的地方）
     * @return
     */
    String getExpression();

}
