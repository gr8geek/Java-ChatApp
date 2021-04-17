import java.net.*;
import java.io.*;
class Client{
    Socket socket;
    BufferedReader br ;
    PrintWriter out;
    public Client(){
        try{
            socket = new Socket("127.0.0.1",7777);
            System.out.println("Connection estabished");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            startReading();
            startWriting();

        }
        catch(Exception e){
            e.printStackTrace();
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
                        break;
                    }
                    System.out.println("Client:"+msg );
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
        new Client();
    }
}