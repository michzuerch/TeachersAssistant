package com.gmail.michzuerch.teachersassistant.ui.views.storefront.events;

import com.gmail.michzuerch.teachersassistant.ui.views.orderedit.OrderItemEditor;
import com.vaadin.flow.component.ComponentEvent;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.Product;

public class ProductChangeEvent extends ComponentEvent<OrderItemEditor> {

	private static final long serialVersionUID = 1L;
	private final Product product;

	public ProductChangeEvent(OrderItemEditor component, Product product) {
		super(component, false);
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

}