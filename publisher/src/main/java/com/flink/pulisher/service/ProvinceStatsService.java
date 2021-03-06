package com.flink.pulisher.service;

import com.flink.pulisher.bean.ProvinceStats;

import java.util.List;

/**
 * Author: Felix
 * Date: 2021/2/27
 * Desc:  按照地区统计的业务接口
 */
public interface ProvinceStatsService {
    List<ProvinceStats> getProvinceStats(int date);
}
