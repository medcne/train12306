package com.leoliu.train.req;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmOrderQueryReq extends PageReq {

    @Override
    public String toString() {
        return "ConfirmOrderQueryReq{" +
                "} " + super.toString();
    }
}

