package com.feng.learn.http.invoker;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feng.learn.http.invoker.api.PersonService;
import com.feng.learn.http.invoker.api.model.Children;
import com.feng.learn.http.invoker.api.model.Person;

public class PersonServiceImpl implements PersonService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Person loadUserByUsername(String name) {
	logger.debug("personServiceImpl loadUserByUsername : {}", name);
	Person p = new Person();
	p.setName("feng");
	p.setAge(26);
	p.setChildrens(Arrays.asList(new Children("childrenOne"), new Children("childrenTwo")));
	return p;
    }

    @Override
    public int getUserAge(Person user) {
	logger.debug("personServiceImpl getUserAge: {}", user);
	return 26;
    }

}
