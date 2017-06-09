package redpig.utility.network;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Kim YoungHun on 2017-05-29.
 */

public class MagicPacket {
    private static final String TAG = "MagicPacket";

    public interface OnMagicPacketCallbackListener{
        void onSuccessSendingMagicPacket();
        void onFailedSendingMagicPacket(Exception exception);
    }

    public static final int PORT = 9;    //  UDP 포트
    /**
     * 해당 IP와 MAC address로 MagicPacket를 전송한다.
     * 성공 실패는 boolean으로 리턴한다.
     * @param ipAddress 대상 IP
     * @param macAddress 대상 MAC address
     * @return
     */
    public static void sendMagicPacket(final String ipAddress, String macAddress, final OnMagicPacketCallbackListener listener){
        final String macStr = macAddress;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] macBytes = getMacBytes(macStr);
                    byte[] bytes = new byte[6 + 16 * macBytes.length];

                    for (int i = 0; i < 6; i++) {
                        bytes[i] = (byte) 0xff;
                    }
                    for (int i = 6; i < bytes.length; i += macBytes.length) {
                        System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
                    }

                    InetAddress address = InetAddress.getByName(ipAddress);
                    DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
                    DatagramSocket socket = new DatagramSocket();
                    socket.send(packet);
                    socket.close();
                    Log.d(TAG, "Magic Packet send Success...");
                    listener.onSuccessSendingMagicPacket();
                }
                catch (Exception e) {
                    Log.d(TAG, "Magic Packet send Failed... " + e.toString());
                    listener.onFailedSendingMagicPacket(e);
                }
            }
        }).start();
    }

    private static byte[] getMacBytes(String macStr) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        String[] hex = macStr.split("(\\:|\\-)");

        if (hex.length != 6) {
            Log.d(TAG, "Invalid MAC address..");
            throw new IllegalArgumentException("Invalid MAC address.");
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        }
        catch (NumberFormatException e) {
            Log.d(TAG, "Invalid hex digit in MAC address..");
            throw new IllegalArgumentException("Invalid hex digit in MAC address.");
        }
        return bytes;
    }
}
