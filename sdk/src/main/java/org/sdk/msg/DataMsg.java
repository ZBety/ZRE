package org.sdk.msg;

import lombok.Data;
import org.sdk.util.ObjectUtil;

import java.util.HashMap;

@Data
public class DataMsg {

    Object data;

    HashMap<String, String> labels;

    HashMap<String, String> header;

    public <T> T dataAs(Class<T> clazz) {
        if (clazz.isInstance(data)) {
            return (T) data;
        }
        return ObjectUtil.convert(data, clazz);
    }

    public HashMap<String, Object> dataAsMap() {
        if (data.getClass().isPrimitive() || data instanceof CharSequence) {
            return new HashMap<String, Object>() {{
                put("data", data);
            }};
        }
        return dataAs(HashMap.class);
    }

    public DataMsg(Object data) {
        this.data = data;
    }
}
