
package com.akkatest.akkademydb;

import java.io.Serializable;

public class KeyNotFoundException extends Exception implements Serializable {

    public final String key;

    public KeyNotFoundException(final String key) {
        this.key = key;
    }

}
