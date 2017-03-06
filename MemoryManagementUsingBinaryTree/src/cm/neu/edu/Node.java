package cm.neu.edu;

import java.util.Scanner;

/**
 * Created by shrut on 2/14/2017.
 */
public class Node {

    Node left;
    Node right;
    int key;
    boolean statusFree;

    public Node(int key)
    {
        this.key=key;
        this.left=null;
        this.right=null;
        statusFree=true;
    }

    public static void main(String args[])
    {
        System.out.println("Please enter a number for creating the Binary tree.");
        Scanner sc=new Scanner(System.in);
        int number=sc.nextInt();

        if(number<=0){
            System.out.print("Please enter a valid number>0.");
            return;
        }

        //NodeManagement nm=new NodeManagement();
        RequestFragment rf=new RequestFragment();

        for(int i=number;i>=0;i--)
        {
            rf.insert((int)Math.pow(2,i));
        }

        System.out.println("Original Memory available");
        rf.print();

        System.out.println("");
        System.out.println("**Requesting random memory block:**");
        System.out.println();



        RequestFragment.requestCall=1;



        for(int i=5;i>0;i--){
            int random = (rf.randInt(0, 50));
            System.out.println("\nProcessing request for :"+ random);
            rf.totalMemory = 0;
            rf.availableMemory=0;
            int result[] = new int[2];
            result= rf.visitNodeForTotal(rf.root);
            rf.totalMemory = result[1]+rf.visitNodeForOne(rf.root);
            rf.availableMemory = result[0]+rf.visitNodeForOne(rf.root);
            System.out.println("Available Memory for allocation :"+ rf.availableMemory);
            rf.numberOfFreeOne = 0;
            rf.requestMemory(random);
            RequestFragment.requestCall=2;
            System.out.println();
            rf.print();
        }


        System.out.println("");
        System.out.println("Number of Failed Requests: "+rf.failedRequest);
        System.out.println();

}


}
