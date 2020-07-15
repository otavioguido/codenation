package br.com.codenation.service;

import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {
		return items.stream()
				.map(orderItem -> {
						Product product = findById(orderItem.getProductId());

						if (product != null){
							if (product.getIsSale())
								return fullPrice(product, orderItem)*0.8;
							return fullPrice(product, orderItem);
						}
						return 0.0;
					})
				.reduce(0.0, Double::sum);
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		return ids.stream()
				.map(id -> findById(id))
				.filter(product -> product != null)
				.collect(Collectors.toSet());
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return orders.stream()
				.map(orderItems -> calculateOrderValue(orderItems))
				.reduce(0.0, Double::sum);
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		return findProductsById(productIds).stream().collect(Collectors.groupingBy(Product::getIsSale));
	}

	private Product findById(Long id){
		return productRepository.findById(id).orElse(null);
	}

	private Double fullPrice(Product product, OrderItem orderItem){
		return product.getValue()*orderItem.getQuantity();
	}

}