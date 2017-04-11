package com.gamboa.troy.HomeEnergyAudit;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 4/6/17.
 */

public class ChangePassRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://54.152.50.236/phpAPI/changePass.php";
    private Map<String, String> params;

    public ChangePassRequest(String username, String oldPassword, String password, String newPassword, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("oldPassword", oldPassword);
        params.put("password", password);
        params.put("newPassword", newPassword);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}