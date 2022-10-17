/**
 * Thread1 is responsible for transfering the characters from the input message to the main buffer.
 * 
 * 
 * @author Kerene Wright
 * @version 1.0
 */


import java.io.BufferedReader; 
import java.io.InputStreamReader;
import java.util.concurrent.*;

public class Thread1 extends Thread{

    private Buffer msg_container;  //input message
    private Buffer buffer;         //main buffer

    public Thread1(Buffer msg_container, Buffer buffer){
        this.msg_container = msg_container;
        this.buffer = buffer;
    }

    public void run() {
        //System.out.println("Thread1 has started....");
        
        while(!msg_container.empty()){
            char msg = msg_container.Get();
            // buffer.contents(); //print the contents of the buffer for debugging purposes.
           
            buffer.put(msg);// characters are entered into the main buffer.
            
            // try{
            //     Thread.sleep(1000);  //set thread time for debugging purposes.
            // }
            // catch(Exception e){}
        }

    }


}
