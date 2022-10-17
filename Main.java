/**
 * This is where the user will input the message for the various threads to transfer into the buffer,
 * capitalize the characters and then finally convert them into packts to be displayed.
 * 
 * @author Kerene Wright
 * @version 1.0
 */

import java.io.BufferedReader;  //class to read input from the buffer
import java.io.InputStreamReader;
import java.nio.BufferOverflowException;
import java.util.*;



public class Main extends Thread{

    private static final int MSG_BUFFER_SIZE = 255;
    public static final int BUFFER_SIZE = 12; //Main Buffer and Secondary Buffer size.

    public static void main(String[] args)
    {

        //character array to hold the input messgae
        Buffer msg_container = new Buffer(MSG_BUFFER_SIZE); 
        
        //main buffer 
        Buffer buffer = new Buffer(BUFFER_SIZE);
        
        //secondary buffer
        Buffer buffer2 = new Buffer(BUFFER_SIZE);
        

        BufferedReader msg_input = new BufferedReader(new InputStreamReader(System.in));  //create a buffer reader
        String read; //reader for the buffer

        System.out.println("Enter the text that needs to be converted:");
        try {                                
            read = msg_input.readLine();  //readLine() reads the data from the user into the variable "read".
        } catch (Exception e) {
            read = "Input messgae error";
            System.out.println(read);
        }
        //System.out.println(read);

        char[] msg_array = read.toCharArray();  //converts the input message into an character array
        //System.out.println(msg_array);

        try {
            if (msg_array.length <= MSG_BUFFER_SIZE)  //checks to see if the message fulls the buffer or not
            {
                for(int i = 0; i <= msg_array.length -1 ; i++)
                {
                    msg_container.put(msg_array[i]);

                }
                //System.out.println("Message has not exceeded buffer's capacity...");
            }
            else
            {
                for(int i = 0; i <= msg_array.length -1 ; i++)
                {
                    msg_container.put(msg_array[i]);

                }
                System.out.println("Message has exceeded buffer's capacity...");
            }
            }
        catch (BufferOverflowException e) {    //throws the error is buffer reaches capacity

            System.out.println("Buffer's is full...");
        }
        
        
               
        
         
        // Creating out threads to be run.
        Thread1 thread1 = new Thread1(msg_container,buffer);  //sending the message and main buffer
        Thread2 thread2 = new Thread2(msg_container,buffer, buffer2);  //sending the message, main and secondary buffer
        Thread3 thread3 = new Thread3(buffer2,thread2);  //sending the second buffer and thread2
 
        // Starting our threads to run.
        thread1.start();
        thread2.start();
        thread3.start();


    }
}
