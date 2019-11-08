package com.gmail.michzuerch.teachersassistant.ui.views.admin.products;

import static com.gmail.michzuerch.teachersassistant.ui.dataproviders.DataProviderUtil.convertIfNotNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import com.gmail.michzuerch.teachersassistant.ui.dataproviders.DataProviderUtil;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import com.gmail.michzuerch.teachersassistant.ui.utils.FormattingUtils;

class PriceConverter implements Converter<String, Integer> {

	private static final long serialVersionUID = 1L;
	private final DecimalFormat df = FormattingUtils.getUiPriceFormatter();

	@Override
	public Result<Integer> convertToModel(String presentationValue, ValueContext valueContext) {
		try {
			return Result.ok((int) Math.round(df.parse(presentationValue).doubleValue() * 100));
		} catch (ParseException e) {
			return Result.error("Invalid value");
		}
	}

	@Override
	public String convertToPresentation(Integer modelValue, ValueContext valueContext) {
		return DataProviderUtil.convertIfNotNull(modelValue, i -> df.format(BigDecimal.valueOf(i, 2)), () -> "");
	}
}