package com.leoliu.train.req;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TrainCarriageQueryReq extends PageReq{
    private String trainCode;
}

