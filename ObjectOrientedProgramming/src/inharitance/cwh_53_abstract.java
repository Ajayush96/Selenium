package inharitance;


abstract class Parent2{

    public Parent2()
    {
        System.out.println("I'm Base2 constructor");
    }
    public void sayHello()
    {
        System.out.println("Hello");
    }
   abstract public void greet();
    abstract public void greet2();

}

class Child3 extends Parent2{
    @Override
    public void greet()
    {
        System.out.println("Good Morning");
    }

    @Override
    public void greet2() {
        System.out.println("Good Morning");
    }
}
public class cwh_53_abstract {
    public static void main(String[] args) {
        Child3 c = new Child3();
        c.greet();
        c.greet2();

    }
}
