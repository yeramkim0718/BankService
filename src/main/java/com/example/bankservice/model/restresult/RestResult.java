
package com.example.bankservice.model.restresult;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResult {

    // error id
    private String id;

    // error message
    private String message;

    private Map<String, Object> data;

    public RestResult() { }

    public RestResult(Map<String, Object> data) {
        this.data = data;
    }

    public RestResult setData(Map<String, Object> data) {

        this.data = data;
        return this;
    }

    public RestResult addData (String key, Object value ) {
        this.data.put(key,value);
        return this;
    }

    public RestResult addPage (int totalPages, long totalElements, int pageNumber, int itemSize) {
        HashMap<String,Object> page = new HashMap<>();
        page.put("totalPages",totalPages);
        page.put("totalElements",totalElements);
        page.put("pageNumber",pageNumber);
        page.put("itemSize",itemSize);
        this.addData("page",page);
        return this;
    }


    public static RestResult success() {
        RestResult restResult = new RestResult();

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "ok");
        restResult.setData(map);

        return restResult;
    }

    public static RestResult notOk() {
        RestResult restResult = new RestResult();

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "notOk");
        restResult.setData(map);

        return restResult;
    }

    public static RestResult failure() {
        RestResult restResult = new RestResult();
        return restResult;
    }

}
