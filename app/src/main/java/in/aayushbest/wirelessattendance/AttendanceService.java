package in.aayushbest.wirelessattendance;

import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.List;


public class AttendanceService extends IntentService {
    private WifiManager mWifiManager;
    private final String MAC_ADDR="";
    private final String WPA_PSK="";
    private boolean mIsConnected=false;
    public AttendanceService(){
        super("Attendance Service");
    }
    /**
     * Get the list of all the available WiFi networks
     * Fetch the networks MAC address
     * Match with the hard-coded String value of MAC address
     * If it matches connect with the stored network-SSID and PIN
     * Connect with the remote database
     * Launch the Logged_In Dashboard Activity.
     * @author Aayush Shrivastava
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        final BroadcastReceiver mWifiBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                    List<ScanResult> avalibleWifi = mWifiManager.getScanResults();
                    for (ScanResult s : avalibleWifi) {
                        if (MAC_ADDR.equals(s.BSSID)) {
                            //TODO:Connect with the network
                            WifiConfiguration wifiConfig = new WifiConfiguration();
                            wifiConfig.SSID = s.SSID;
                            wifiConfig.preSharedKey = WPA_PSK;
                            mWifiManager.setWifiEnabled(true);
                            int networkId=mWifiManager.addNetwork(wifiConfig);
                            mWifiManager.enableNetwork(networkId,true);
                            mIsConnected=true;
                        }
                    }
                }
            }
        };
        if(mIsConnected){
            intent=new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            startActivity(intent);
        }
    }



}
