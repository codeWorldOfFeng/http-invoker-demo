package com.feng.learn.http.invoker.api;

import com.feng.learn.http.invoker.api.model.Person;

public interface UserService {
    Person getPersonByName(String name);
}
