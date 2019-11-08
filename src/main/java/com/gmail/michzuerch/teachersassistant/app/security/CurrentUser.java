package com.gmail.michzuerch.teachersassistant.app.security;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;

@FunctionalInterface
public interface CurrentUser {

	User getUser();
}
