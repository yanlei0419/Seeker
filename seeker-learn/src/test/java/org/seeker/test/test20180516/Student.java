package org.seeker.test.test20180516;

import java.util.HashSet;
import java.util.Set;

// Java Bean : Student
public class Student {

    private College College;  

    private String name;  
    private String gentle;  
    private double height;
    private int age; 

    // 无参构造器
    public Student() {  
    }  

    public Student(String name, int age, double height) {
        super();
        this.name = name;
        this.height = height;
        this.age = age;
    }

    //getter & setter
    public College getCollege() {  
        return College;  
    }  

    public void setCollege(College College) {  
        this.College = College;  
    }  

    public String getName() {  
        return name;  
    }  

    public void setName(String name) {  
        this.name = name;  
    }

    public String getGentle() {
        return gentle;
    }

    public void setGentle(String gentle) {
        this.gentle = gentle;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", height=" + height + ", age=" + age
                + "]";
    } 
}



// Java Bean : College
class College {

    private String name;  

    private Set<Student> Students = new HashSet<Student>();  

    // 无参构造器
    public College() {  
    }  

    //getter & setter
    public String getName() {  
        return name;  
    }  

    public void setName(String name) {  
        this.name = name;  
    }  

    public Set<Student> getStudents() {  
        return Students;  
    }  

    public void setStudents(Set<Student> Students) {  
        this.Students = Students;  
    }  
}