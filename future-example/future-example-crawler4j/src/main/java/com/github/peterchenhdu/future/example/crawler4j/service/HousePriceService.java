/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.service;

import com.github.peterchenhdu.future.common.enums.CalendarFieldEnum;
import com.github.peterchenhdu.future.example.crawler4j.dto.DayPriceDto;
import com.github.peterchenhdu.future.example.crawler4j.entity.HouseDetail;
import com.github.peterchenhdu.future.example.crawler4j.mapper.HouseDetailMapper;
import com.github.peterchenhdu.future.util.DateTimeUtils;
import com.github.peterchenhdu.future.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @author PiChen
 * @since 2018/8/18 22:54
 */
@Service
public class HousePriceService {

    @Autowired
    private HouseDetailMapper houseDetailMapper;


    public List<HouseDetail> getLast30Day() {
        Date now = new Date();
        Example example = new Example(HouseDetail.class);
        example.setOrderByClause("create_date ASC");
        example.createCriteria()
                .andGreaterThan("listingTime", DateTimeUtils.add(now, CalendarFieldEnum.DATE, -30).substring(0,10))
                .andLessThanOrEqualTo("listingTime",DateTimeUtils.toDateString(now));
        return houseDetailMapper.selectByExample(example);
    }

    @Cacheable(value="longCache", key="#root.targetClass + #root.methodName")
    public List<DayPriceDto> getLast30DayPriceDto() {
        List<HouseDetail> list = this.getLast30Day();
        Map<String, Double> mapPrice = new HashMap<>();
        Map<String, Integer> mapCount = new HashMap<>();
        for(HouseDetail h:list) {
            String key = h.getListingTime();
            if(ObjectUtils.isEmpty(mapCount.get(key))){
                mapPrice.put(key, Double.parseDouble(h.getUnitPrice()));
                mapCount.put(key, 1);
            } else {

                mapPrice.put(key, mapPrice.get(key)+ Double.parseDouble(h.getUnitPrice()));
                mapCount.put(key, mapCount.get(key) + 1);
            }

        }
        List<DayPriceDto> rstList = new ArrayList<>();
        mapPrice.keySet().forEach(key->{
            DayPriceDto dto = new DayPriceDto();
            //2011-22-22
            dto.setDay(key.substring(5,10));
            dto.setPrice((int) (mapPrice.get(key)/mapCount.get(key)));
            rstList.add(dto);
        });

        return rstList;
    }
}
