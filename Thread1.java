/**
 * Insert Description
 * 
 * 
 * @author Kerene Wright
 * @version 1.0
 */


import java.io.BufferedReader; 
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.util.Arrays;


public class Thread1 extends Thread{

    private char[] msg_split;             // variable to contain the messgae when fragmented
    private CharBuffer msg_buff, main_buff;   // variables to represent the different buffers to be used
    private static final int BUFFER_SIZE = 12;

    public Thread1(CharBuffer main_buff){
        this.main_buff = main_buff;
    }

    public void run() {
        System.out.println("Thread1 has started....");
        System.out.println("Main Buffer Size: " +main_buff.length());
        System.out.println("Main Buffer Contents: " +Arrays.toString(main_buff.array()));  ///print out main buffer contents


        //for(int j =  0; j <= )


    }

}
