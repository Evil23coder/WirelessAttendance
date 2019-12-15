package in.aayushbest.wirelessattendance.helper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class NetOperationSingleton {
    private RequestQueue mRequestQueue;
    private static NetOperationSingleton instance;
    private static Context mCtx;

    private NetOperationSingleton(Context context){
        mCtx=context;
        mRequestQueue=getRequestQueue();
    }

    public static synchronized NetOperationSingleton getInstance(Context context){
        if(instance==null){
            instance=new NetOperationSingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if(mRequestQueue==null){
            mRequestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
