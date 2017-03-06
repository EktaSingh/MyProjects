package Part1;

import java.util.ArrayList;

import static Part1.RequestFragment.randInt;

/**
 * Created by shruti on 1/27/2017.
 */
public class LinkedList {

    Node head;  // head of list 

    /* Node Class */
    class Node {

        int data;
        Node next;

        // Constructor to create a new node
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /* This function is in LinkedList class. Inserts a
   new Node at front of the list.*/
    public void push(int new_data) {
        /* 1 & 2: Allocate the Node &
                  Put in the data*/
        Node new_node = new Node(new_data);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;
    }

    public void pop(LinkedList lst) {
        Node current = lst.head;
        lst.head = current.next;

    }

    public boolean isEmpty(LinkedList lst) {
        if (lst.head == null) {
            return true;
        } else {
            return false;
        }
    }


    /* This function prints contents of linked list starting from
        the given node */
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        
        ArrayList<LinkedList> arrList = new ArrayList<>();

        int value = 0;
        for (int i = 0; i < 10; i++) {
            LinkedList llist = new LinkedList();

            for (int j = 0; j < 10; j++) {

                llist.push((int) Math.round(Math.pow(2, value)));

            }
            value = value + 1;
            arrList.add(llist);

        }
        
        RequestFragment requestMemory = new RequestFragment();

        System.out.println("\nCreated Array list is: ");
        for (int i = 0; i < arrList.size(); i++) {
            LinkedList llist = arrList.get(i);
            requestMemory.printList(llist.head);
        }

        for(int i=40;i>0;i--){
          int random = randInt(0, 1000);
          System.out.println("\n Processing request for :"+ random);
          requestMemory.requestMemory(arrList, random);  
          
          System.out.println("\n After request memory Array list is: ");
              for (int j = 0; j < arrList.size(); j++) {
                  LinkedList llist = arrList.get(j);
                  requestMemory.printList(llist.head);
              }
        }
//        System.out.println("\n Processing request for :" + 9);
//        requestMemory.requestMemory(arrList, 9);
//        System.out.println("\n After request memory Array list is: ");
//        for (int j = 0; j < arrList.size(); j++) {
//            LinkedList llist = arrList.get(j);
//            requestMemory.printList(llist.head);
//        }
//        System.out.println("\n Processing request for :" + 20);
//        requestMemory.requestMemory(arrList, 20);
//        System.out.println("\n After request memory Array list is: ");
//        for (int j = 0; j < arrList.size(); j++) {
//            LinkedList llist = arrList.get(j);
//            requestMemory.printList(llist.head);
//        }
//        System.out.println("\n Processing request for :" + 64);
//        requestMemory.requestMemory(arrList, 64);
//        System.out.println("\n After request memory Array list is: ");
//        for (int j = 0; j < arrList.size(); j++) {
//            LinkedList llist = arrList.get(j);
//            requestMemory.printList(llist.head);
//        }
//        System.out.println("\n Processing request for :" + 33);
//        requestMemory.requestMemory(arrList, 33);
//        System.out.println("\n After request memory Array list is: ");
//        for (int j = 0; j < arrList.size(); j++) {
//            LinkedList llist = arrList.get(j);
//            requestMemory.printList(llist.head);
//        }

long endTime=System.currentTimeMillis();
long timeElapsed=requestMemory.timeCalculated(startTime,endTime);
        System.out.println("\n Total Failed Request: " + RequestFragment.failedReq);
         System.out.println("\n Total Fragements created in this request: " + RequestFragment.fragment);
        System.out.println("\n Total Time Elapsed in milliseconds: " + timeElapsed + "\n Total Time Elapsed in seconds: " + timeElapsed/1000);
    }

}
