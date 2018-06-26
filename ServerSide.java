// My first program is a tcp file sharing
// created by a beginner coder goes with the name " @Th3_w1ck3d " (Souleymane Guerida)
// On the way to do much more :D !!




import java.io.*;
import java.net.*;


public class ServerSide {
    public static void main(String[] args) throws IOException {
        // initialize the port number
        final int PortNumber = 2510;

        try {
            // starting the server at port 2510
            ServerSocket Wicked = new ServerSocket(PortNumber);
            System.out.println("Starting Wicked server at port : " + PortNumber);
            Thread.sleep(3000);
            System.out.println("Waiting for incoming connections ...");
            try {
                // accept connection from the clients
                Socket WickedClients = Wicked.accept();
                // print the client Ip when the connection is accepted
                System.out.println("Accepted connection from : " + WickedClients);
                Thread.sleep(3000);
                ServerSide.send(WickedClients, Wicked);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void send(Socket WickedClients, ServerSocket Wicked) throws IOException, InterruptedException {
        // Specify where the ServerSide file is located ...
        String Root = System.getProperty("user.dir");

        // Specify where the the desired file to transfer is located
        File file = new File(Root + File.separator + "Share/Song.mp3");

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        OutputStream os = WickedClients.getOutputStream();

           // Progress method, haven't finished it yet 
//         String state = "Downloading file : ";
//         String anim = "|/-\\";
//         int x;
//         for (x = 0; x <= 100; x++) {
//             String data = "\r" + state.toString() +  "  " + anim.charAt((x % anim.length())) + "  " + x;
//             System.out.write(data.getBytes());
//             System.out.print("%");
//             Thread.sleep(100);
//         }
        // read the data of the file then write it
        try {
            byte[] contents;
            long fileLength = file.length();
            long current = 0;

            while (current != fileLength) {
                int size = (int) file.length();
                if (fileLength - current >= size)
                    current += size;
                else {
                    size = (int) (fileLength - current);
                    current = fileLength;
                }
                contents = new byte[size];
                bis.read(contents, 0, size);
                os.write(contents);
            }

            os.flush();
            Wicked.close();
            WickedClients.close();
            System.out.println("\nFile sent successfully !");
        } catch (Exception e){
            System.out.println("File was not sent !!" + e);
        }
    }
 }
