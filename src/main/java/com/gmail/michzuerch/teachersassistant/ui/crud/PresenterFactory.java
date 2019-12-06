/**
 *
 */
package com.gmail.michzuerch.teachersassistant.ui.crud;

import com.gmail.michzuerch.teachersassistant.app.security.CurrentUser;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.Order;
import com.gmail.michzuerch.teachersassistant.backend.service.OrderService;
import com.gmail.michzuerch.teachersassistant.ui.views.storefront.StorefrontView;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class PresenterFactory {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public EntityPresenter<Order, StorefrontView> orderEntityPresenter(OrderService crudService, CurrentUser currentUser) {
        return new EntityPresenter<>(crudService, currentUser);
    }

}
