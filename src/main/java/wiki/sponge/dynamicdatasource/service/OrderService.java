package wiki.sponge.dynamicdatasource.service;

import java.util.List;

import wiki.sponge.dynamicdatasource.domain.Order;

public interface OrderService {
	
	public List<Order> getAll();

	
	public void create(Order order);
}
