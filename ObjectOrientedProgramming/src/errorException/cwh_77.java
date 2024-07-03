package errorException;

public class cwh_77 {
    public static void main(String[] args) {


        int a=6000;
        int b=6;

        try{
            int c=a/b;
            System.out.println("The result is " + c);
        }catch (Exception e)
        {
            System.out.println("We failed to divide Reason: ");
            System.out.println(e);
        }




    }
}
