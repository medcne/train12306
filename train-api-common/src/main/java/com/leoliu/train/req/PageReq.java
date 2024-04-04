package com.leoliu.train.req;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PageReq {
    @NotNull(message = "页数不能为空")
    private Integer page;
    @NotNull(message = "条数不能为空")
    @Max(value = 100,message = "条数不能超过100")
    private Integer size;
}
