package com.leoliu.train.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberLoginResp {
    private Long id;

    private String mobile;

    private String token;

}
