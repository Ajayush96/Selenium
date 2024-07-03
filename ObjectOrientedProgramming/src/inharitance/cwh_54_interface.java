package inharitance;


interface Bicycle{
    int a = 45;
    void applyBreak(int Decrement);
    void speedUp(int Increment);
}
interface HornBicycle{
    void BlowHornK3g();
    void BlowHornmhn();
}

class AvonCycle implements Bicycle,HornBicycle{

    @Override
    public void applyBreak(int Decrement) {
        System.out.println("Applying Break");
    }

    @Override
    public void speedUp(int Increment) {
        System.out.println("Applying speedup");
    }
    void blowHorn()
    {
        System.out.println("pee pee pee poo poo");
    }

    @Override
    public void BlowHornK3g() {
        System.out.println("kabhi khusi kabhi gum pee pee pee");
    }

    @Override
    public void BlowHornmhn() {
        System.out.println("Mai hu na poo poo poo");
    }
}
public class cwh_54_interface {
    public static void main(String[] args) {
        AvonCycle c = new AvonCycle();
        c.applyBreak(1);
        System.out.println(c.a);
        c.BlowHornK3g();
        c.BlowHornmhn();
    }
}
