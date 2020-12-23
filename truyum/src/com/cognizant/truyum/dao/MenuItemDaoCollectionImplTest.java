package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImplTest
{
	public static void main(String[] args)
	{
		testGetMenuItemListAdmin();
		testGetMenuItemListCustomer();
		testModifyMenuItem();
		testGetMenuItem();
	}
	
	
	public static void testGetMenuItemListAdmin()
	{
		MenuItemDao menuItemDao=(MenuItemDao) new MenuItemDaoCollectionImpl();
		List<MenuItem> menuItemList=menuItemDao.getMenuItemListAdmin();
		System.out.println("Id \t Name \t Price \t Active \t Category \t Date Of Launch \t Free Delivery ");
		for(MenuItem m:menuItemList)
		{
			System.out.println(m.getId()+"\t"+m.getName()+"\t"+m.getPrice()+"\t"+m.isActive()+"\t"
		+m.getCategory()+"\t"+m.getDateOfLaunch()+"\t"+m.isFreeDelivery());
		}
	}
	
	public static void testGetMenuItemListCustomer()
	{
		MenuItemDao menuItemDao=new MenuItemDaoCollectionImpl();
		List<MenuItem> list=menuItemDao.getMenuItemListCustomer();
		System.out.println("Id \t Name \t Price \t Category \t Date Of Launch \\t Free Delivery ");
		
		for(MenuItem menuItem: list)
		{
			System.out.println(menuItem.getId()+" "+menuItem.getName()+" "+menuItem.getPrice()+" "+menuItem.getCategory()+" "+menuItem.getDateOfLaunch());
		}
		
	}
	public static void testModifyMenuItem()
	{
		MenuItem menuItem=new MenuItem(1,"Sandwich", (float) 199.0, true, DateUtil.convertToDate("16/07/2018"), "Main Course", true);
		MenuItemDao menuItemDao=new MenuItemDaoCollectionImpl();
		menuItemDao.modifyMenuItem(menuItem);
		List<MenuItem> list=menuItemDao.getMenuItemListCustomer();
		System.out.println();
		System.out.println("Data after modification");
		System.out.println();
		for(MenuItem menuItems: list)
		{
			System.out.println(menuItems.getId()+" "+menuItems.getName()+" "+menuItems.getPrice()+" "+menuItems.getCategory()
			+" "+menuItems.getDateOfLaunch());
		}
		
	}
	public static void testGetMenuItem()
	{
		MenuItemDao menuItemDao=new MenuItemDaoCollectionImpl();
		MenuItem menuItems=menuItemDao.getMenuItem(1);
		System.out.println(menuItems.getId()+" "+menuItems.getName()+" "+menuItems.getPrice()+" "+menuItems.getCategory()
		+" "+menuItems.getDateOfLaunch());
	}

}
