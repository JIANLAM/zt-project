package com.szcti.lcloud.common.utils;

public enum CommonStatus {

    SUCCESS(200,"success"),ERROR(404,"fail"),
    UNCHECKED(0,"未审核"),PASS(1,"通过"),
    UNPASS(2,"未通过"),CHECKLESS(3,"无需审核");
    private CommonStatus(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private int status;

    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public static CommonStatus getCommonStatus(String key) {
        for (CommonStatus s : CommonStatus.values()) {
            if ((s.status+"").equals(key)) {
                return s;
            }
        }
        return null;
    }
}