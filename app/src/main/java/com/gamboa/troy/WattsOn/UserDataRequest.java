package com.gamboa.troy.WattsOn;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 3/30/17.
 */

public class UserDataRequest extends StringRequest{
    private static final String LOGIN_REQUEST_URL = "http://54.152.50.236/ModifyData.php";
    private Map<String, String> params;

    public UserDataRequest(String username,  Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
