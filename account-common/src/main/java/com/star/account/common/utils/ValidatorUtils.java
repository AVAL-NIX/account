package com.star.account.common.utils;

import com.star.account.common.exception.RestException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 */
public final class ValidatorUtils {


    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws RestException  校验不通过，RestException
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws RestException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
        	ConstraintViolation<Object> constraint = (ConstraintViolation<Object>)constraintViolations.iterator().next();
            throw new RestException(constraint.getMessage());
        }
    }


    /**
     * 校验对象
     * @param object        待校验对象
     * @throws RestException  校验不通过，RestException
     */
    public static void validateEntity(Object object)
            throws RestException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Object> constraint = (ConstraintViolation<Object>)constraintViolations.iterator().next();
            throw new RestException(constraint.getMessage());
        }
    }


    public ValidatorUtils() {
    }
}
