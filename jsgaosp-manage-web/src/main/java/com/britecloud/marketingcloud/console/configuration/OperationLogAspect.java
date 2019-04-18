package com.britecloud.marketingcloud.console.configuration;

import com.alibaba.fastjson.JSON;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.model.OperationLog;
import com.britecloud.marketingcloud.service.BcLogService;
import com.britecloud.marketingcloud.utils.ClientUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Optional;

@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private BcLogService bcLogService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.britecloud.marketingcloud.console.configuration.OperationLogAnn)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveOperationLog(JoinPoint joinPoint) {
        System.out.println("--保存操作日志--");
        //保存日志
        OperationLog operationLog = new OperationLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        OperationLogAnn myLog = method.getAnnotation(OperationLogAnn.class);
        if (myLog != null) {
            String value = myLog.value();
            operationLog.setOperation(value);//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        operationLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = "";
        try {
            params = JSON.toJSONString(args);
        }catch (Exception ex){
            params = "";
        }
        operationLog.setParams(params);

        Object userObject = ClientUtils.get(ClientUtils.CURRENT_USER);
        BcUser user = userObject == null?null:(BcUser)userObject;

        //获取用户名
        operationLog.setUserLoginName(user == null?"":user.getUserName());

        operationLog.setIp("");
        //调用service保存SysLog实体类到数据库
        bcLogService.saveOperationLog(operationLog);
    }
}
