package com.comm.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * ****************************************************************
 * 文件名称 : QBDataModel.java
 * 作    者       : xiaojie.huang
 * 创建时间 : 2014-9-17 下午4:24:20
 * 文件描述 : 重写Request
 * 版权声明 : Copyright © 2014 江苏钱旺智能系统有限公司
 * 修改历史 : 2014-9-17  1.00 初始版本
 * ****************************************************************
 */
public class Result {
    // 服务器返回成功
    public static final int RESPONSE_CODE_SCUESS = 1000;

    // 接口需要版本强制更新
    public static final int RESPONSE_CODE_NEEDFORCE_UPDATE = 1001;

    // 服务器在维护中
    public static final int RESPONSE_CODE_SERVICE_MAINTENANCE = 1002;

    // 有新版本接口可以使用，但不用强制更新
    public static final int RESPONSE_CODE_HAS_NEW_VERSION = 1003;

    // sesson超时
    public static final int RESPONSE_CODE_SESSION_TIMOUT = 1004;

    // 有错误
    public static final int RESPONSE_CODE_HAS_ERROR = 1005;

    // 数据不存在
    public static final int RESPONSE_CODE_DATA_NOT_FOUND = 1006;

    public static final String RESPONSE_MSG_HAS_ERROR = "系统异常";
    /**
     * 服务器返回的json数据响应码
     */
    private int responseCode = 0;

    /**
     * 成功的信息
     */
    private String errorMsg = null;

    private Object data;

    public Result() {
    }

    public Result(int responseCode, String errorMsg, Object data) {
        this.responseCode = responseCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    /**
     * 存在错误
     *
     * @param msg
     * @param errorCode
     * @return
     */
    public static Result error(String msg) {
        Result result = new Result(Result.RESPONSE_CODE_HAS_ERROR, msg, null);
        return result;
    }

    /**
     * 存在错误
     *
     * @param msg
     * @param errorCode
     * @return
     */
    public static Result error(String msg, int errorCode) {
        Result result = new Result(errorCode, msg, null);
        return result;
    }

    /**
     * 常用的成功请求
     *
     * @param data
     * @return
     */
    public static Result success(Object data) {
        Result result = new Result(Result.RESPONSE_CODE_SCUESS, "", data);
        return result;
    }

    /**
     * 常用的成功请求
     *
     * @param data
     * @return
     */
    public static Result success(String msg, Object data) {
        Result result = new Result(Result.RESPONSE_CODE_SCUESS, msg, data);
        return result;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }
}
