package com.spring.boot.bean.table;

/**
 * @author yuderen
 * @version 2017/10/31 15:31
 */
public class SlaveTableInfo extends NnkTableInfo{

    private String linkItem;        // 与主表关联属性

    public String getLinkItem() {
        return linkItem;
    }

    public void setLinkItem(String linkItem) {
        this.linkItem = linkItem;
    }
}
