package org.lazydevs.veetle.api;

/**
 * Created by IntelliJ IDEA.
 * User: Sascha
 * Date: 28.05.11
 * Time: 12:50
 * To change this template use File | Settings | File Templates.
 */
public class LoadChannelListException extends Exception{

    public LoadChannelListException() {
    }

    public LoadChannelListException(String message) {
        super(message);
    }

    public LoadChannelListException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadChannelListException(Throwable cause) {
        super(cause);
    }
}
