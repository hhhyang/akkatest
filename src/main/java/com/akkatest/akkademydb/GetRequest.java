
package com.akkatest.akkademydb;

import java.io.Serializable;

public class GetRequest implements Serializable {
    public final String key;

    public GetRequest(final String key) {
        this.key = key;
    }

}
