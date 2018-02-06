package com.lv.converter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.comm.util.DESUtil;
import com.comm.util.Result;
import com.fasterxml.jackson.databind.JavaType;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenwei on 2016/5/13
 */
public class JsonAndDESMessageConverter extends MappingJackson2HttpMessageConverter {

    private final Logger log = LoggerFactory.getLogger(JsonAndDESMessageConverter.class);

    private String desKey;

    private final List<Charset> availableCharsets;

    private final Charset defaultCharset = Charset.forName("UTF-8");

    private static SerializerFeature[] features = {SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty};

    public JsonAndDESMessageConverter() {
        this.availableCharsets = new ArrayList();
        this.availableCharsets.add(defaultCharset);
    }

    @Override
    protected Object readInternal(Class<?> clazz,
                                  HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        JavaType javaType = getJavaType(clazz, null);
        return readJavaType(javaType, inputMessage);
    }

    @Override
    public Object read(Type type, Class<?> contextClass,
                       HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        JavaType javaType = getJavaType(type, contextClass);
        return readJavaType(javaType, inputMessage);
    }

    private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage) {
        try {

            String message = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
            log.debug("接受参数：{}", message);
            String decryptStr = null;
            decryptStr = DESUtil.decrypt(message, getDesKey());
            String repStr = decryptStr;
            try {
                String regex = "(.*\"password\":\")[\\w\\d]*(\"[,\\}]{1}\"?.*)";
                repStr = repStr.replaceAll(regex, "$1***$2");
            } catch (Exception e) {
                log.debug("参数替换失败");
            }
            log.debug("参数解密：{}", repStr);
            return this.objectMapper.readValue(decryptStr, javaType);
        } catch (IOException ex) {
            throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("解密错误");
            log.error(ExceptionUtils.getFullStackTrace(e));
            return null;
        }
    }

    @Override
    protected void writeInternal(Object object,
                                 HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getHeaders().setAcceptCharset(getAvailableCharsets());
        Charset charset = getContentTypeCharset(outputMessage.getHeaders().getContentType());

        if (object instanceof Result) {
            boolean needEncrypt = true;
            Result resultObj = (Result) object;
            if (resultObj.getData() == null) {
                resultObj.setData("");
                needEncrypt = false;
            }

            if (needEncrypt) {
                String encryptStr = null;
                String result = JSONObject.toJSONStringWithDateFormat(resultObj.getData(), "yyyy-MM-dd HH:mm:ss", features);
                try {
                    log.debug("返回结果：{}", result);
                    encryptStr = DESUtil.encrypt(result, getDesKey());
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error(ExceptionUtils.getFullStackTrace(e));
                    encryptStr = "加密错误";
                }
                resultObj.setData(encryptStr);
            }

            StreamUtils.copy(resultObj.toString(), charset, outputMessage.getBody());
        }

        if (object instanceof String) {
            String resultStr = (String) object;
            JSONObject jsonObject = JSONObject.parseObject(resultStr);
            String data = jsonObject.getString("data");
            try {
                log.debug("返回结果：{}", resultStr);
                if (null != data && data != "") {
                    String dataEncrypt = DESUtil.encrypt(data, getDesKey());
                    jsonObject.put("data", dataEncrypt);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error(ExceptionUtils.getFullStackTrace(e));
                resultStr = "加密错误";
            }
            StreamUtils.copy(jsonObject.toJSONString(), charset, outputMessage.getBody());
        }
    }

    private Charset getContentTypeCharset(MediaType contentType) {
        if (contentType != null && contentType.getCharSet() != null) {
            return contentType.getCharSet();
        } else {
            return this.defaultCharset;
        }
    }

    public void setDesKey(String desKey) {
        this.desKey = desKey;
    }

    public String getDesKey() {
        return desKey;
    }

    public List<Charset> getAvailableCharsets() {
        return availableCharsets;
    }

    public Charset getDefaultCharset() {
        return defaultCharset;
    }
}
