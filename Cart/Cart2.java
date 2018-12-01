package com.jzfblog.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * ʹ��map�洢
 * @author �����
 *
 */
public class Cart2 {
	
	// �ܼ�/����
	private double total;
	// ������ȷ���Ĺ�����
	Map<String, CartItem> map = new HashMap<String, CartItem>();
	
	// ��ӹ�������ﳵ��
	public void addCartItemToCart(CartItem cartItem) {
		// ��ȡ�������ﳵ��ӵ���Ʒpid
		String pid = cartItem.getProduct().getPid();
		if(map.containsKey(pid)) {
			CartItem oldItem = map.get(pid);
			oldItem.setNum(oldItem.getNum() + cartItem.getNum());
		}else {
			map.put(pid, cartItem);
		}
	}
	
	// �Ƴ�������
	public void removeCartItem(String pid) {
		map.remove(pid);
	}
	// ��չ��ﳵ
	public void clearCart() {
		map.clear();
	}

	// �����ܼ�
	public double getTotal() {
		// Ҫע�����ÿ�λ�ȡtotalֵʱ����Ҫ�ȸ�ֵΪ0
		total = 0;
		// ��ȡ��Map�����еĹ�����
		Collection<CartItem> values = map.values();
		for (CartItem cartItem : values) {
			total += cartItem.getSubTotal();
		}
		return total;
	}
	
	// ����map�����е�ֵ
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	public void setTotal(double total) {
		
	}

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
}
