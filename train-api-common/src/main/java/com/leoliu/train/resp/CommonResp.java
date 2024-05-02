package com.leoliu.train.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResp<T> {
    /**
     * 业务上的成功或者失败
     */
    private boolean success = true;
    /**
     * 返回的信息
     */
    private String message;
    /**
     * 返回的内容
     */
    private T content;

    public CommonResp(T content) {
        this.content = content;
    }

    public CommonResp(boolean success, String message, T content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }
}
