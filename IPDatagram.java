import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPDatagram {
    public static void main(String[] args)throws IOException {

        String MyIPAddress= "192.168.2.1";  //dieuthunsi pigis
        String IPAddressDest ="79.139.225.2"; //dieuthunsi proorismou


        String TCPport="80";
        final int TCPWindow = 65535;




        JOptionPane.showMessageDialog(null,"My IP address is: "+MyIPAddress);
        IPAddressValidation(MyIPAddress);   //elegxos egkurotitas ths dieuthunsis pigis

        JOptionPane.showMessageDialog(null,"The IP address of the destination is:"+IPAddressDest);
        IPAddressValidation(IPAddressDest);  //elegxos egkurotitas tis dieuthunsis proorismou

        JOptionPane.showMessageDialog(null, "The TCP port number is: "+TCPport);
        TCPportValidation(TCPport);  //elegxos egkurotitas tis TCP port

        CreateIpPacket(MyIPAddress, IPAddressDest); //dimiourgia paketou IP kai eggrafi dedomenon se fakelo
        CreateTCPPacket(TCPport, TCPWindow); //dimiourgia paketou TCP
        System.out.println("\nThe data have been saved in the file IPPacket.txt...");




    }

    public static void IPAddressValidation(String IP){

        Pattern pattern = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$"); //elegxos egkurotitas twn dieuthunsewn IP me xrisi pattern
        boolean matches = false;
        do {

            Matcher matcher = pattern.matcher(IP);
            matches = matcher.matches();
            if (matches == false){
                JOptionPane.showMessageDialog(null,"The IP is wrong.");
                break;
            }
            else {

                JOptionPane.showMessageDialog(null, "The IP address is correct!");
            }
        } while (!matches);
    }

    public static int TCPportValidation(String tcp){
        int Tcp = Integer.parseInt(tcp);
        if(Tcp >=1 && Tcp <=65535){
            JOptionPane.showMessageDialog(null, "The TCP port number is correct!");
        }
        else{
            JOptionPane.showMessageDialog(null, "The TCP port number is wrong!");
        }
        return Tcp;
    }

    public static void CreateIpPacket(String IPAddress, String IPAddressDest)throws IOException {
        String IPPacketTitle[] = {"Version", "IHL", "Total Length", "Protocol", "Time To Live", "Destination Address", "Source Address"};
        String IPPacketValue[] = {"4", "5","605", "6","255", IPAddressDest, IPAddress};

        int Version = Integer.parseInt(IPPacketValue[0]);
        int IHL = Integer.parseInt(IPPacketValue[1]);
        int TotalLength = Integer.parseInt(IPPacketValue[2]);
        int Protocol = Integer.parseInt(IPPacketValue[3]);
        int TTL = Integer.parseInt(IPPacketValue[4]);
        String version = Integer.toHexString(Version);        //metatropi dedomenwn sto dekaeksadiko sustima
        String ihl = Integer.toHexString(IHL);
        String total_length = Integer.toHexString(TotalLength);
        String protocol = Integer.toHexString(Protocol);
        String ttl = Integer.toHexString(TTL);

        String octet1 = IPAddress.substring(0,3);
        String octet2 = IPAddress.substring(4,7);
        String octet3 = IPAddress.substring(8,9);
        String octet4 = IPAddress.substring(10,11);
        int octet_1 = Integer.parseInt(octet1);
        int octet_2 = Integer.parseInt(octet2);
        int octet_3 = Integer.parseInt(octet3);
        int octet_4 = Integer.parseInt(octet4);
        String octet__1 = Integer.toHexString(octet_1);
        String octet__2 = Integer.toHexString(octet_2);
        String octet__3 = Integer.toHexString(octet_3);
        String octet__4 = Integer.toHexString(octet_4);


        String Octet1 = IPAddressDest.substring(0,2);
        String Octet2 = IPAddressDest.substring(3,6);
        String Octet3 = IPAddressDest.substring(7,10);
        String Octet4 = IPAddressDest.substring(11,12);
        int Octet_1 = Integer.parseInt(Octet1);
        int Octet_2 = Integer.parseInt(Octet2);
        int Octet_3 = Integer.parseInt(Octet3);
        int Octet_4 = Integer.parseInt(Octet4);
        String Octet__1 = Integer.toHexString(Octet_1);
        String Octet__2 = Integer.toHexString(Octet_2);
        String Octet__3 = Integer.toHexString(Octet_3);
        String Octet__4 = Integer.toHexString(Octet_4);

        String octets = octet__1 + octet__2 + octet__3 + octet__4;
        String Octets = Octet__1+ Octet__2 + Octet__3+ Octet__4;



        String HexIPPacketValue[] = {version, ihl, total_length, protocol, ttl, Octets , octets};

        PrintWriter file = new PrintWriter("IPPacket.txt");

        System.out.println("\nThe IP Packet in decimal values is as follows:");
        for(int i=0; i<IPPacketTitle.length;i++){

            System.out.println(IPPacketTitle[i] + " : "+IPPacketValue[i]);
        }

        System.out.println("\nThe IP packet in hexadecimal values is as follows:");

        for (int i=0; i<IPPacketTitle.length; i++){
            System.out.println(IPPacketTitle[i] + " : "+HexIPPacketValue[i]);
            file.println(IPPacketTitle[i] + " : "+HexIPPacketValue[i]); //eggrafi dedomenwn sto arxeio IPPacket.txt

        }

        file.close();

    }

    public static void CreateTCPPacket(String TCPPort, int maxWindow){   //dimiourgia paketou TCP kai apothikeusi twn dedomenwn se pinaka
        String MaxWindow = Integer.toString(maxWindow);
        String TCPPacketTitle[] = {"Destination Port","Source Port", "Window", "SYN"};
        String TCPPacketValue[] = {TCPPort, "1500", MaxWindow, "1"  };
        System.out.println("\nThe TCP Packet is as follows: ");
        for(int i=0; i<TCPPacketTitle.length; i++){
            System.out.println(TCPPacketTitle[i] + " : "+TCPPacketValue[i]);
        }
    }
}

