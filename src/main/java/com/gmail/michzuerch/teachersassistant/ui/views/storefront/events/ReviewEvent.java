package com.gmail.michzuerch.teachersassistant.ui.views.storefront.events;

import com.gmail.michzuerch.teachersassistant.ui.views.orderedit.OrderEditor;
import com.vaadin.flow.component.ComponentEvent;

public class ReviewEvent extends ComponentEvent<OrderEditor> {

	private static final long serialVersionUID = 1L;

	public ReviewEvent(OrderEditor component) {
		super(component, false);
	}
}