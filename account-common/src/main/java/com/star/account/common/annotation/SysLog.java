

package com.star.account.common.annotation;

import java.lang.annotation.*;


/**
 * @description: 系统日志
 * <br>
 * @date: 2019/8/22 19:56
 * @author: zhengxin
 * @version: account V1.0
 * @since: JDK 1.8
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog
{

	String value() default "";
}
