import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/* A UDP client that first sends out a packet to a server and then waits for a respond. 
 * The client listens on some arbitrary port. After receieving the packet from the sever, 
 * the client prints out the data.
 * -Alexis U Garcia */

public class UDPDaytime {

	//Create a datagram packet for sending (arbitrary) data to address on port
	private static void sendRequest(DatagramSocket s, String host, int port) throws Exception {
    	byte[] data = new byte[1];
    	//Get the host address as an InetAddress, using static method getByName
    	InetAddress address = InetAddress.getByName(host);
    	DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
    	//Sending packet
    	s.send(packet);
    }
	
	//Create a datagram packet for receiving data of length data.length
	private static void waitForDate(DatagramSocket s) throws Exception {
		//Buffer long enough for any message
		byte[] buffer = new byte[256];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		//Wait for incoming datagram packet (which will contain the date/time)
		s.receive(packet);
        //Decode the data received in the datagram and print it out
		String today = new String(packet.getData());
		System.out.println(today);
	}
	
	public static void main(String[] args) {
		try {
			//Define host and port 
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter a host: ");
			String host = scanner.nextLine();
			System.out.println("Enter a port number: ");
			int port = scanner.nextInt();
			// Creates new DatagramSocket that listens on an arbitrary port.
            // Note: Using 0 as port binds the DatagramSocket to first available port 
			DatagramSocket s = new DatagramSocket(0);
			s.setSoTimeout(5000);
			sendRequest(s, host, port);
			waitForDate(s);
			scanner.close();
			s.close();
		}
		catch(Exception e) {
			System.err.println("Error: " + e);
		}
	}

}
