package com.feng.learn.http.invoker.api;

import com.feng.learn.http.invoker.api.model.Person;

public interface PersonService {
    Person loadUserByUsername(String name);

    int getUserAge(Person user);
}
