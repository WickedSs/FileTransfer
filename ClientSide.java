// My first program is a tcp file sharing
// created by a beginner coder goes with the name " @Th3_w1ck3d " (Souleymane Guerida)
// On the way to do much more :D !!




import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ClientSide {
    public static String filePath;
    public static void main(String[] args) throws IOException {
        // declare the Scanner
        Scanner reader = new Scanner(System.in);

        // take the user input of IPAddress AND Port
        System.out.print("Enter the IPAddress of Wicked Server : ");
        String IPAddress = reader.nextLine();
//      System.out.println("Give the file path ---> ");
//      filePath = reader.nextLine();


        // bound the IPAddress and Port to connect to the WickedServer
        try {
            Socket WickedConnection = new Socket(IPAddress, 2510);
            System.out.println("Successfully connected to Wicked Server :D !");
            Thread.sleep(3000);
            ClientSide.Receive(WickedConnection);

        }catch (Exception e) {
            System.out.println("Lost connection !!!");
        }


    }


    public static void Receive(Socket WickedConnection) throws IOException {

        byte[] contents = new byte[10000];
        //Set the FileOutputStream to the output file's full path.
        FileOutputStream fos = new FileOutputStream("G:/Trans-file.mp3");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        InputStream is = WickedConnection.getInputStream();

        // Number of bytes read in one read() call
        int bytesRead = 0;

        while((bytesRead=is.read(contents)) != -1)
            bos.write(contents, 0, bytesRead);

        bos.flush();
        WickedConnection.close();
        System.out.println("File saved successfully!");


    }
}
