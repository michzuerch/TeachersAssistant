package com.gmail.michzuerch.teachersassistant.ui.utils.converters;

import com.gmail.michzuerch.teachersassistant.backend.data.OrderState;
import com.gmail.michzuerch.teachersassistant.ui.dataproviders.DataProviderUtil;
import com.vaadin.flow.templatemodel.ModelEncoder;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderStateConverter implements ModelEncoder<OrderState, String> {

    private static final long serialVersionUID = 1L;
    private Map<String, OrderState> values;

    public OrderStateConverter() {
        values = Arrays.stream(OrderState.values())
                .collect(Collectors.toMap(OrderState::toString, Function.identity()));
    }

    @Override
    public OrderState decode(String presentationValue) {
        return DataProviderUtil.convertIfNotNull(presentationValue, values::get);
    }

    @Override
    public String encode(OrderState modelValue) {
        return DataProviderUtil.convertIfNotNull(modelValue, OrderState::toString);
    }

}
