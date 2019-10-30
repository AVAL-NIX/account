package com.star.account.model.enums;

import lombok.Getter;

/**
 * @description: // TODO
 * <br>
 * @date: 2019/10/29 11:38
 * @author: zhengxin
 * @version: mall V1.0
 * @since: JDK 1.8
 */
@Getter
public enum TypeEnum {


    ZHICHU(1, "支出"),
    SHOURU(2, "收入");


    private int code;

    private String value;


    TypeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }


    public static TypeEnum getEnum(int code) {
        TypeEnum[] typeEnums = TypeEnum.values();
        for (TypeEnum typeEnum : typeEnums) {
            if (typeEnum.getCode() == code) {
                return typeEnum;
            }
        }
        return null;
    }
}
