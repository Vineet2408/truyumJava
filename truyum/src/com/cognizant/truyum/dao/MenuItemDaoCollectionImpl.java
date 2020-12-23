package com.cognizant.truyum.dao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;
public class MenuItemDaoCollectionImpl implements MenuItemDao
{

private static List<MenuItem>menuItemList;
	
public MenuItemDaoCollectionImpl() {
	if(MenuItemDaoCollectionImpl.menuItemList == null)
	{
		List<MenuItem> list=new ArrayList<>();
		list.add(new MenuItem(1,"Sandwich", (float) 99.0, true, DateUtil.convertToDate("15/07/2017"), "Main Course", true));
		list.add(new MenuItem(2,"Burger", (float) 129.0, true, DateUtil.convertToDate("23/12/2017"), "Main Course", false));
		list.add(new MenuItem(3,"Pizza", (float) 149.0, true, DateUtil.convertToDate("21/08/2018"), "Main Course", false));
		list.add(new MenuItem(4,"French Fries", (float) 57.0, false, DateUtil.convertToDate("02/07/2017"), "Starters", true));
		list.add(new MenuItem(5,"Chocolate Brownie", (float) 32.0, true, DateUtil.convertToDate("02/11/2022"), "Desert", true));
		setMenuItemList(list);
	}
	
}
	
	public void setMenuItemList(List<MenuItem> menuItemList) {
		MenuItemDaoCollectionImpl.menuItemList = menuItemList;
}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		// TODO Auto-generated method stub
		return MenuItemDaoCollectionImpl.menuItemList ;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		// TODO Auto-generated method stub
		List<MenuItem> menuItemListCustomer=new ArrayList<>();
		Date date =new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		String ds=simpleDateFormat.format(date);
		Date ds1=DateUtil.convertToDate(ds);
		
		List<MenuItem> list=MenuItemDaoCollectionImpl.menuItemList;
		for(MenuItem m:list)
		{
			Date d1=m.getDateOfLaunch();
			if((d1.before(ds1)||d1.equals(ds1))&& m.isActive())
			{
				menuItemListCustomer.add(m);
			}
		}
		return menuItemListCustomer;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) 
	{
		// TODO Auto-generated method stub
		List<MenuItem> list=MenuItemDaoCollectionImpl.menuItemList;
		for(MenuItem m:list)
		{
			if(m.getId()==menuItem.getId())
			{
				m.setName(menuItem.getName());
				m.setPrice(menuItem.getPrice());
				m.setCategory(menuItem.getCategory());
				m.setDateOfLaunch(menuItem.getDateOfLaunch());
				m.setFreeDelivery(menuItem.isFreeDelivery());
				m.setActive(menuItem.isActive());
			}
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) 
	{
		// TODO Auto-generated method stub
		List<MenuItem> list=MenuItemDaoCollectionImpl.menuItemList;
		for(MenuItem m:list)
		{
			if(m.getId()==menuItemId)
			{
				return m;
			}
		}
		return null;
		
	}
}
