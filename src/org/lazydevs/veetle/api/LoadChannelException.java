package org.lazydevs.veetle.api;

/**
 * Created by IntelliJ IDEA.
 * User: Sascha
 * Date: 01.06.11
 * Time: 23:24
 * To change this template use File | Settings | File Templates.
 */
public class LoadChannelException extends Exception{

    public LoadChannelException() {
    }

    public LoadChannelException(String message) {
        super(message);
    }

    public LoadChannelException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadChannelException(Throwable cause) {
        super(cause);
    }
}
