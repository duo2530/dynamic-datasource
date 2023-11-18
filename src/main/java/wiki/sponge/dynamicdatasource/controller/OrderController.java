package wiki.sponge.dynamicdatasource.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wiki.sponge.dynamicdatasource.domain.Order;
import wiki.sponge.dynamicdatasource.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/all")
	public List<Order> getOrder() {
		return orderService.getAll();
	}
	
	@PostMapping("")
	public String create(@RequestBody Order order) {
		orderService.create(order);
		
		return "success";
	}

}
