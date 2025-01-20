package org.greenfred.enums;

public enum DateTimePatternEnum {
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"), YYYY_MM_DD("yyyyy-MM-dd");
    private String pattern;
    DateTimePatternEnum(String pattern) {
        this.pattern = pattern;
    }
    public String getPattern() {
        return pattern;
    }
}
