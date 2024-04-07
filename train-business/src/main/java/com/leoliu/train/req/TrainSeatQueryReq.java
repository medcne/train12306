package com.leoliu.train.req;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrainSeatQueryReq extends PageReq{
    private String trainCode;
}

