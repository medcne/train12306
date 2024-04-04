package com.leoliu.train.req;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerQueryReq extends PageReq{
    private Long memberId;
}
