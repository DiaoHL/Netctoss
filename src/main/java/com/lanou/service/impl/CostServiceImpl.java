package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.mapper.CostMapper;
import com.lanou.service.CostService;
import com.lanou.utils.MyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/8/30.
 */
@Service
public class CostServiceImpl implements CostService {
    @Resource
    private CostMapper costMapper;
    public PageInfo<Cost> getAllCost(Integer pageNum, Integer pageSize) throws MyException {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum,pageSize);
        List<Cost> allCost = costMapper.findAllCost();
        System.out.println(allCost);
        if (allCost == null){
            throw new MyException("查询所有咨费失败");
        }
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(allCost);

        return pageInfo;
    }

    public void editCost(Cost cost) throws MyException {
        int i = costMapper.updateCost(cost);
        if (i == 0){
            throw new MyException("修改资费信息失败");
        }
    }

    public Cost getCostByCostId(Integer costId) throws MyException {
        Cost cost = costMapper.findCostByCostId(costId);
        if (cost == null){
            throw new MyException("根据id查询资费失败");
        }
        return cost;
    }

    public void getCostIdByName(String name) throws MyException {
        Integer costIdByName = costMapper.findCostIdByName(name);
        if (costIdByName != null){
            throw new MyException("资费名重复");
        }
    }

    public void addCost(Cost cost) throws MyException {
        Integer maxId = costMapper.findMaxId();
        System.out.println(maxId);
        cost.setCostId(maxId + 1);
        int i = costMapper.insertCost(cost);
        if (i == 0){
            throw new MyException("添加资费失败");
        }
    }

    public void delCost(Integer costId) throws MyException {
        int i = costMapper.removeCost(costId);
        if (i==0){
            throw new MyException("删除资费失败");
        }
    }

    public void editCostStatus(Cost cost) throws MyException {
        int i = costMapper.updateCostStatus(cost);
        if (i==0){
            throw new MyException("修改资费状态失败");
        }
    }

}
