package com.leoliu.train.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberLoginReq {
    @NotBlank(message = "[手机号]不能为空")
    @Pattern(regexp = "^1\\d{10}$",message = "[手机号]验证不通过")
    private String mobile;
    @NotBlank(message = "[验证码]不能为空")
    private String code;

}
