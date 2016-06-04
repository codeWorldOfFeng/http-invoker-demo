/**
 * 
 */
package com.feng.learn;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.feng.learn.client.CrossDomainService;
import com.feng.learn.http.invoker.api.PersonService;
import com.feng.learn.http.invoker.api.UserService;
import com.feng.learn.http.invoker.api.model.Children;
import com.feng.learn.http.invoker.api.model.Person;

/**
 * @author feng_Pc
 *
 */
public class HttpInvokerTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Ignore
    public void test() {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-http-invoker.xml");
	PersonService service = (PersonService) context.getBean("personService");
	Person p = new Person();
	p.setName("person1");
	p.setAge(99);
	p.setChildrens(Arrays.asList(new Children("c1"), new Children("c2")));
	int age = service.getUserAge(p);
	Person result = service.loadUserByUsername("zhangzhanfeng");
	System.out.println(result);

	UserService uService = (UserService) context.getBean("userService");
	p = uService.getPersonByName("joijjlkjlk");
	System.out.println(p);
    }

    @Test
    public void testServiceFactory() {
	String url = "http://127.0.0.1:8080/jetty/cudService/userService";
	ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-http-invoker.xml");
	CrossDomainService service = context.getBean("crossDomainService", CrossDomainService.class);
	System.out.println(service.getServices());
	UserService userService = service.getService(url, UserService.class);
	Person p = userService.getPersonByName("zhangzhanf");
	System.out.println(p);

	userService = service.getService(UserService.class, "127.0.0.1:8080");

	p = userService.getPersonByName("zhangzhanf");
	System.out.println(p);
    }

}
