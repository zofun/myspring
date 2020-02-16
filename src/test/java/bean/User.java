package bean;

import java.io.Closeable;

public class User  {
    private String name;
    private int age;

    public User(){
        //空构造器
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void init(){
        System.out.println("init方法被调用了");
    }

    public void sayHello(){
        System.out.println(name+": hello!");
    }
}
