package in.aayushbest.wirelessattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Map;

import in.aayushbest.wirelessattendance.helper.StaffDbHelper;

public class DashboardActivity extends AppCompatActivity {
    private TextView mNameAndCollegeText;
    private TextView mCurrentDateAndTime;
    private StaffDbHelper mStaffDatabase;
    private String mName;
    private String mCollege;
    private String mDepartment;
    private String mDesignation;
    private String mMobile;
    private String mAuthMethod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mNameAndCollegeText=(TextView)findViewById(R.id.account_description_text);
        mCurrentDateAndTime=(TextView)findViewById(R.id.zoned_datetime);
        mStaffDatabase=new StaffDbHelper(getApplicationContext());
        Map<String,String> userData=mStaffDatabase.getStaffDetails();
        setName(userData.get("name"));
        setCollege(userData.get("college"));
        setDesignation(userData.get("designation"));
        setAuthMethod(userData.get("auth_method"));
        setMobile(userData.get("mobile"));
        setDepartment(userData.get("department"));
        String message=getDesignation()+"."+getName()+" at "+getCollege();
        mNameAndCollegeText.setText(message);
    }
    public String getCollege(){
        return mCollege;
    }
    public void setCollege(String college){
        mCollege=college;
    }
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDesignation() {
        return mDesignation;
    }

    public void setDesignation(String designation) {
        mDesignation = designation;
    }

    public String getAuthMethod() {
        return mAuthMethod;
    }

    public void setAuthMethod(String authMethod) {
        mAuthMethod = authMethod;
    }

    public String getMobile() {
        return mMobile;
    }

    public void setMobile(String mobile) {
        mMobile = mobile;
    }

    public String getDepartment() {
        return mDepartment;
    }

    public void setDepartment(String department) {
        mDepartment = department;
    }
}
