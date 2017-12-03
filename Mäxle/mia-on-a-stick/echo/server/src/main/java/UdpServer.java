import java.net.*;
import java.util.Arrays;

public class UdpServer
{
  private static final int PORT = 9876;
  private static final int BUFFER_SIZE = 1024;

  public static void main(String args[])
      throws Exception
  {
    DatagramSocket serverSocket = new DatagramSocket(PORT);
    byte[] receiveData = new byte[BUFFER_SIZE];
    byte[] sendData = new byte[BUFFER_SIZE];

    System.out.println(String.format("Running echo server on %1s:%2d", "localhost", PORT));

    try {
      while (true) {
        Arrays.fill(sendData, (byte) 0);
        Arrays.fill(receiveData, (byte) 0);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);
        String sentence = new String(receivePacket.getData());
        InetAddress address = receivePacket.getAddress();
        int port = receivePacket.getPort();
        System.out.println("< [" + address.getHostName() + ":" + port + "] " + sentence);

        String capitalizedSentence = sentence.toUpperCase();
        System.out.println("> [" + address.getHostName() + ":" + port + "] " + capitalizedSentence);

        sendData = capitalizedSentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
        serverSocket.send(sendPacket);
      }
    } finally {
      if (serverSocket != null) {
        serverSocket.close();
      }
    }
  }
}
