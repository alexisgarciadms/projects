import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.Scanner;

/* A UDP server that recieves any packet and sends back the packet to the client. In this 
 * scenerio, the date and time are being sent back. Ultimately, this process can happen for
 * as many amount of packets are being receiveed.*/

public class UDPDaytimed {
	
	//Create a datagram packet for receiving arbitrary data 
    private static DatagramPacket waitForTeaser(DatagramSocket s) throws Exception {
    	//Buffer long enough for any message
    	byte[] buffer = new byte[256];
    	DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        //Wait for incoming datagram packet
    	s.receive(packet);
    	return (packet);
    }
    
    //Create data, an array of bytes encoding today's date & time
    private static void sendDate(DatagramSocket s, InetAddress client, int port) throws Exception {
        //Create data, an array of bytes encoding today's date & time
    	Date today = new Date();
    	byte[] data = today.toString().getBytes();
    	DatagramPacket packet = new DatagramPacket(data, data.length, client, port);
    	//Sending datagram to client on port
    	s.send(packet);
    }

	public static void main(String[] args) {
		try {
			//Define port to listen on (preferably from the command line arguments)
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter a port number: ");
			int port = scanner.nextInt();
			boolean running = true;
			//Create a new DatagramSocket that listens to the specified port. 
			DatagramSocket s = new DatagramSocket(port);
			while (running) {
				DatagramPacket teaser = waitForTeaser(s);
				//extract client InetAdress and port that client listens to	
				sendDate(s, teaser.getAddress(), teaser.getPort());
			}
			scanner.close();
			s.close();
		}
		catch(Exception e) {
			System.err.println("Error: " + e);
		}
	}

}
