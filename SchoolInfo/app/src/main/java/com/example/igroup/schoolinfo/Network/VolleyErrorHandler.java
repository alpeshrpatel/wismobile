package com.example.igroup.schoolinfo.Network;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.igroup.schoolinfo.Extras.Constants;
import com.example.igroup.schoolinfo.R;


public class VolleyErrorHandler {

    public Context context;

    public VolleyErrorHandler(Context context) {
        this.context = context;
    }

    public String handleVolleyError(VolleyError error) {


        String errorMessage = Constants.NA;
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            errorMessage = context.getResources().getString(R.string.error_timeout);
        } else if (error instanceof AuthFailureError) {
            errorMessage = context.getResources().getString(R.string.auth_fail);
        } else if (error instanceof NetworkError) {
            errorMessage = context.getResources().getString(R.string.network_error);
        } else if (error instanceof ParseError) {
            errorMessage = context.getResources().getString(R.string.parse_error);
        } else if (error.networkResponse.statusCode == 404) {
            errorMessage = context.getResources().getString(R.string.error_404);
        } else {
            errorMessage = context.getResources().getString(R.string.error_universal);
        }

        return errorMessage;
    }
}
