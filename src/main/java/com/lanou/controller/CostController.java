package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.service.CostService;
import com.lanou.utils.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/8/30.
 */
@Controller
@RequestMapping("/cost")
public class CostController {
    @Resource
    private CostService costService;

    // 资费页面的跳转
    @RequestMapping("/fee_listPage")
    public String feeListPage(){
        return "fee/fee_list";
    }

    // 修改资费页面的跳转
    @RequestMapping(value = "/fee_modiPage")
    public String feeModiPage(@RequestParam("costId") Integer costId,
                              HttpSession session){
        session.setAttribute("costId",costId);
        return "fee/fee_modi";
    }
    // 资费详情页面的跳转
    @RequestMapping("/fee_detail")
    public String feeDetailPage(@RequestParam("costId") Integer costId,
                                HttpSession session){
        session.setAttribute("costId",costId);
        System.out.println(costId);
        return "/cost/fee_detail";
    }

    // 添加资费页面的跳转
    @RequestMapping("/fee_addPage")
    public String feeAddPage(){
        return "fee/fee_add";
    }

    // 查找所有资费信息(分页)
    @RequestMapping("/getAllCost")
    @ResponseBody
    public Map getAllCost(@RequestParam("pageNum") Integer pageNum,
                          @RequestParam("pageSize") Integer pageSize){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            PageInfo<Cost> allCost = costService.getAllCost(pageNum, pageSize);
            map.put("msg","success");
            map.put("costList",allCost);
        } catch (MyException e) {
            map.put("msg",e.getMessage());
        }
        return map;
    }
    // 根据costId查找cost对象
    @RequestMapping("/getCostByCostId")
    @ResponseBody
    public Map getCostByCostId(HttpSession session){
        Integer costId = (Integer) session.getAttribute("costId");
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            Cost costById = costService.getCostByCostId(costId);
            map.put("msg","success");
            map.put("cost",costById);
        } catch (MyException e) {
            map.put("msg","error");
        }
        return map;
    }

    // 修改cost
    @RequestMapping("/editCost")
    @ResponseBody
    public Map editCost(HttpSession session,Cost cost){
        System.out.println(cost);
        Integer costId = (Integer) session.getAttribute("costId");
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            costService.editCost(cost);
            map.put("msg","success");
        } catch (MyException e) {
            map.put("msg","error");
        }
        return map;
    }
    // 检查资费名是否重复
    @RequestMapping("/checkCostName")
    @ResponseBody
    public Map checkCostName(@RequestParam("name") String name){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            costService.getCostIdByName(name);
            map.put("msg","success");
        } catch (MyException e) {
            map.put("msg","error");
        }
        return map;
    }
    // 添加资费
    @RequestMapping("/addCost")
    @ResponseBody
    public Map addCost(Cost cost){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            cost.setStatus("0");
            cost.setCreatime(new Date());
            costService.addCost(cost);
            System.out.println(cost);
            map.put("msg","success");
        } catch (MyException e) {
            map.put("msg","error");
        }
        return map;
    }
    // 删除资费
    @RequestMapping("/delCost")
    @ResponseBody
    public Map delCost(@RequestParam("costId") Integer costId){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            costService.delCost(costId);
            map.put("msg","success");
        } catch (MyException e) {
            map.put("msg","error");
        }
        return map;
    }
    // 修改资费状态
    @RequestMapping("/editCostStatus")
    @ResponseBody
    public Map editCostStatus(Cost cost){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            Date startime = new Date();
            cost.setStartime(startime);
            costService.editCostStatus(cost);
            System.out.println(startime);
            map.put("msg","success");
            map.put("date",startime);
        } catch (MyException e) {
            map.put("msg","error");
        }
        return map;
    }


}
