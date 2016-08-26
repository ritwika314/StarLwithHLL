package edu.illinois.mitra.lightpaint.utility;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * The static Common class is used to store StarL constants used for messaging, event handling, and GUI updates. A series of useful functions
 * are included as well.
 * @author Adam Zimmerman
 * @version 1.0
 */
public final class Utility {
	
	private Utility() {
	}
	
	public static String toTitleCase(String name) {
		String[] tokens = name.toLowerCase().split("\\s+");
		StringBuilder builder = new StringBuilder();

		for(String token : tokens) {
		    char capLetter = Character.toUpperCase(token.charAt(0));
			builder.append(capLetter);
			builder.append(token.substring(1));
			builder.append(" ");
		}
		builder.trimToSize();
		return builder.toString();
	}
	
	/**
	 * Convert an array of strings to an array of integers. Returns null if any array element doesn't contain a number
	 * @param parts an array integers in string form
	 * @return an array of integers parsed from the array of strings.
	 */
	public static int[] partsToInts(String[] parts) {
		int[] retval = new int[parts.length];
		for(int i = 0; i < parts.length; i++) {
			try {
				retval[i] = (int) Double.parseDouble(parts[i]);
			} catch(NumberFormatException e) {
				//Log.e(TAG, "Can't parse " + parts[i] + " as an integer!");
				return null;
			}
		}
		return retval;
	}
	
	/**
	 * @param a1
	 * @param a2
	 * @return The input parameter with the smallest magnitude
	 */
	public static int min_magitude(int a1, int a2) {
		if(Math.abs(a1) < Math.abs(a2)) {
			return a1;
		} else {
			return a2;
		}
	}
	
	/**
	 * Convert a series of integers to a string array
	 * @param pieces
	 * @return String representation of the input integers
	 */
	public static String[] intsToStrings(Integer ... pieces) {
		String[] retval = new String[pieces.length];
		for(int i = 0; i < pieces.length; i++) {
			retval[i] = pieces[i].toString();
		}
		return retval;
	}
	
	/**
	 * @param str The input string to process
	 * @param delimiter The delimiting string used to split the input string
	 * @return An array contining the integers parsed from the input string
	 */
	public static int[] partsToInts(String str, String delimiter) {
		String[] parts = str.split(delimiter);
		return partsToInts(parts);
	}
	
	// Common value manipulation and comparison functions
	/**
	 * @param val value to test 
	 * @param min minimum acceptable value
	 * @param max maximum acceptable value
	 * @return true if val is between min and max, false otherwise
	 */
	public static <T extends Comparable<T>> boolean inRange(T val, T min, T max) {
		if(val.compareTo(min) >= 0 && val.compareTo(max) <= 0) return true;
		return false;
	}
	
	/**
	 * @param val value to test
	 * @param max maximum acceptable value
	 * @return val if val is less than max, max otherwise
	 */
	public static <T extends Comparable<T>> T cap(T val, T max) {
		if(val.compareTo(max) < 0) {
			return val;
		} else {
			return max;
		}
	}
	
	/**
	 * @param val value to test
	 * @param min minimum acceptable value
	 * @param max maximum acceptable value
	 * @return the value of val, capped between min and max
	 */
	public static <T extends Comparable<T>> T  cap(T val, T min, T max) {
		if(val.compareTo(max) > 0) {
			return max;
		} else if(val.compareTo(min) < 0) {
			return min;
		} else {
			return val;
		}
	}
	
    public static InetAddress getLocalAddress()throws IOException {
		try {
		    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
		        NetworkInterface intf = en.nextElement();
		        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
		            InetAddress inetAddress = enumIpAddr.nextElement();
		            if (!inetAddress.isLoopbackAddress()) {
		            	return inetAddress;
		            }
		        }
		    }
		} catch (SocketException ex) {
		    //Log.e(TAG, ex.toString());
		}
		return null;
    }
    
	/**
	 * Converts a two byte array to an integer
	 * @param b a byte array of length 2
	 * @return an int representing the unsigned short
	 */
	public static final int unsignedShortToInt(byte[] b) 
	{
		if(b.length != 2){
			return -99;
		}
	    int i = 0;
	    i |= b[0] & 0xFF;
	    i <<= 8;
	    i |= b[1] & 0xFF;
	    return i;
	}
	
	public static final int signedShortToInt(byte[] b)
	{
		if(b.length != 2) {
			return -99;
		}
		int i = ((b[0] & 0xFF) << 8) | (b[1] & 0xFF);
//		i |= b[0];
//		i <<= 8;
//		i |= b[1];
		return i;
	}

	
	/**
	 * Converts an input value to an angle between -90 and 270 degrees (360 degree range) 
	 * @param angle the angle to be rectified
	 * @return a rectified angle value
	 */
	public static int angleWrap(int angle) {
		int retval = angle % 360;
		if(retval > 270) {
			retval = retval - 360;
		} if(retval < -90) {
			retval = retval + 360;
		}
		return retval;
	}
}
