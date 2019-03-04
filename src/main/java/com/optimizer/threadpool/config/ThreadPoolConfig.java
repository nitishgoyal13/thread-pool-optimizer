package com.optimizer.threadpool.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 Created by mudit.g on Feb, 2019
 ***/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThreadPoolConfig {

    private int thresholdUsagePercentage = 60;

    private int maxUsagePercentage = 80;

    private int queryDurationInHours = 72;

    private int initialDelayInSeconds = 1;

    private int intervalInSeconds = 86400;

    public int getThresholdUsagePercentage() {
        return thresholdUsagePercentage;
    }

    public void setThresholdUsagePercentage(int thresholdUsagePercentage) {
        this.thresholdUsagePercentage = thresholdUsagePercentage;
    }

    public int getMaxUsagePercentage() {
        return maxUsagePercentage;
    }

    public void setMaxUsagePercentage(int maxUsagePercentage) {
        this.maxUsagePercentage = maxUsagePercentage;
    }

    public int getQueryDurationInHours() {
        return queryDurationInHours;
    }

    public void setQueryDurationInHours(int queryDurationInHours) {
        this.queryDurationInHours = queryDurationInHours;
    }
}