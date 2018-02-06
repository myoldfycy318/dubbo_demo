package com.comm.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * ObjectsTranscoder
 *
 * @author Zhang ShanMin
 * @date 2016/4/6
 * @time 21:56
 */
public class ObjectsTranscoder<M extends Serializable> {

    public byte[] serialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] result = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            M m = (M) value;
            os.writeObject(m);
            os.close();
            bos.close();
            result = bos.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException("Non-serializable object", e);
        } finally {
            IOUtils.closeQuietly(os);
        }
        return result;
    }

    public  M deserialize(byte[] in) {
        M result = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (in != null) {
                bis = new ByteArrayInputStream(in);
                is = new ObjectInputStream(bis);
                result = (M) is.readObject();
                is.close();
                bis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }
        return result;
    }
}
