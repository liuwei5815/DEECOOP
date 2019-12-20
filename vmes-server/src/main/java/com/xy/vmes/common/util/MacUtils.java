package com.xy.vmes.common.util;

import org.apache.commons.lang.StringUtils;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by 46368 on 2018/12/19.
 */
public class MacUtils {

    public static void  getMac2(){
        try {

            Process process = Runtime.getRuntime().exec("ipconfig /all");

            InputStreamReader ir = new InputStreamReader(process.getInputStream(),"GBK");

            LineNumberReader input = new LineNumberReader(ir);

            String line;

            while ((line = input.readLine()) != null)
//            System.out.println(line);
            if(!StringUtils.isEmpty(line)){
                if (line.indexOf("物理地址") >= 0||line.indexOf("Physical Address") >= 0) {
//                    System.out.println(line);
                    String MACAddr = line.substring(line.indexOf("-") - 2);

                    System.out.println("MAC address = [" + MACAddr + "]");

                }
            }


        } catch (java.io.IOException e) {

            System.err.println("IOException " + e.getMessage());

        }
    }

//    private static String getMac2() throws SigarException {
//        Sigar sigar = new Sigar();
//        String[] ifaces = sigar.getNetInterfaceList();
//        for (String iface : ifaces) {
//            NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(iface);
//            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
//                    || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
//                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
//                continue;
//            }
//            String mac = cfg.getHwaddr();
//            return mac;
//        }
//        return null;
//
//    }


    public static String getMac() {
        try {
            Enumeration<NetworkInterface> el = NetworkInterface
                    .getNetworkInterfaces();
            while (el.hasMoreElements()) {
                byte[] mac = el.nextElement().getHardwareAddress();
                if (mac == null)
                    continue;

                String hexstr = bytesToHexString(mac);
                String MACAddr = getSplitString(hexstr, "-", 2).toUpperCase();
                System.out.println("MAC address2 = [" + MACAddr + "]");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }


    public static String getSplitString(String str, String split, int length) {
        int len = str.length();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (i % length == 0 && i > 0) {
                temp.append(split);
            }
            temp.append(str.charAt(i));
        }
        String[] attrs = temp.toString().split(split);
        StringBuilder finalMachineCode = new StringBuilder();
        for (String attr : attrs) {
            if (attr.length() == length) {
                finalMachineCode.append(attr).append(split);
            }
        }
        String result = finalMachineCode.toString().substring(0,
                finalMachineCode.toString().length() - 1);
        return result;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {

        //****************获取MAC地址*****************//
        System.out.println("***MAC地址***");
        getMac2();
        String mac = getMac();
        System.out.println(mac);
    }

}
