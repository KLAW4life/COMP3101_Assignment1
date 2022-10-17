/**
 * Thread2 is responsible for retrieving the characters from the main buffer,
 * converting them into uppercase letters and tranferring them into the secondary buffer
 * for thread3 to finalize the process.
 * 
 * @author Kerene Wright
 * @version 1.0
 */

import java.util.concurrent.*;

public class Thread2 extends Thread{

    private Buffer msg_container;  //message
    private Buffer buffer;         //main buffer
    private Buffer buffer2;        //secondary buffer

    public Thread2(Buffer msg_container, Buffer buffer, Buffer buffer2){
        this.msg_container = msg_container;
        this.buffer = buffer;
        this.buffer2 = buffer2;
    }

    public void run() {
        //System.out.println("Thread2 is running");

        //while loop will run until thread one is terminated
        while(true){

            //When both the messgae and main buffer contents are empty, the this thread will be terminated.
            if((msg_container.empty() == true) && (buffer.empty() == true)){
                return; //ends the thread
            }

            char thread2_msg = buffer.Get();   //getting the characters from the main buffer
            //System.out.println("char from buffer 1: " + thread2_msg);
            buffer2.put(Character.toUpperCase(thread2_msg));  //capitalizes the characters from main buffer and places them into the seondary buffer for thread3 to use

            // try{
            //     Thread.sleep(1000);  //set thread time for debugging purposes.
            // }
            // catch(Exception e){} 
        }

    }

}