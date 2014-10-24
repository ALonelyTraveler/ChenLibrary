
package com.android.chen.lib.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.apache.http.conn.util.InetAddressUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

/*
 * This class is used to manage state of network. Add permission before use this
 * class. <uses-permission
 * android:name="android.permission.ACCESS_NETWORK_STATE" /> <uses-permission
 * android:name="android.permission.ACCESS_WIFI_STATE" />
 */

/**
 * @author WinChen
 * @date 2014-4-2 上午9:54:50
 * @version
 */
public class NetworkMonitor
{

	public final static int NONE_CONNECT_STATE = 0;

	public final static int WIFI_CONNECT_STATE = 1;

	public final static int NETWORK_CONNECT_STATE = 2;

	public final static int WIFI_NETWORK_CONNECT_STATE = 3;

	/**
	 * To determine whether the wifi is connected.
	 * 
	 * @return
	 */
	public static boolean wifiConnect(Context context)
	{

		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo[] = connectivityManager.getAllNetworkInfo();
		int length = networkInfo.length;
		int i = 0;
		for (i = 0; i < length; i++)
		{
			if (networkInfo[i].getTypeName().equals("WIFI") && networkInfo[i]
					.isConnected())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * To determine whether the network is connected.
	 * 
	 * @return
	 */
	public static boolean networkConnect(Context context)
	{

		boolean flag = false;
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		State network_state = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
		if (network_state == State.CONNECTED || network_state == State.CONNECTING)
		{
			flag = true;
		}
		return flag;
	}

	// 检查网络是否已连接
	public static boolean checkNetworkInfo(Context context)
	{

		ConnectivityManager conMan = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		// mobile 3G Data Network
		State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();

		// wifi
		State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();

		boolean isConnected = false;

		// 如果3G网络和wifi网络都连接
		if (mobile == State.CONNECTED || mobile == State.CONNECTING || wifi == State.CONNECTING || wifi == State.CONNECTED)
		{
			isConnected = true;
		}
		return isConnected;
	}

	/**
	 * To determine whether there are available network.
	 * 
	 * @return
	 */
	public static boolean effectivityConnect(Context context)
	{

		return wifiConnect(context) || networkConnect(context);
	}

	/**
	 * To get state of current network.
	 * 
	 * @return
	 */
	public static int getNetworkState(Context context)
	{

		int state = 0;
		if (wifiConnect(context))
		{
			if (networkConnect(context))
			{
				state = WIFI_NETWORK_CONNECT_STATE;
			} else
			{
				state = WIFI_CONNECT_STATE;
			}
		} else
		{
			if (networkConnect(context))
			{
				state = NETWORK_CONNECT_STATE;
			} else
			{
				state = NONE_CONNECT_STATE;
			}
		}
		return state;
	}

	/**
	 * To get mac address.
	 * 
	 * @param context
	 * @return
	 */
	public static String getMacAddress(Context context)
	{

		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		String address = "";
		if (info != null)
		{
			address = info.getMacAddress();
		}
		return address == null ? "" : address;
	}

	/**
	 * To get real ip address.
	 * 
	 * @return
	 * @throws SocketException
	 */
	public static String getRealIp() throws SocketException
	{

		String localip = null;// 当地的Ip地址
		String netip = null;// 网络ip地址

		Enumeration<NetworkInterface> netInterfaces = NetworkInterface
				.getNetworkInterfaces();
		InetAddress ip = null;
		boolean finded = false;//
		while (netInterfaces.hasMoreElements() && !finded)
		{
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements())
			{
				ip = address.nextElement();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip
						.getHostAddress().indexOf(":") == -1)
				{
					netip = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip
						.getHostAddress().indexOf(":") == -1)
				{
					localip = ip.getHostAddress();
				}
			}
		}

		if (netip != null && !"".equals(netip))
		{
			return netip;
		} else
		{
			return localip;
		}
	}

	/**
	 * To get local ip address.
	 * 
	 * @return
	 */
	public static String getLocalIpAddress()
	{

		try
		{
			String ipv4;
			List<NetworkInterface> nilist = Collections.list(NetworkInterface
					.getNetworkInterfaces());
			for (NetworkInterface ni : nilist)
			{
				List<InetAddress> ialist = Collections.list(ni
						.getInetAddresses());
				for (InetAddress address : ialist)
				{
					if (!address.isLoopbackAddress() && InetAddressUtils
							.isIPv4Address(ipv4 = address.getHostAddress()))
					{
						return ipv4;
					}
				}

			}

		} catch (SocketException ex)
		{
			Log.e("d", ex.toString());
		}
		return "";
	}
}
