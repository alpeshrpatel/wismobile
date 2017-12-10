package com.example.igroup.schoolinfo.Network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by iGroup on 8/21/2017.
 */

public class VolleySingleton {

    private static VolleySingleton mInstance;
    private RequestQueue requestQueue;
    private Context ctx;
    private VolleySingleton(Context context)
    {
        ctx = context;
        requestQueue = getRequestQueue();
    }

public RequestQueue getRequestQueue()
{
    if (requestQueue == null)
    {
        requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
    }
    return requestQueue;
}
public static synchronized VolleySingleton getmInstance(Context context)
{
    if(mInstance == null)
    {
        mInstance = new VolleySingleton(context);

    }
    return  mInstance;

}
public<T>  void addToRequestque(Request<T> request)
{
    requestQueue.add(request);
}
}
