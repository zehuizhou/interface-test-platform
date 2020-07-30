package com.rabbit.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

/**
 * Created by jiangyitao.
 */
@Slf4j
public class NetUtil {

    private static String ip = null;

    private static String ipv4Ip = null;

    /**
     * 检测本地端口是否可用
     *
     * @param port 端口号
     * @return
     */
    public static boolean isPortAvailable(int port) {
        try {
            bindPort("127.0.0.1", port);
            bindPort("0.0.0.0", port);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static void bindPort(String ip, int port) throws IOException {
        try (Socket socket = new Socket()) {
            socket.bind(new InetSocketAddress(ip, port));
        }
    }

    /**
     * 获取本机ip
     *
     * @return
     */
    public static String getIp() throws SocketException {
        if(ip != null) {
            return ip;
        }
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                if (!inetAddress.isLinkLocalAddress() && !inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                    ip = inetAddress.getHostAddress();
                    return ip;
                }
            }
        }
        return ip;
    }

    public static String getLocalIpv4Address() throws SocketException {
        if(ipv4Ip != null) {
            return ipv4Ip;
        }
        Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
        String siteLocalAddress = null;
        while (ifaces.hasMoreElements()) {
            NetworkInterface iface = ifaces.nextElement();
            Enumeration<InetAddress> addresses = iface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                String hostAddress = addr.getHostAddress();
                if (addr instanceof Inet4Address
                        && !addr.isLoopbackAddress()
//                        && !hostAddress.startsWith("192.168")
//                        && !hostAddress.startsWith("172.")
                        && !hostAddress.startsWith("127.0.0.1")
                        && !hostAddress.startsWith("169.")) {
                    if (addr.isSiteLocalAddress()) {
                        siteLocalAddress = hostAddress;
                    } else {
                        return hostAddress;
                    }
                }
            }
        }
        ipv4Ip = siteLocalAddress == null ? "" : siteLocalAddress;
        return ipv4Ip;
    }
}
