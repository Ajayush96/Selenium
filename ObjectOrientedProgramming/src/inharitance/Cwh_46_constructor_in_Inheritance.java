package inharitance;


class Base1{

    Base1()
    {
        System.out.println("I'm a constructor");
    }
    Base1(int x)
    {
        System.out.println("I'm a overloaded constructor value of a as :" + x);
    }
    int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
class Derived1 extends Base1{
    Derived1(){
        //super(0);
        System.out.println("I'm a derived class constructor");
    }
    Derived1(int x,int y){
        super(4);
        System.out.println("I'm a overloaded constructor of derived with value of b as :" +y);
    }
    int y;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
class ChildofDerived extends Derived1{
    ChildofDerived(){
        System.out.println("i'm a child of derived constructor");
    }
    ChildofDerived(int x,int y,int z){
        super(x,y);
        System.out.println("I'm a overloaded constructor of child of derived with value of z as :" +z);
    }
}
public class Cwh_46_constructor_in_Inheritance {
    public static void main(String[] args) {

       // Base1 b = new Base1();
       // Derived1 d=new Derived1(4,5);
        ChildofDerived cd = new ChildofDerived(4,5,6);

    }
}
