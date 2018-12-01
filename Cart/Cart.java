package com.jzfblog.store.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ﳵ��ȡ��
 * @author �����
 *
 */
public class Cart {
	// �ܼ�/����
	private double total;
	// ������ȷ���Ĺ�����
	private List<CartItem> list = new ArrayList<CartItem>();
	
	// ��ӹ�������ﳵ
	public void addCartItemToCart(CartItem cartItem) {
		
		// ���ñ�����Ĭ��Ϊfalse��û���������Ʒ
		boolean flag = false;
		
		// �洢ԭ�ȵĹ�����
		CartItem old = null;
		
		// �ڽ���ǰ��������빺�ﳵ֮ǰ��������Ҫ�ж�֮ǰ�Ƿ����������Ʒ
		for(CartItem cartItem2: list) {
			if(cartItem.equals(cartItem2.getProduct().getPid())){
				flag = true;
				old = cartItem2;
			}
		}
		if(flag == false) {
			// û�������ֱ��list.add(CartItem cartItem)
			list.add(cartItem);
		}else {
			// �������ȡԭ���������ͱ������������֮��s���õ�ԭ��������
			old.setNum(old.getNum() + cartItem.getNum());
		}
	}
	// �Ƴ�������
	public void removeCartItem(String pid) {
		for (CartItem cartItem : list) {
			// �жϹ��������Ƿ�����Ŀ��pid�����ͬ
			if(pid.equals(cartItem.getProduct().getPid())) {
				// ɾ����ǰcartItem
				// ֱ�ӵ���list.remove(cartItem)�޷�ɾ��cartItem����Ҫʹ�õ�����ɾ��
			}
		}
	}
	// ��չ��ﳵ
	public void clearCart() {
		list.clear();
	}
}
