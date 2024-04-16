package com.example.transaction_service.dto.body;

import lombok.Data;

import java.util.Map;

@Data
public class Exchange {

    private String result;
    private String documentation;
    private String terms_of_use;
    private long time_last_update_unix;
    private String time_last_update_utc;
    private long time_next_update_unix;
    private String time_next_update_utc;
    private String base_code;
    private Map<String, Double> conversion_rates;
}
