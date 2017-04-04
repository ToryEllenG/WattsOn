package com.gamboa.troy.WattsOn;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 4/2/17.
 */

public class EditUserDataRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://54.152.50.236/TestUpdate.php";
    private Map<String, String> params;

    public EditUserDataRequest(String username, String first_name, String last_name, String email, String phone_number, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("first_name", first_name);
        params.put("last_name", last_name);
        params.put("email", email);
        params.put("phone_number", phone_number);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
