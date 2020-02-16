package aop.advisor;

import java.util.List;

/**
 * @ClassName : AdvisorRegister
 * @Author : TCW
 * @Date: 2020-02-16 11:26
 */
public interface AdvisorRegister {
    void register(Advisor advisor);

    List<Advisor> getAdvisor();
}
