package org.lazydevs.veetle.api;

/**
 * Created by IntelliJ IDEA.
 * User: sascha
 * Date: 08.01.12
 * Time: 12:52
 * To change this template use File | Settings | File Templates.
 */
public class ParseChannelException extends Exception {
    public ParseChannelException() {
    }

    public ParseChannelException(String s) {
        super(s);
    }

    public ParseChannelException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ParseChannelException(Throwable throwable) {
        super(throwable);
    }
}
