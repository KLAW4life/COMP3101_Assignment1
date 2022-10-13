/**
 * @author Kerene Wright
 * @version 1.0
 */

import java.io.BufferedReader;  //class to read input from the buffer
import java.io.InputStreamReader;
import java.nio.BufferOverflowException;
import java.nio.CharBuffer;
import java.util.*;



public class Buffer extends Thread{

    private static final int MSG_BUFFER_SIZE = 255;
    
    public static void main(String[] args)
    {
        //create the main buffer from the input message into a character buffer with the max size of 255 characters.
        CharBuffer main_buff = CharBuffer.allocate(MSG_BUFFER_SIZE);  
        

        BufferedReader msg_input = new BufferedReader(new InputStreamReader(System.in));  //create a buffer reader
        String read; //reader for the buffer

        System.out.println("Enter the text that needs to be converted:");
        try {                                
            read = msg_input.readLine();  //readLine() reads the data from the user into the variable "read".
        } catch (Exception e) {
            read = "Input messgae error";
        }
        System.out.println(read);

        char[] msg_array = read.toCharArray();  //converts the input message into an character array
        System.out.println(msg_array);

        try {
            if (msg_array.length <= MSG_BUFFER_SIZE)  //checks to see if the message fulls the buffer or not
            {
                for(int i = 0; i <= msg_array.length -1 ; i++)
                {
                    main_buff.put(msg_array[i]);
                    //System.out.println("Main Buffer Contents: " +msg_array[i]);
                    System.out.println("Main Buffer Contents: " +Arrays.toString(main_buff.array()));  ///print out main buffer contents

                }
                System.out.println("Messgae has not exceeded buffer's capacity...");
            }
        } catch (BufferOverflowException e) {    //throws the error is buffer reaches capacity

            System.out.println("Buffer's is full...");
        }
        
         
        // Creating out threads to be run.
        Thread1 thread1 = new Thread1(main_buff);
        //Thread2 thread2 = new Thread2();
        //Thread3 thread3 = new Thread3();
 
        // Starting our threads to run.
        thread1.start();
        //thread2.start();
        //thread3.start();


    }
}
