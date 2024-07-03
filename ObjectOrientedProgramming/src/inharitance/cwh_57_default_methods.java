package inharitance;

interface MyCamera{
    void takeSnap();
    void recordVideo();
    default void record4kvideo(){
        System.out.println("Recording in 4k");
    }

}
interface Mywifi{
    String[] getNetwork();
    void connectToNetwork(String Network);

}
class MyCellPhone{
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

class MySmartPhone extends MyCellPhone implements Mywifi,MyCamera{

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
public class cwh_57_default_methods {
    public static void main(String[] args) {

        MySmartPhone ms = new MySmartPhone();
        ms.record4kvideo();
        String [] ar= ms.getNetwork();
        for(String item:ar)
        {
            System.out.println(item);
        }
    }
}
