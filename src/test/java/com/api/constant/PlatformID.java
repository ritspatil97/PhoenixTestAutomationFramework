package com.api.constant;

public enum PlatformID {
    FRONT_DESK(2),
    FST(3);

    int code ;

    PlatformID (int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
