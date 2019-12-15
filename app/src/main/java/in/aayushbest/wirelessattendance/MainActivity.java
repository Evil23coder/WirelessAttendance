package in.aayushbest.wirelessattendance;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.aayushbest.wirelessattendance.helper.AppConfig;
import in.aayushbest.wirelessattendance.helper.NetOperationSingleton;
import in.aayushbest.wirelessattendance.helper.SessionManager;
import in.aayushbest.wirelessattendance.helper.StaffDbHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =MainActivity.class.getSimpleName();
    private NetOperationSingleton mOperations;
    private EditText mMACAddressText;
    private Button mRegisterButton;
    private SessionManager mSessionManager;
    private StaffDbHelper mStaff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSessionManager=new SessionManager(getApplicationContext());
        if(mSessionManager.isRegistered()){
            Intent i=new Intent(this,DashboardActivity.class);
            startActivity(i);
        }
        mMACAddressText=(EditText)findViewById(R.id.mac_address);
        mRegisterButton=(Button)findViewById(R.id.button_register);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerYourself(v);
            }
        });
        WifiManager wifiManager=(WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiData= wifiManager.getConnectionInfo();
        String macAddress = wifiData.getMacAddress();
        mMACAddressText.setText(R.string.default_unique_id);
        mStaff=new StaffDbHelper(getApplicationContext());
        mOperations=NetOperationSingleton.getInstance(getApplicationContext());

    }

    public void registerYourself(View view) {
        StringRequest request=new StringRequest(Request.Method.POST, AppConfig.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    boolean error=jsonObject.getBoolean("error");
                    if(!error){
                        mSessionManager.setRegistered(true);
                        JSONObject member=jsonObject.getJSONObject("staff");
                        String hardwareId=member.getString("hardware_id");
                        String name=member.getString("name");
                        String designation=member.getString("designation");
                        String department=member.getString("department");
                        String college=member.getString("mobile");
                        String mobile=member.getString("mobile");
                        String authMethod=member.getString("auth_method");
                        mSessionManager.setAuthenticatedType(authMethod);
                        mStaff.addStaffDetails(hardwareId,name,designation,department,college,mobile,authMethod);
                        Intent i=new Intent(getApplicationContext(),DashboardActivity.class);
                        startActivity(i);
                    }else{
                        String errorMessage=jsonObject.getString("error_msg");
                        Toast.makeText(getApplicationContext(),getString(R.string.register_message)+errorMessage,Toast.LENGTH_SHORT).show();
                    }

                }catch (JSONException e){
                    Toast.makeText(getApplicationContext(),"Communication Error:"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"error occured");
                Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                params.put("hardware_id",mMACAddressText.getText().toString());
                return params;
            }
        };
        mOperations.addToRequestQueue(request);

    }
}
