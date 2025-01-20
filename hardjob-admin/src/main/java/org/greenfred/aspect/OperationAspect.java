package org.greenfred.aspect;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.greenfred.annotation.GlobalInterceptor;
import org.greenfred.annotation.VerifyParam;
import org.greenfred.enums.ResponseCodeEnum;
import org.greenfred.exception.BusinessException;
import org.greenfred.utils.StringTools;
import org.greenfred.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component("operationAspect")
public class OperationAspect {
    private Logger logger = LoggerFactory.getLogger(OperationAspect.class);

    private static final String[] BASE_TYPE_ARRAY = new String[]{"java.lang.String", "java.lang.Integer",
            "java.lang.Long"};

    @Pointcut("@annotation(org.greenfred.annotation.GlobalInterceptor)")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void interceptorDo(JoinPoint joinPoint) throws BusinessException {
        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        GlobalInterceptor interceptor = method.getAnnotation(GlobalInterceptor.class);
        if (interceptor == null) {
            return;
        }
        /**
         * 校验参数
         */
        if (interceptor.checkParam()) {
            validateParams(method, args);
        }
        logger.info("拦截到了方法:{}", method.getName());
    }

    /**
     * 校验参数
     * @param method
     * @param args
     */
    private void validateParams(Method method, Object[] args) throws BusinessException {
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object value = args[i];
            VerifyParam verifyParam = parameter.getAnnotation(VerifyParam.class);
            if (verifyParam == null) {
                continue;
            }
            String paramTypeName = parameter.getParameterizedType().getTypeName();
            // 基础类型
            if (ArrayUtils.contains(BASE_TYPE_ARRAY, paramTypeName)) {
                checkValue(value, verifyParam);
            } else {
                checkObjValue(parameter, value);
            }
        }
    }

    private void checkObjValue(Parameter parameter, Object value) throws BusinessException {
        try {
            String type = parameter.getParameterizedType().getTypeName();
            Class clazz = Class.forName(type);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                VerifyParam fieldVerifyParam = field.getAnnotation(VerifyParam.class);
                if (fieldVerifyParam == null) {
                    continue;
                }
                field.setAccessible(true);
                Object resultValue = field.get(value);
                checkValue(resultValue, fieldVerifyParam);
            }
        } catch (ClassNotFoundException e) {
            logger.error("校验参数失败", e);
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkValue(Object value, VerifyParam verifyParam) throws BusinessException {
        Boolean isEmpty = value == null || StringTools.isEmpty(value.toString());
        Integer length = value == null ? 0 : value.toString().length();

        /**
         * 校验空
         */
        if (isEmpty && verifyParam.required()) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }

        /**
         * 校验长度
         */
        if (!isEmpty && (verifyParam.max() != -1 && verifyParam.max() < length || verifyParam.min() != -1 && verifyParam.min() > length)) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }

        /**
         * 校验正则
         */
        if (!isEmpty && !StringTools.isEmpty(verifyParam.regex().getRegex()) && !VerifyUtils.verify(verifyParam.regex(),
                String.valueOf(value))) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
    }
}
