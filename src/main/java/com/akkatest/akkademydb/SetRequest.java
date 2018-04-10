
package com.akkatest.akkademydb;

import java.io.Serializable;

public class SetRequest implements Serializable {

    public final String key;
    public final Object value;

    public SetRequest(final String key, final Object value) {
        this.key = key;
        this.value = value;
    }


}
