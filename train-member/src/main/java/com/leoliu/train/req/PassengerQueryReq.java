package com.leoliu.train.req;

import lombok.Data;

@Data
public class PassengerQueryReq extends PageReq{
    private Long memberId;
}

