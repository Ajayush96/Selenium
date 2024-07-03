package inharitance;

interface MyCamera2{
    void takeSnap();
    void recordVideo();
    default void record4kvideo(){
        System.out.println("Recording in 4k");
    }

}
interface Mywifi2{
    String[] getNetwork();
    void connectToNetwork(String Network);

}
class MyCellPhone2{
    void callNumber(int phoneNumber)
    {
        System.out.println("Calling "+ phoneNumber);
    }
    void pickCall()
    {
        System.out.println("Connecting...");
    }
    void takeSnap(){
        System.out.println("Taking snap");
    }

}

class MySmartPhone2 extends MyCellPhone2 implements Mywifi2,MyCamera2{

    @Override
    public void takeSnap() {
        System.out.println("Taking snap");
    }

    @Override
    public void recordVideo() {
        System.out.println("Taking snap");
    }

    @Override
    public String[] getNetwork() {
        System.out.println("Getting List of Network");
        String[] networkList = {"Harry","Prashanth","Anjali5G"};
        return networkList;
    }

    @Override
    public void connectToNetwork(String Network) {

    }
    public void record4kvideo(){
        System.out.println("Recording in 4k and taking snap......");
    }
}
public class cwh_59_polymorphsm {
    public static void main(String[] args) {

        MyCamera2 mc = new MySmartPhone2();
        //mc.getNetwork(); not allowed
        mc.record4kvideo();

            }
        }


