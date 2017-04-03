package com.gamboa.troy.WattsOn;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 4/2/17.
 */

public class EditUserDataRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://54.152.50.236/Login.php";
    private Map<String, String> params;

    public EditUserDataRequest(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
