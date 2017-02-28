package com.gamboa.troy.WattsOn;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 2/17/17.
 */

public class HouseRegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://54.152.50.236/HouseRegister.php"; //Replace with API endpoint
    private Map<String, String> params;

    public HouseRegisterRequest(String street_address, String city, String state, String zipcode, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("street_address", street_address);
        params.put("city", city);
        params.put("state", state);
        params.put("zipcode", zipcode);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


