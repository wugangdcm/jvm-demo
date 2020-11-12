package com.wdcm.jvm.concurrent.enums;

public enum CountEnum {

    ONE(1, "one"), TWO(2, "two"), THREE(3, "three"), FOUR(4, "four"), FIVE(5, "five"), SIX(6, "six");
    private Integer code;
    private String name;

    CountEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(Integer code) {
        CountEnum[] conntryEnums = CountEnum.values();
        for (int i = 0; i < conntryEnums.length; i++) {
            if (code.compareTo(conntryEnums[i].getCode()) == 0) {
                return conntryEnums[i].getName();
            }
        }
        return null;
    }
}
