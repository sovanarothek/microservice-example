package com.example.common.base.util;

import com.example.common.base.dto.PageData;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;

public class ApiResult<T> {
    private int status;
    private String message;
    private T data;

    public ApiResult() {
    }

    public ApiResult(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResult(int status, String message, List<T> data) {
        this.status = status;
        this.message = message;
        HashMap<String, List<T>> dataObj = new HashMap<>();
        dataObj.put("data", data);
        this.data = (T) dataObj;
    }

    public ApiResult(int status) {
        this.status = status;
    }

    public ApiResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ApiResult<Object> successPage(List list, Long count) {
        return successPage(list, count, null);
    }

    public static ApiResult<Object> successPage(List list, Long count, HashMap<String, Object> sumMap) {
        ApiResult<Object> apiResult = new ApiResult<>();
        PageData pageData = PageData.data(list, count);
        pageData.setSumMap(sumMap);
        apiResult.data = pageData;
        apiResult.status = HttpStatus.OK.value();
        apiResult.message = "Success";
        return apiResult;
    }

    public static ApiResult<Object> successPage(List list, Integer count) {
        return successPage(list, count, null);
    }

    public static ApiResult<Object> successPage(List list, Integer count, HashMap<String, Object> sumMap) {
        ApiResult<Object> apiResult = new ApiResult();
        PageData pageData = PageData.data(list, count);
        pageData.setSumMap(sumMap);
        apiResult.data = pageData;
        apiResult.status = HttpStatus.OK.value();
        apiResult.message = "Success";
        return apiResult;
    }

    public static ApiResult<Object> success() {
        return new ApiResult<>(HttpStatus.OK.value(), "Success");
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(HttpStatus.OK.value(), "Success", data);
    }

    public static ApiResult<Object> failNotFound(String message) {
        return new ApiResult<>(HttpStatus.NOT_FOUND.value(), message);
    }

    public static ApiResult<Object> badRequest(String message) {
        return new ApiResult<>(HttpStatus.BAD_REQUEST.value(), message);
    }

    public static ApiResult<Object> error(int i, String message) {
        return new ApiResult<>(i, message);
    }
}
