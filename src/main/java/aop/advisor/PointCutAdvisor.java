package aop.advisor;

import aop.pointcut.PointCut;

/**
 * @ClassName : PointCutAdvisor
 * @Author : TCW
 * @Date: 2020-02-16 11:11
 */
public interface PointCutAdvisor  extends Advisor{
    PointCut getPointCutResolver();
}
