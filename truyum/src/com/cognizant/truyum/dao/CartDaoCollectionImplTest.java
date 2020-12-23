package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImplTest {

	public static void main(String[] args) 
	{
		testAddCartItem();
		testGetAllCartItems();
		testRemoveCartItem();	
	}
	
	public static void testAddCartItem()
	{
		CartDao cartDao=new CartDaoCollectionImpl();
		cartDao.addCartItem(1L,3L);
		cartDao.addCartItem(1L,2L);
		cartDao.addCartItem(1L,4L);
		cartDao.addCartItem(1L,1L);
		List<MenuItem> list=null;
		try {
			list=cartDao.getAllCartItems(1);
			if(list!=null)
			{
				System.out.println("Id \t Name \t Price \t Active \t Category \t Date Of Launch \t Free Delivery ");
				for(MenuItem m:list)
				{
					System.out.println(m.getId()+"\t"+m.getName()+"\t"+m.getPrice()+
							"\t"+m.isActive()+"\t"
				+m.getCategory()+"\t"+m.getDateOfLaunch()+"\t"+m.isFreeDelivery());
					if(m.getId()==3L)
					{
						System.out.println("MenuItem found");
					}
				}
			}
			
		} 
		catch (CartEmptyException e)
		{
			// TODO Auto-generated catch block
			System.out.println(e.getMsg());
		}
	}
	public static void testGetAllCartItems()
	{
		CartDao cartDao=new CartDaoCollectionImpl();
		List<MenuItem> list=null;
		try {
			list=cartDao.getAllCartItems(1);
			System.out.println("Id \t Name \t Price \t Active \t Category \t Date Of Launch \t Free Delivery ");
			for(MenuItem m:list)
			{
				System.out.println(m.getId()+"\t"+m.getName()+"\t"+m.getPrice()+
						"\t"+m.isActive()+"\t"
			+m.getCategory()+"\t"+m.getDateOfLaunch()+"\t"+m.isFreeDelivery());
			}
		} catch (CartEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void testRemoveCartItem()
	{
		CartDao cartDao=new CartDaoCollectionImpl();
		List<MenuItem> list;
		try {
			list = cartDao.getAllCartItems(1L);
			System.out.println("Id \t Name \t Price \t Active \t Category \t Date Of Launch \t Free Delivery ");
			for(MenuItem m:list)
			{
				System.out.println(m.getId()+"\t"+m.getName()+"\t"+m.getPrice()+
						"\t"+m.isActive()+"\t"
			+m.getCategory()+"\t"+m.getDateOfLaunch()+"\t"+m.isFreeDelivery());
			}
		} catch (CartEmptyException e1) {
			System.out.println("Cart is Empty");
					}
		
		
		cartDao.removeCartItem(1, 1);
			System.out.println("After removing item");
		try {
			list = cartDao.getAllCartItems(1);
			
			System.out.println("Id \t Name \t Price \t Active \t Category \t Date Of Launch \t Free Delivery ");
			for(MenuItem m:list)
			{
				System.out.println(m.getId()+"\t"+m.getName()+"\t"+m.getPrice()+
						"\t"+m.isActive()+"\t"
			+m.getCategory()+"\t"+m.getDateOfLaunch()+"\t"+m.isFreeDelivery());
			}
			
		} catch (CartEmptyException e) {
			e.printStackTrace();
		}
		
	}

}
