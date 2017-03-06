package cm.neu.edu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by shrut on 2/14/2017.
 */
public class RequestFragment {

    static Node root;

    static int numberOfBusyOne = 0;
    static int requestCall = 0;
    static int numberOfFreeOne = 0;
    static int allocatedMemory = 0;
    static int failedRequest = 0;
    static int totalMemory=0;
    static int availableMemory=0;


    public RequestFragment() {
        root = null;
    }

    public void insert(int key) {
        root = insertNode(key, root);
    }


    public Node insertNode(int key, Node root) {

        if (root == null) {
            Node newnode = new Node(key);
            root = newnode;

            return root;
        } else

        {
            addNewNode(root, key);
        }

        return root;
    }

    public int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public void print() {
        printBSTLevelOrder(root);
    }

    public void printBST(Node root) {
        if (root != null) {

            System.out.print(" " + root.key + " ");

            printBST(root.left);

            printBST(root.right);
        }
    }

    public void addNewNode(Node node, int key) {
        if (node.left != null) {
            addNewNode(node.left, key);
        }
        if (node.right != null) {
            addNewNode(node.right, key);
        }
        if (node.left == null && node.right == null) {
            //Accessing every leaf leaf!

            node.left = new Node(key);
            node.right = new Node(key);

            //On reaching last leaf, we add new nodes at the end of both the right and left left, making the new nodes the leafs

        }
    }

    //Returning hte number of available 1's in the tree because we want to occupy all the 1's first
    //The allocating of the 1's is handled by the allocate for 1 method

    public int visitNodeForOne(Node node) {
        if (node.left != null) {
            visitNodeForOne(node.left);
        }
        if (node.right != null) {
            visitNodeForOne(node.right);
        }
        if (node.left != null && node.right != null) {
            //Checking if the 1's on the left are available
            if (node.left.key == 1)

            {
                if (node.left.statusFree)
                    numberOfFreeOne++;
            }

            //Checking if the 1's on the right are available
            if (node.right.key == 1)

            {
                if (node.right.statusFree)
                    numberOfFreeOne++;
            }

        }

        return numberOfFreeOne;
    }


    int[] result = new int[2];
    public int[] visitNodeForTotal(Node node) {

        if (node.left != null) {
            visitNodeForTotal(node.left);
        }
        if (node.right != null) {
            visitNodeForTotal(node.right);
        }
        if (node.left != null && node.right != null) {
            //Checking if the 1's on the left are available
            if(node.statusFree)
            availableMemory += node.key;
            totalMemory += node.key;



        }
        result[0] = availableMemory;
        result[1] = totalMemory;
        return result;
    }

    public int allocateNodeForOne(Node node, int requestedMemory) {
        if (node.left != null) {
            if (numberOfBusyOne == requestedMemory)
                return 0;
            allocateNodeForOne(node.left, requestedMemory);
        }
        if (node.right != null) {
            if (numberOfBusyOne == requestedMemory)
                return 0;
            allocateNodeForOne(node.right, requestedMemory);
        }
        if (node.left == null && node.right == null) {
            if (node.key == 1 && numberOfBusyOne < requestedMemory) {

                if (numberOfBusyOne < requestedMemory) {
                    node.statusFree = false;
                   System.out.println();
                   System.out.println(node.key+" Made busy.");
                    node.key=0;
                    numberOfBusyOne++;

                }


                if (numberOfBusyOne == requestedMemory)
                    return 0;
            }
        }
        return requestedMemory-numberOfBusyOne;
    }


    //This is to set the status of the parent node as free if all its children are busy i.e. the parent node is now the new leaf
    public void checkAndUpdateStatusOfParent(Node node) {
        if (node.left != null && node.left.key!=0) {
            checkAndUpdateStatusOfParent(node.left);
        }
        if (node.right != null && node.right.key!=0) {
            checkAndUpdateStatusOfParent(node.right);
        }

          if (node.left != null || node.right != null) {

              if(node.left.key==0 && node.right.key==0 && node.statusFree==false)
              {
                  node.statusFree=true;
              }

                if ((node.left.statusFree == false || node.right.statusFree == false) && (node.left.key!=0 || node.right.key!=0)) {
                    node.statusFree = false;
                    System.out.println();
                    System.out.println(node.key+" Parent Made busy.");

                }

            }

    }

    public void printBSTLevelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node current = q.peek();
            System.out.print(current.key + " ");

            if (current.left != null) {
                q.add(current.left);
            }

            if (current.right != null) {
                q.add(current.right);
            }
            q.remove();
        }
    }

    public void requestMemory(int requestedMemory) {
        //Step 1:Check if root is exact match and that it status and its descendants are available



        if ((requestedMemory <= totalMemory && requestedMemory % 2 == 0) || requestedMemory==1) {
            //Step1:
            //We are using the 1's only if it is equal to the requested memory
            int countOfOne = 0;
            countOfOne = visitNodeForOne(root);
            numberOfFreeOne = 0;


            if (countOfOne >= requestedMemory || requestCall == 1) {
                int memoryStillToBeAllocated=allocateNodeForOne(root, requestedMemory);
                numberOfBusyOne = 0;
                checkAndUpdateStatusOfParent(root);
                if(memoryStillToBeAllocated>0)
                {
                    allocateNode(root,memoryStillToBeAllocated);
                }

            }
            //
            else {
                // for layer above node with value 1
                allocateNode(root, requestedMemory);
                allocatedMemory = 0;
            }
        } else if ( requestedMemory <= totalMemory && requestedMemory % 2 != 0) {


            //Handle cases where the requested memory is an odd number.
            int remainder = requestedMemory % 2;

            int remainingRequestedMemory = requestedMemory - remainder;

            //First try to allocate the 1
            //Then allocate the remaining value
            requestMemory(remainder);
            requestMemory(remainingRequestedMemory);

        } else if ( requestedMemory > totalMemory) {
            System.out.print("");
            System.out.println("Not enough memory!");
            System.out.print("");
            failedRequest++;
            return;
        }

    }


    public void allocateNode(Node node, int requestedMemory) {

        if (node.left!= null && node.left.key!=0)
        {
            allocateNode(node.left, requestedMemory);
        }

        if (node.right != null && node.right.key!=0) {

            allocateNode(node.right, requestedMemory);
        }

        if ((node.left == null && node.right==null) || (node.left.key==0 || node.right.key==0))
        {

            if (allocatedMemory < requestedMemory)
            {
                if (node.key <= requestedMemory && node.key != 0 && node.statusFree) {
                    node.statusFree = false;
                    allocatedMemory += node.key;
                    System.out.println("");
                     System.out.println(node.key+" Made busy.");

                    node.key = 0;

                    checkAndUpdateStatusOfParent(root);

                }

                else if (node.key > requestedMemory && node.key != 0 && node.statusFree)
                {
                    if (allocatedMemory < requestedMemory) {
                        node.statusFree = false;
                       System.out.println();
                        System.out.println(node.key+" Made busy.");


                        allocatedMemory += node.key;
                        node.key = 0;

                        checkAndUpdateStatusOfParent(root);
                    }
                }
            }
        }

    }
}

