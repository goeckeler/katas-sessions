import java.io.*;
import java.net.*;

public class UdpClient
{
  private static final String HOST = "localhost";
  private static final int PORT = 9876;
  private static final int BUFFER_SIZE = 1024;

  private final String host;
  private final int port;

  public static void main(String args[])
      throws Exception
  {
    System.out.println("Usage:   udpclient [<host> [<port> [<message>]]]");
    System.out.println(String.format("Default  udpclient %1s %2d < ... \n", HOST, PORT));

    int port = PORT;
    if (args.length >= 2) {
      port = Integer.valueOf(args[1]);
    }

    String host = HOST;
    if (args.length >= 1) {
      host = args[0];
    }

    String sentence = "";
    if (args.length >= 3) {
      for (int pos = 2; pos < args.length; ++pos) {
        sentence += " " + args[pos];
      }
      sentence = sentence.trim();
      System.out.println("> " + sentence);
    } else {
      System.out.print("$ ");
      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
      sentence = inFromUser.readLine();
      System.out.println("> " + sentence);
    }

    System.out.println("< " + new UdpClient(host, port).echo(sentence));
  }

  public UdpClient(final String host, final int port) {
    this.host = host;
    this.port = port;
  }

  public String echo(final String message)
    throws IOException
  {
    InetAddress IPAddress = InetAddress.getByName(host);
    DatagramSocket socket = new DatagramSocket();

    byte[] sendData = new byte[BUFFER_SIZE];
    sendData = message.getBytes();

    // send data to server
    DatagramPacket datagram = new DatagramPacket(sendData, sendData.length, IPAddress, port);
    socket.send(datagram);

    // receive response from server
    byte[] receiveData = new byte[BUFFER_SIZE];
    datagram = new DatagramPacket(receiveData, receiveData.length);
    socket.receive(datagram);

    String response = new String(datagram.getData());
    socket.close();

    return response;
  }
}
