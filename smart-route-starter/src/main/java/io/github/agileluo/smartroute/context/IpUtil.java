package io.github.agileluo.smartroute.context;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class IpUtil {
	private static Set<String> localIps = new HashSet<>();
	static{
		try {
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
			while(e.hasMoreElements())
			{
			    NetworkInterface n = (NetworkInterface) e.nextElement();
			    Enumeration<InetAddress> ee = n.getInetAddresses();
			    while (ee.hasMoreElements())
			    {
			        InetAddress i = ee.nextElement();
			        localIps.add(i.getHostAddress());
			    }
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static boolean isLocalIp(String ip){
		return localIps.contains(ip);
	}
	//是否都是本地ip
	public static boolean isAllLocalIp(String ip1, String ip2){
		return localIps.contains(ip1) && localIps.contains(ip2);
	}
}
