package ipfinder;

import java.util.Arrays;

public class IP {

    private String ip;
    private String[] ipArr;
    private String network;
    private String broadcast;
    private String hostMin;
    private String hostMax;
    private int CIDR;
    private int mixedOctet;
    private int min;
    private int max;
    private int decOctet;
    private int binOctet;
    private double hostCount;

    public IP(String ip, int CIDR) {
        this.ip = ip;
        this.CIDR = CIDR;
        ipArr = ip.split("\\.");
        min = 0;
        max = 0;
        mixedOctet = 0;
        decOctet = 0;
        binOctet = CIDR;

        network = "";
        broadcast = "";
        hostMin = "";
        hostMax = "";
        hostCount = 0;
    }

    private void getMixedOctet() {
        while((binOctet -= 8) > 0 ) { mixedOctet++; }
        binOctet += 8;
    }

    private int getDecOctet() {
        decOctet = Integer.parseInt(ipArr[mixedOctet]);
        return decOctet;
    }

    private String[] getBinOctet() {
        getDecOctet();
        String binStr = "";
        while(binOctet-- > 0) binStr += "1";
        String[] binArr = String.format("%1$-8s", binStr).replace(' ', '0').split("");
        return binArr;
    }

    private void createMinMax() {
        String[] binArr = getBinOctet();
        int finder = 0;

        for(int i = binArr.length-1; i >=0; i--) {
            if(binArr[i].equals("1")) {
                finder = (int)Math.pow(2, 7-i);
                break;
            }
        }

        if(finder != 0) { min = decOctet - (decOctet % finder); }
        max = min + finder - 1;
    }

    public int getMin() { return min; }
    public int getMax() { return max; }

    public String getNetwork() {
        getMixedOctet();
        createMinMax();

        ipArr[mixedOctet] = String.valueOf(min);

        if(mixedOctet != 3) {
            for(int i = mixedOctet+1; i <=3; i++ ) {
                ipArr[i] = "0";
            }
        }
        network = String.join(".", ipArr);
        return network;
    }

    public String getBroadcast() {
        getMixedOctet();
        createMinMax();

        ipArr[mixedOctet] = String.valueOf(max);

        if(mixedOctet != 3) {
            for(int i = mixedOctet+1; i <=3; i++ ) {
                ipArr[i] = "255";
            }
        }
        broadcast = String.join(".", ipArr);
        return broadcast;
    }

    public String getHostMin() {
        getMixedOctet();
        createMinMax();

        ipArr[mixedOctet] = String.valueOf(min);
        if(mixedOctet != 3) {
            for(int i = mixedOctet+1; i <3; i++ ) {
                ipArr[i] = "0";
            }
        }
        ipArr[3] = "1";
        hostMin = String.join(".", ipArr);
        return hostMin;
    }

    public String getHostMax() {
        getMixedOctet();
        createMinMax();

        ipArr[mixedOctet] = String.valueOf(max);
        if(mixedOctet != 3) {
            ipArr[3] = "254";
            for(int i = mixedOctet+1; i <3; i++ ) {
                ipArr[i] = "255";
            }
        }
        if(ipArr[mixedOctet] == ipArr[3]) { ipArr[3] = String.valueOf(Integer.parseInt(ipArr[3])-1); }
        hostMax = String.join(".", ipArr);
        return hostMax;
    }

    public double getHostCount() {
        int hostBits = 32 - CIDR;
        hostCount = Math.pow(2, hostBits)-2;
        return hostCount;
    }

    public void showInfo() {
        System.out.printf(
            "Network: %s\n" +
            "Broadcast: %s\n" +
            "Host Min: %s\n" +
            "Host Max: %s\n" +
            "Host Count: %s\n",
            network, broadcast, hostMin, hostMax, hostCount
        );
    }
}
