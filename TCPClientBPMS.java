package BPMS;

import java.net.*;  //for Socket
import java.io.*;   //for IOExceptionand Input/OutputStream

public class TCPClientBPMS
{

    public static void upload(String[] measurement) throws UnknownHostException, IOException
    {        
        String server = "127.0.0.1" /*args[0]*/ ;    //Server name or IP address
        //input string to bytes

        int servPort = 8080;

        //Create socket that is connected to server on specified port
        Socket socket;

        socket = new Socket(server, servPort);
        
        System.out.println("Connected to server...sending echo string");

        for(int i = 0; i < 5; i++)
        {
            byte[] byteBuffer = measurement[i].getBytes();

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            out.write(byteBuffer);  //Send the encoded string to the server

            //Receive the same string back from the server
            int totalBytesRcvd = 0; //Total bytes received so far
            int bytesRcvd;          //Bytes received in last read
            while (totalBytesRcvd < byteBuffer.length)
            {
                if((bytesRcvd = in.read(byteBuffer, totalBytesRcvd, byteBuffer.length - totalBytesRcvd)) == -1)
                    throw new SocketException("Connection closed prematurely");
                totalBytesRcvd += bytesRcvd;
            }

            System.out.println("Received " + new String(byteBuffer));
        }
        
        socket.close(); //Close the socket and its streams

    }
}
