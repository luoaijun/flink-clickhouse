package com.flink.pulisher.service;

import com.flink.pulisher.bean.KeywordStats;

import java.util.List;

/**
 * Author: Felix
 * Date: 2021/3/1
 * Desc: 关键词统计接口
 */
public interface KeywordStatsService {
    public List<KeywordStats> getKeywordStats(int date, int limit);
}

