package com.leoliu.train.req;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrainStationQueryReq extends PageReq{
    private String trainCode;
}

