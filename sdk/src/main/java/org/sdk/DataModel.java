package org.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import org.sdk.util.ObjectUtil;

import java.util.Map;

public interface DataModel extends ResourceId{

    default Map<String, Object> toMap() {
        return ObjectUtil.convert(this);
    }

    @Override
    @JsonIgnore
    public default String getResourceId() {
        String modelString = ObjectUtil.convert(this).toString();
        HashCode hash = Hashing.goodFastHash(32).hashString(modelString, Charsets.UTF_8);
        return this.getClass().getName()+"@"+hash;
    }
}
