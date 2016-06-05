package com.feng.learn.http.invoker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.feng.learn.http.invoker.api.UserService;
import com.feng.learn.http.invoker.api.model.Person;

@Service("userService")
public class UserServiceImpl extends CrossDomainService implements UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Person getPersonByName(String name) {
		logger.debug("userServiceImpl.getPersonByName({})", name);
		Person p = new Person();
		p.setName("userServiceImpl");
		return p;
	}

}
