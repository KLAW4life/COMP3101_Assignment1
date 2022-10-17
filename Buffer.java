/*
 * The Buffer class containes the  code responsible for multithreading synchronization.
 * Semaphores are implemented using the producer consumer problem.
 * The semaphores help to achieve mutual exclusion of thread #2 and thread #3 
 * 
 * @author Kerene Wright
 * @version 1.0
 */

import java.util.concurrent.*;




class Buffer {
    private char [] buffer; // collection to store characters for use  
    private int count = 0; // variable to to keep track of amount of characters in the buffer
    private int pointer_start = 0; //pointer to track the location of the characters being enetered into the buffer.
    private int pointer_end = 0; //pointer to track the location of the last character leaving the buffer.
    private  int BUFFER_SIZE; // buffer size
    

    static Semaphore sem_consumer = new Semaphore(1); //semSignal
    static Semaphore sem_producer = new Semaphore(1); //semWait



    /*
     * Constructor
     */
    Buffer(int buff_size){
        BUFFER_SIZE = buff_size;
        buffer = new char[BUFFER_SIZE];
    }

    //using synchronized to achive mutual exclusion when putting characters into the buffer
    public synchronized void put(char msg_letter){  //when the buffer is full, the thread will automatically wait until the buffer is freed.
        while (count == buffer.length){
            //System.out.println("Buffer has exceeded max capacity");

            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error due to max buffer");  //error is thrown when buffer is full
            }
        }

        //the thread will use the producer semaphore to get the information needed. 
        //This is done using mutal exclusion.
        try {
            sem_producer.acquire(); //thread acquires the lock so no other thread can use the buffer.
            buffer[pointer_start] = msg_letter;
            //System.out.println("Current message letter: " +msg_letter);
            pointer_start = (pointer_start + 1) % buffer.length; //keep track of the message letters locations in the buffer. 
            count = count + 1;
            //System.out.println("Producer Counter = " +count);
        } catch (InterruptedException e) {}
        finally{sem_producer.release();} //the thread releases the lock on the resources.

        notify();//tells the other threading waiting that the current thread is finished and the lock is now avaliable.
    }
    

    public synchronized char Get(){
        //if the buffer is empty, then the thread will wait until the buffer get populated
        while(count == 0){
            //System.out.println("Current Buffer is empty......will proceed to wait until populated");
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }

        //if the buffer is not empty, then consumer semaphore will proceed using mutal exclusion
        char msg_letterout;
        try {
            sem_consumer.acquire();
            msg_letterout = buffer[pointer_end];
            //System.out.println("Current message letter: " +msg_letterout);
            pointer_end = (pointer_end + 1) % buffer.length; //keep track of the message letters locations leaving the buffer. 
            count = count - 1 ;
            //System.out.println("Consumer Counter = " + count);
        } catch (Exception e) {
            msg_letterout = 'e'; //initialized to e. will return e if an error is caught.
        }
        finally{
            sem_consumer.release(); //the thread releases the lock on the resources.
        }

        notify(); //tells the other threading waiting that the current thread is finished and the lock is now avaliable.
        return msg_letterout;
    }

    public boolean empty() {  //method to return 'true' or 'false' whether the buffer is empty or not
        if (count <= 0){
            return true;
        }
        else{
            return false;
        }
    } 

    //print out the contents of the buffer for debugging.
    public void contents(){
        for(int i =0; i<buffer.length; i++){

            System.out.println(buffer[i]+", count:" + count );

        }

    }
}



