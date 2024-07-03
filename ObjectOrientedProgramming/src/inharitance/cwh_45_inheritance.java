package inharitance;



class Base
{
  int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        System.out.println("I'm setting x now");
    }

    public void printMe(){
        System.out.println("I'm a constructor");
    }
}

class Derived extends Base
{
int y;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
public class cwh_45_inheritance {

    public static void main(String[] args) {

        Derived d= new Derived();
        d.setX(4);
        d.setY(5);
        System.out.println(d.getX());
        d.printMe();


    }
}
