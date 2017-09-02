package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.utils.MyException;

import java.util.List;

/**
 * Created by dllo on 17/8/30.
 */
public interface CostService {
    PageInfo<Cost> getAllCost(Integer pageNum, Integer pageSize) throws MyException;
    void editCost(Cost cost) throws MyException;
    Cost getCostByCostId(Integer costId) throws MyException;
    void getCostIdByName(String name) throws MyException;
    void addCost(Cost cost) throws MyException;
    void delCost(Integer costId) throws MyException;
    void editCostStatus(Cost cost) throws MyException;

}
