package com.gamboa.troy.WattsOn;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Troygbv on 2/3/2017.
 */

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://54.152.50.236/Register.php"; //Replace with API endpoint
    private Map<String, String> params;

    public RegisterRequest(String first_name, String last_name, String email, String username, String password, String phone_number, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("first_name", first_name);
        params.put("last_name", last_name);
        params.put("email", email);
        params.put("username", username);
        params.put("password", password);
        params.put("phone_number", phone_number);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
