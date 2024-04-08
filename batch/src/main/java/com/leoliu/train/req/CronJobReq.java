package com.leoliu.train.req;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CronJobReq {
    private String group;

    private String name;

    private String description;

    private String cronExpression;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CronJobDto{");
        sb.append("cronExpression='").append(cronExpression).append('\'');
        sb.append(", group='").append(group).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

}