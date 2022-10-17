/**
 * Thread3 is responsible for accepting the capitalized characters from the secondary buffer from thread2 
 * and display as 2 digit packet numbers along with the message.
 * 
 * @author Kerene Wright
 * @version 1.0
 */

 import java.util.concurrent.*;

public class Thread3 extends Thread{

    private Buffer buffer2;
    private static int packets = -1;
    private int packet_content_count  = 0;
    private Thread t2_ref;
    private char contents[] = new char[Main.BUFFER_SIZE - 6]; // 6 characters taken from the buffer and 6 characters for the packet digits and ':' to be outputted
    private String word;

    //Constructor
    public Thread3(Buffer buffer2, Thread thread2 ){
        this.buffer2 = buffer2;   //secondary buffer with capitalized characters
        this.t2_ref = thread2;    //thread2 being passed
    }


    public void run() {
        //System.out.println("Thread3 is running");
        
        
        //while loop will run continously until thread3 terminates
        while(true){


            if(!buffer2.empty()){
                char take = buffer2.Get();

                // if the packet is not full then the character will be added to the current packet number.   
                if(packet_content_count < contents.length){
                   contents[packet_content_count] = take;
                   packet_content_count++;
                }
                else{
                    packets++;
                    word = new String();
                    for(int i =0; i< packet_content_count; i++){word  = word + contents[i];} 

                    // if packet digit is less than double digits then the '0' is added to the print statement 
                    if(packets < 10){
                        System.out.println("0"+ packets +":0" + packet_content_count + ":"  + word);
                        
                    }
                    else{
                        System.out.println(packets +":0"+ packet_content_count + ":"  + word); //double digit packet digits are added by counter 'i'
                    }

                    
                    //recreating the packet
                    packet_content_count =0;
                    contents = new char[Main.BUFFER_SIZE - 6];
                    contents[packet_content_count] = take;
                    packet_content_count++;
                    

                }

            
            }
            else{
                // if buffer 2 is empty print packet that currently exists 
                // packets++;
                // word = new String();
                //     for(int i =0; i< packet_content_count; i++){word  = word + contents[i];} 
                // if(packets < 10){
                //         System.out.println("0"+ packets +":0"+packet_content_count + ":"  + word);
                        
                //     }
                //     else{
                //         System.out.println(packets +":0"+packet_content_count + ":"  + word); //double digit packet digits are added by counter 'i'
                //     }
            }



            // try{
            //     Thread.sleep(1000);  //set thread time for debugging purposes.
            // }
            // catch(Exception e){}

            //thread3 will terminate when the second buffer is empty and thread2 is terminated. 
            if(buffer2.empty() && !t2_ref.isAlive()){
                packets++;
                word = new String();
                    for(int i =0; i< packet_content_count; i++){word  = word + contents[i];} 
                if(packets < 10){
                        System.out.println("0"+ packets +":0" +packet_content_count + ":"  + word);
                        
                    }
                    else{
                        System.out.println(packets +":0"+packet_content_count + ":"  + word); //double digit packet digits are added by counter 'i'
                    }

                return;

            }
        }

    }

}