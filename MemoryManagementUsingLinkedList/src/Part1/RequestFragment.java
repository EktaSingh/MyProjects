package Part1;

import Part1.LinkedList.Node;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by shrut on 1/27/2017.
 */
public class RequestFragment {

    public static long timeCalculated(long startTime, long endTime) {
        long timeElapsed = endTime - startTime;
        return timeElapsed;
    }
    static int failedReq = 0;
    static int fragment=0;

    public void requestMemory(ArrayList<LinkedList> arrList, int memory) {

        int requestedMemory = memory;

        // Step 0: Check if memory requested exceeds available space
        boolean check = requestMemoryCheck(arrList, memory);
        if (check) {
            failedReq++;
            System.out.println("Not enough memory to process for " + requestedMemory);
            return;
        }
        // Step 1: Check if exists in the array - o(n)
        String result;
        for (int i = 0; i < arrList.size(); i++) {
            if (arrList.get(i).head == null) { // even if linked list is empty it is stil in the AL. so check and remove-[]
                arrList.remove(i);
                i++;

            }

            if (i < arrList.size()) {

                LinkedList llist = arrList.get(i);
                if (!llist.isEmpty(llist)) {

                    int value = llist.head.data;

                    if (value == memory) {
                        result = "\nFound in the array";

                        llist.pop(llist);

                        System.out.println(result + " " + requestedMemory);
                        result = "\nRequest successful for";
                        System.out.println(result + " " + requestedMemory);
                        return;
                    }

                }

            }
        }

        result = "\nNot Found in the array";
        System.out.println(result + " " + requestedMemory);

        // ************** Step 1 Ends here ********************
        // Step 2: Fragment and defragment
        System.out.println("Trying to fragment and defragment...");
        memory = modulus(arrList, memory);
        if (memory == 0) // success
        {
            result = "\nRequest successful for";
            System.out.println(result + " " + requestedMemory);
            return;
        }

        // ************** Step 2 Ends here ********************
        // Step 3: Waste memory
        System.out.println("Trying to allocate extra memory...");

        for (int i = 0; i < arrList.size(); i++) { //o(n)
            if (arrList.get(i) == null) {
                arrList.remove(i);
                i++;
            }

            LinkedList llist = arrList.get(i);
            if (!llist.isEmpty(llist)) {
                int value = llist.head.data;
                if (value > memory) {

               if (value == memory / 2) { // if double size available split the block
                        llist.pop(llist);
                         System.out.println("Popping linked list to divide the block of value : "+ value);
                        int data = memory / 2;

                        for (LinkedList list : arrList) {  // o(n)

                            Node head = list.head;
                            if (head != null) {
                                if (head.data == data) {
                                    fragment++;
                                    list.push(data);
                                    memory = memory / 2;
                                    break;
                                }
                            }

                        }
                        LinkedList newLlist = new LinkedList();
                        newLlist.push(data);
                        arrList.add(newLlist);
                        arrList = doInsertionSort(arrList);
                        if (memory == 0) {
                            result = "\nRequest successful for";
                            System.out.println(result + " " + requestedMemory);
                        }
                        return;
                    }

                } else{                    
                    if(!llist.isEmpty(llist)){
                    llist.pop(llist);
                    System.out.println("Popping linked list to allocate extra memory : "+ value);
                    result = "Alloacted extra memory for ";
                    System.out.println("Memory allocation pending " + memory);
                    memory = 0;
                    System.out.println(result + " " + requestedMemory + " is " + value);

                }}

                return;
            }

        }

//        if (memory == 0) {
//            result = "\nRequest successful for";
//            System.out.println(result + " " + requestedMemory);
             
        //}
//        else{
//        result = "\nRequest failed for";
//        failedReq++;
//        System.out.println(result + " " + requestedMemory);
//        }

        // ************** Step 3 Ends here ********************
    }

    public static ArrayList<LinkedList> doInsertionSort(ArrayList<LinkedList> input) {

        int temp;

        for (int i = 1; i < input.size(); i++) {

            
            for (int j = i; j > 0; j--) {
                if(input.get(j).head!=null && input.get(j-1).head!=null){
                if (input.get(j).head.data < input.get(j - 1).head.data) {
                    temp = input.get(j).head.data;
                    input.get(j).head.data = input.get(j - 1).head.data;
                    input.get(j - 1).head.data = temp;
                }
            }
        }
        }
        return input;
    }

    public boolean requestMemoryCheck(ArrayList<LinkedList> arrList, int memory) {
        long total = 0;
        for (LinkedList llist : arrList) { // o(n)
            Node n = llist.head;
            while (n != null) {
                total += n.data;
                n = n.next;
            }
        }

        if (memory > total) {
            return true;
        } else {
            return false;
        }
    }

    public int modulus(ArrayList<LinkedList> arr, int memory) {
        //n=33
        int remainder = memory % 2;//rem=1
        memory = memory - remainder;
        int allocationReturn = 0;
        if (remainder == 1) {
            allocationReturn = allocation(arr, remainder);
            System.out.println("Allocation returned"+ allocationReturn);
        }
        for (LinkedList llist : arr) { // o(n)
            Node n = llist.head;
            while (n != null && memory > 0) {
                if (n.data == memory) { // if memory requested is directly available
                    llist.pop(llist);
                     System.out.println("Popping linked list if exact match found  : "+ n.data);
                    memory = 0;
                    return allocationReturn;
                } else if (n.data < memory) { // or if something less than requested memory is available
                    memory = memory - n.data;
                    llist.pop(llist);
                    System.out.println("Popping linked list if value found less than the memory  : "+ n.data);

                } else {
                    break;
                }
                n = n.next;
            }
        }

        if (memory > 0) {
            memory = allocation(arr, memory + allocationReturn);
        }
        return memory;
    }

    public int allocation(ArrayList<LinkedList> arr, int n) {

        int diffPrev = n;
        if (n == 1) {

            LinkedList llistCurrent = arr.get(0);
            if (!llistCurrent.isEmpty(llistCurrent)) { // o(1)
                if (arr.get(0).head.data == 1) {

                    llistCurrent = arr.get(0);
                    llistCurrent.pop(llistCurrent);
                    System.out.println("Popping linked list if exact match found for 1 : "+ arr.get(0).head.data);
                    return 0;
                }
            } else {
                return diffPrev;
            }

        }

       /* for (int i = arr.size() - 1; i > 0; i--) {
            LinkedList llistCurrent = arr.get(i);
            LinkedList llistPrev = arr.get(i - 1);
            if (!llistCurrent.isEmpty(llistCurrent) && !llistPrev.isEmpty(llistPrev)) {
                LinkedList.Node headCurrent = llistCurrent.head;
                LinkedList.Node headPrev = llistPrev.head;
                Node nCurrent = headCurrent;

                if (nCurrent != null && diffPrev > 0) {
                    if (headCurrent.data > diffPrev && headPrev.data < diffPrev) {
                        diffPrev = diffPrev - headPrev.data;
                        Node node = headPrev;
                        if (node != null) {
                            llistPrev.pop(llistPrev);
                        }
                    } else if (headCurrent.data == diffPrev) {
                        diffPrev = diffPrev - headCurrent.data;
                        Node node = headCurrent;
                        if (node != null) {
                            llistCurrent.pop(llistCurrent);
                        }
                    } else if (headPrev.data == diffPrev) {
                        diffPrev = diffPrev - headPrev.data;
                        Node node = headPrev;
                        if (node != null) {
                            llistPrev.pop(llistPrev);
                        }
                    }

                    //nCurrent = nCurrent.next;
                }
            }
        }*/
        return diffPrev;
    }

    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public void printList(LinkedList.Node head) {
        LinkedList.Node tnode = head;
        System.out.print("[");
        while (tnode != null) {

            System.out.print(tnode.data + " ");

            tnode = tnode.next;
        }
        System.out.print("]");
    }

}
