
class MyMainEmployee
{
    private int id;
    private String name;
//public MyMainEmployee(){
//    id=45;
//    name="Your-name-here";
//}
//public MyMainEmployee(String Myname){
//    id=45;
//    name=Myname;
//}
public MyMainEmployee(String Myname,int id1){
    id=id1;
    name=Myname;
}
    public String getName() {return name;}
    public void setName(String n){this.name=n;}
    public int getId(){return id;}
    public void setId(int i){this.id=i;}
}
public class Constructors {

    public static void main(String[] args) {
        MyMainEmployee Ash = new MyMainEmployee("Ashish",55);
        System.out.println(Ash.getId());
        System.out.println(Ash.getName());

    }
}
