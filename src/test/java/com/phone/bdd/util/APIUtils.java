package com.phone.bdd.util;

import com.phone.bdd.service.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class APIUtils {

    @Autowired
    private APIService apiService;

    public void postPhone(Map<String, Object> phoneData) {
        apiService.postPhone(phoneData);
    }

    public void deletePhone(String id) {
        apiService.deletePhone(id);
    }

    public void updatePhone(String id, Map<String, Object> phoneData) {
        apiService.updatePhone(id, phoneData);
    }
}
