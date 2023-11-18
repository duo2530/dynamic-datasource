package wiki.sponge.dynamicdatasource.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import wiki.sponge.dynamicdatasource.annotation.ReadOnly;
import wiki.sponge.dynamicdatasource.domain.Order;
import wiki.sponge.dynamicdatasource.mapper.OrderMapper;
import wiki.sponge.dynamicdatasource.service.OrderService;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@ReadOnly
	@Override
	public List<Order> getAll() {
		return orderMapper.selectAll();
	}

	@Override
	public void create(Order order) {
		 orderMapper.insert(order);
	}

}
