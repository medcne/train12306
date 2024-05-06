package com.leoliu.train.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ConfirmOrderDoReq {
    /**
     * 日志跟踪号
     */
    private String logId;

    /**
     * 会员id
     */
    @Setter
    @Getter
    private Long memberId;

    /**
     * 日期
     */
    @Setter
    @Getter
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @NotNull(message = "【日期】不能为空")
    private Date date;

    /**
     * 车次编号
     */
    @Getter
    @NotBlank(message = "【车次编号】不能为空")
    private String trainCode;

    /**
     * 出发站
     */
    @NotBlank(message = "【出发站】不能为空")
    private String start;

    /**
     * 到达站
     */
    @NotBlank(message = "【到达站】不能为空")
    private String end;

    /**
     * 余票ID
     */
    @NotNull(message = "【余票ID】不能为空")
    private Long dailyTrainTicketId;

    /**
     * 车票
     */
    @NotEmpty(message = "【车票】不能为空")
    private List<ConfirmOrderTicketReq> tickets;

    /**
     * 验证码
     */
    @NotBlank(message = "【图片验证码】不能为空")
    private String imageCode;

    /**
     * 图片验证码token
     */
    @NotBlank(message = "【图片验证码】参数非法")
    private String imageCodeToken;


    @Override
    public String toString() {
        return "ConfirmOrderDoReq{" +
                "memberId=" + memberId +
                ", date=" + date +
                ", trainCode='" + trainCode + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", dailyTrainTicketId=" + dailyTrainTicketId +
                ", tickets=" + tickets +
                ", imageCode='" + imageCode + '\'' +
                ", imageCodeToken='" + imageCodeToken + '\'' +
                '}';
    }


}
