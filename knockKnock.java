import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.StringTokenizer;

/* UDP client that implemenets the KKP 1.0.0 protocol. Payload of each packet is a String 
 * seperated into three parts seperated by "|". Part one includes: "KKP/1.0.0, second part 
 * includes a command, and third part is options for the command. All stated in the KKP 1.0.0
 * protocol.
 * -Alexis U Garcia */

public class knockKnock {
	
	static final String ID = "KKP";
	static final String VER = "1.0.0";
	//numJokes: static field that will hold the number of jokes that 
	static int numJokes;
	//setup: static field containing the setup message
	static String setup;
	//punch: static field containing the punch message
	static String punch;
	
	//Convert the message to a byte array, put together in a Datagram, and send it through the UDP socket
	private static void sendRequest(DatagramSocket s, InetAddress host, int port, String msg) throws Exception {
		//Converting the string into a list of bytes to send as a packet
		byte[] data = msg.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, host, port);
		//Shows exactly what the client is sending
		System.out.println("Sending [" + msg +"] to " + host + " on port " + port);
		s.send(packet);
    }
	
	//Receives the message, converts it to a string and processes it 
	private static void recieveRequest(DatagramSocket s) throws Exception {
		//Using 128 to create a buffer large enough for any possible joke
		byte[] buffer = new byte[128];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		s.receive(packet);
		//Converting date from byte to string and printing out
        String msgString = new String(packet.getData()).trim();
		System.out.println("Received [" + msgString + "] from " + packet.getAddress() + " to port " + s.getLocalPort());
        StringTokenizer tokens = new StringTokenizer(msgString, "|");
        //Breaks up the string into three parts, separated by a vertical line. StringTokenizer allows us to do this.
        String protocol = "";
        String cmd = "";
        String option = "";
        if (tokens.hasMoreTokens())
            protocol = tokens.nextToken();
        if (tokens.hasMoreTokens())
            cmd = tokens.nextToken();
        if (tokens.hasMoreTokens())
            option = tokens.nextToken().trim();
        if (protocol.equals(ID + "/" + VER)) {
        	if (cmd.equals("jokes"))
                numJokes = (int)(Math.random()*Integer.parseInt(option));
            else if (cmd.equals("setup"))
                setup = option;
            else if (cmd.equals("punch"))
                punch = option;
            
            if ((setup != null) && (punch != null))
                showJoke(setup, punch);
        	}
        }
      
	//This is where the setup and joke will be printed
   private static void showJoke(String setup, String punch) {
	   System.out.println("Knock knock");
	   System.out.println("Who's there");
	   System.out.println(setup);
	   System.out.println(setup + " who");	   
	   System.out.println(punch);
   }

	
	public static void main(String[] args) {		
		try {
			//Calling the DNS server to look up the IP address
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter a host: ");
			String host = scanner.nextLine();
	    	InetAddress address = InetAddress.getByName(host);
			System.out.println("Enter a port number: ");
			int inputPort = scanner.nextInt();
			int port = inputPort;
			DatagramSocket s = new DatagramSocket(0);
			//Setting a time to close socket so that it doesn't wait forever
			s.setSoTimeout(3000);
			//This is following the setup as instructed
	        sendRequest(s, address, port, ID +"/" + VER + "|jokes|-1");
			recieveRequest(s);
            sendRequest(s, address, port, ID +"/" + VER + "|setup|" + numJokes);
			recieveRequest(s);
            sendRequest(s, address, port, ID + "/" + VER + "|punch|" + numJokes);
			recieveRequest(s);
			scanner.close();
			s.close();
		}
		catch(Exception e) {
			System.err.println("Error: " + e);
		}
	}
}
