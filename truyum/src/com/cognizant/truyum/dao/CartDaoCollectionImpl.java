package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class CartDaoCollectionImpl implements CartDao {

	private static HashMap<Long, Cart>userCarts;
	
	public CartDaoCollectionImpl() {
		if(CartDaoCollectionImpl.userCarts==null)
		{
			HashMap<Long, Cart> cart=new HashMap<>();
			List<MenuItem> list=new ArrayList<>();
			list.add(new MenuItem(1,"Sandwich", (float) 99.0, true, DateUtil.convertToDate("15/07/2017"), "Main Course", true));
			list.add(new MenuItem(2,"Burger", (float) 129.0, true, DateUtil.convertToDate("23/12/2017"), "Main Course", false));
			double total=0.0;
			for(MenuItem m:list)
			{
				total+=m.getPrice();
			}
			Cart ob=new Cart(list,total);
			cart.put(123L,ob);
			total=0.0;
			ob=null;
			list=new ArrayList<>();
			list.add(new MenuItem(3,"Pizza", (float) 149.0, true, DateUtil.convertToDate("21/08/2018"), "Main Course", false));
			list.add(new MenuItem(4,"French Fries", (float) 57.0, false, DateUtil.convertToDate("02/07/2017"), "Starters", true));
			list.add(new MenuItem(5,"Chocolate Brownie", (float) 32.0, true, DateUtil.convertToDate("02/11/2022"), "Desert", true));
			for(MenuItem m:list)
			{
				total+=m.getPrice();
			}
			ob=new Cart(list,total);
			cart.put(456L,ob);
			
			setUserCarts(cart);
		}
	}
	public HashMap<Long,Cart> getUserCarts() {
		return CartDaoCollectionImpl.userCarts;
	}

	public  void setUserCarts(HashMap<Long, Cart> userCarts) {
		CartDaoCollectionImpl.userCarts = userCarts;
	}


	@SuppressWarnings("null")
	@Override
	public void addCartItem(long userId, long menuItemId) {
		Cart cart=CartDaoCollectionImpl.userCarts.get(userId);
		MenuItemDao menuItemDao=new MenuItemDaoCollectionImpl();
		
		if(cart==null)
		{	
			List<MenuItem> list=new ArrayList<>();
					list.add(menuItemDao.getMenuItem(menuItemId));
			double total=menuItemDao.getMenuItem(menuItemId).getPrice();
			cart=new Cart(list,total);
			
			CartDaoCollectionImpl.userCarts.put(userId,cart);
		}
		else
		{
			List<MenuItem> list=cart.getMenuItemList();
			list.add(menuItemDao.getMenuItem(menuItemId));
			double total=cart.getTotal()+menuItemDao.getMenuItem(menuItemId).getPrice();
			cart.setTotal(total);
			cart.setMenuItemList(list);
			CartDaoCollectionImpl.userCarts.put(userId, cart);
		}

	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		
		if(CartDaoCollectionImpl.userCarts.isEmpty())
		{
			throw new CartEmptyException("Empty List found exception");
		}
		return CartDaoCollectionImpl.userCarts.get(userId).getMenuItemList();
	}

	@Override
	public void removeCartItem(long userId, long menuItemId)
	{
		Cart cart=CartDaoCollectionImpl.userCarts.get(userId);
		if(cart== null)
		{
			try {
				throw new CartEmptyException("No user found");
			} catch (CartEmptyException e) {
				System.out.println(e.toString());
			}
		}
		List<MenuItem> list=cart.getMenuItemList();
		int i=0;
		for(MenuItem m:list)
		{
			if(m.getId()== menuItemId)
			{
				double total=cart.getTotal()-(m.getPrice());
				cart.setTotal(total);
				list.remove(i);
				break;
			}
			i++;
		}
		cart.setMenuItemList(list);
		CartDaoCollectionImpl.userCarts.put(userId, cart);	
		
	}

}
