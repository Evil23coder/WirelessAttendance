package in.aayushbest.wirelessattendance.helper;

import android.provider.BaseColumns;

public final class StaffContract {
    private StaffContract(){}

    public static class StaffEntry implements BaseColumns{
        public static final String TABLE_NAME="staff";
        public static final String _ID="hardware_id";
        public static final String COLUMN_NAME_NAME="name";
        public static final String COLUMN_NAME_DESIGNATION="designation";
        public static final String COLUMN_NAME_DEPARTMENT="department";
        public static final String COLUMN_NAME_COLLEGE="college";
        public static final String COLUMN_NAME_MOBILE="mobile";
        public static final String COLUMN_NAME_AUTH_METHOD="auth_method";

    }
}
