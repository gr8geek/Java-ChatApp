import java.net.*;
import java.io.*;
class Server{
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    public Server(){
        try{
            server = new ServerSocket(7777);
            socket = server.accept();

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();
        }
        catch(Exception e){
            e.printStackTrace();
            //System.out.println()
        }
    }
    public void startReading()throws IOException{
        // Reader Thread
        Runnable r1 = ()->{
            System.out.println("Reader thread stated");
            while(true){
                try{
                    String msg = br.readLine();
                    if(msg.equals("exit")){
                        System.out.println("Client Has stopped the chat");
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        new Thread(r1).start();
    }
    public void startWriting(){
        // Writer Thread
        Runnable r2 = ()->{
            while(true){
                System.out.println("Writer running");
                try{
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        new Thread(r2).start();

    }
    public static void main(String args[]){
        System.out.println("Hello");
        new Server();
    }
}