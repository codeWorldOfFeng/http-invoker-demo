package com.feng.learn.http.invoker.api.model;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6261969332021602637L;

    private String name;
    private int age;
    private List<Children> childrens;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getAge() {
	return age;
    }

    public void setAge(int age) {
	this.age = age;
    }

    public List<Children> getChildrens() {
	return childrens;
    }

    public void setChildrens(List<Children> childrens) {
	this.childrens = childrens;
    }

    @Override
    public String toString() {
	return "Person [name=" + name + ", age=" + age + ", childrens=" + childrens + "]";
    }

}
