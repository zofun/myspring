package aop.advisor;

import java.util.List;

/**
 * @ClassName : AdvisorRegister
 * @Author : TCW
 * @Date: 2020-02-16 11:26
 * 切面注册接口
 */
public interface AdvisorRegister {

    /**
     * 注册切面
     * @param advisor
     */
    void register(Advisor advisor);

    /**
     * 获取所有的切面
     * @return
     */
    List<Advisor> getAdvisor();
}
