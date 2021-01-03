package com.Dao;

import com.bean.Bill;
import com.utils.JDBCUtil;

public class BillDao {

    public boolean saveBill(Bill bill) {
        String sql = "insert into t_bill(bill_email,bill_money,bill_detailed,bill_flag,bill_time,bill_week)"+ "values(?,?,?,?,?,?)";
        return JDBCUtil.save(sql,bill.getEmail(),bill.getMoney(),bill.getDetailed(),bill.getFlag(),bill.getTime(),bill.getWeek());
    }
}
