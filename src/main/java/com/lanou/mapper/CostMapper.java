package com.lanou.mapper;

import com.lanou.bean.Cost;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dllo on 17/8/30.
 */
@Repository
public interface CostMapper {
    List<Cost> findAllCost();
    int updateCost(Cost cost);
    Cost findCostByCostId(@Param("costId") Integer costId);
    int insertCost(Cost cost);
    Integer findCostIdByName(@Param("name") String name);
    Integer findMaxId();
    int removeCost(@Param("costId") Integer costId);
    int updateCostStatus(Cost cost);
}
