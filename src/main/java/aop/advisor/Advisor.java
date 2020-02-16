package aop.advisor;

/**
 * @ClassName : Advisor
 * @Author : TCW
 * @Date: 2020-02-16 11:08
 */
public interface Advisor {
    String getAdviceBeanName();
    String getExpression();

}
