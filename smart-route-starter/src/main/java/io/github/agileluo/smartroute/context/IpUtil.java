package io.github.agileluo.smartroute.context;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IpUtil {
	private static Set<String> localIps = new HashSet<>();
	private static String localRemoteIp;
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
			List<String> localRemoteIps = new ArrayList<>();
			for(String ip : localIps){
				if(ip.matches("^(\\d{1,3}.){3}\\d{1,3}") && !ip.startsWith("127.")){
					localRemoteIps.add(ip);
				}
			}
			for(String ip : localRemoteIps){
				if(!ip.startsWith("192.168")){
					localRemoteIp = ip;
				}
			}
			if(localRemoteIp == null){
				localRemoteIp = localRemoteIps.get(0);
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
	/**
	 * 找出本地对外ip
	 * @return
	 */
	public static String getLocalRemoteIp(){
		return localRemoteIp;
	}
}
