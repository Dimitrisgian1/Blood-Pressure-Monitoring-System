package BPMS;

import java.net.*;  //for Socket
import java.io.*;   //for IOExceptionand Input/OutputStream

public class TCPServerBPMS extends Thread
{

    private static final int BUFSIZE = 32;  // Size of receive buffer
    private int servPort;
    private BPBook bpBook;

    public TCPServerBPMS(int port) {
        this.servPort = port;
    }


    private void HandleConn()
    {
         //Create a server socket to accept client connection requests
         ServerSocket servSock;
         try {
             servSock = new ServerSocket(servPort);
 
             int recvMsgSize;    //Size of received message
             byte[] byteBuffer = new byte[BUFSIZE];  //Receive buffer
 
             for(;;) //Run forever accepting and serving connections
             {
                 Socket clntSock = servSock.accept();    //Get client connection
 
                 System.out.println("Handling client at " + clntSock.getInetAddress().getHostAddress() + " on port " + clntSock.getPort());
 
                 InputStream in = clntSock.getInputStream();
                 OutputStream out = clntSock.getOutputStream();
 
                 //Receive until client closes connection, indicated by -1 return 
                 while((recvMsgSize = in.read(byteBuffer)) != -1)
                     out.write(byteBuffer, 0, recvMsgSize);
 
                 clntSock.close();   //Close the socket. We are done with this client.
             }
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
    }


    @Override
    public void run()
    {
        HandleConn();
        
        
    }
}
