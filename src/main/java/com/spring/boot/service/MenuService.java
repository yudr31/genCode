package com.spring.boot.service;

import com.spring.boot.bean.Menu;
import com.spring.boot.bean.Page;
import com.spring.boot.mapper.MenuDao;
import com.spring.boot.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-17 14:05
 */
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuDao menuDao;

//    public PageInfo<Menu> getMenuList(Page page, Menu menu){
//        PageHelper.startPage(page.getPageNo(),page.getPageSize());
//        List<Menu> menuList = menuDao.findMenuList(menu);
//        return new PageInfo(menuList);
//    }

//    public Menu getMenu(Menu menu){
//        return menuRepository.findOne(menu.getId());
//    }
//
//    public boolean createMenu(Menu menu){
//        Menu res = menuRepository.save(menu);
//        if (null != res)
//            return true;
//        return false;
//    }

}
