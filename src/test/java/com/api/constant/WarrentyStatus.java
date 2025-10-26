package com.api.constant;

public enum WarrentyStatus {

    IN_WAARENTY(1),
    OUT_WARRENTY(2);

    private int code;

    WarrentyStatus(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
