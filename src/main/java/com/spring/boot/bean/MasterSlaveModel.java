package com.spring.boot.bean;

import com.spring.boot.bean.table.SingleTableInfo;
import com.spring.boot.bean.table.SlaveTableInfo;
import com.spring.boot.bean.table.TableInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuderen
 * @version 2017/10/13 14:53
 */
public class MasterSlaveModel {

    private SingleTableInfo master;
    private List<SlaveTableInfo> slave = new ArrayList();

    public SingleTableInfo getMaster() {
        return master;
    }

    public void setMaster(SingleTableInfo master) {
        this.master = master;
    }

    public List<SlaveTableInfo> getSlave() {
        return slave;
    }

    public void setSlave(List<SlaveTableInfo> slave) {
        this.slave = slave;
    }
}
