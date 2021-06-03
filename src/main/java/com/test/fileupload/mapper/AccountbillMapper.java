package com.test.fileupload.mapper;

import com.test.fileupload.entity.Accountbill;
import org.apache.ibatis.annotations.Property;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

public interface AccountbillMapper {

    @Results(id = "acc",value = {
            @Result(property = "accSeq" , column = "acc_seq"),
            @Result(property = "macId" ,column = "mac_id"),
            @Result(property = "trafficFee",column = "traffic_fee"),
            @Result(property = "lineName",column = "line_name"),
            @Result(property = "unitName",column = "unit_name"),
            @Result(property = "carId",column = "car_id"),
            @Result(property = "fullNum",column = "full_num"),
            @Result(property = "halfNum",column = "half_num"),
            @Result(property = "freeNum",column = "free_num"),
            @Result(property = "seatNum",column = "seat_num"),
            @Result(property = "checkNum",column = "check_num"),
            @Result(property = "stationFee",column = "station_fee"),
            @Result(property = "manageFee",column = "manage_fee"),
    })
    @Select("select * from accountbill limit 50")
    public List<Accountbill> queryAccountbillAll();

    default void he(){
        List<String> column = new ArrayList<>();
        column.add("主键");column.add("机构代码"); column.add("运输费");column.add("线路名"); column.add("机构名"); column.add("车牌号"); column.add("全票数");column.add("半票");
        column.add("免票数");column.add("席位数"); column.add("检票数");column.add("站务费"); column.add("管理费");









        }
}
