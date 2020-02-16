package aop.advisor.impl;

import aop.advisor.PointCutAdvisor;
import aop.pointcut.PointCut;

/**
 * @ClassName : RegexMatchAdvisor
 * @Author : TCW
 * @Date: 2020-02-16 16:57
 */
public class RegexMatchAdvisor implements PointCutAdvisor {


    private String adviceName;
    private String expression;
    private PointCut pointCut;

    public RegexMatchAdvisor(String adviceName, String expression, PointCut pointCut) {
        this.adviceName = adviceName;
        this.expression = expression;
        this.pointCut = pointCut;
    }

    @Override
    public PointCut getPointCutResolver() {
        return this.pointCut;
    }

    @Override
    public String getAdviceBeanName() {
        return this.adviceName;
    }

    @Override
    public String getExpression() {
        return this.expression;
    }
}
