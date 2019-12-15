package in.aayushbest.wirelessattendance.biometric;

import android.annotation.TargetApi;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.widget.ImageView;

import in.aayushbest.wirelessattendance.DashboardActivity;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintUiHelper extends FingerprintManager.AuthenticationCallback {
    private static final long ERROR_TIMEOUT_MILLIS=1600;
    public static final long SUCCESS_DELAY_MILLIS=1300;

    private FingerprintManager mFingerprintManager;
    private ImageView mIcon;
    private DashboardActivity mDashboardActivity;
    public FingerprintUiHelper() {
        super();
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);

    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        super.onAuthenticationHelp(helpCode, helpString);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);

    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
    }

    public boolean isMarshmallowRunning(){
        return Build.VERSION.SDK_INT>-Build.VERSION_CODES.M;
    }
}
