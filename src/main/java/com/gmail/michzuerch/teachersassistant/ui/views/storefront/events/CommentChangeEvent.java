package com.gmail.michzuerch.teachersassistant.ui.views.storefront.events;

import com.gmail.michzuerch.teachersassistant.ui.views.orderedit.OrderItemEditor;
import com.vaadin.flow.component.ComponentEvent;

public class CommentChangeEvent extends ComponentEvent<OrderItemEditor> {

    private static final long serialVersionUID = 1L;
    private final String comment;

    public CommentChangeEvent(OrderItemEditor component, String comment) {
        super(component, false);
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

}