package hello;

import java.io.IOException;

/**
 * @author changeworld
 */
public interface BaseClient {
    /**
     * Same as SET command in Redis
     *
     * @param key
     * @param value
     * @throws IOException
     */
    void set(String key, String value) throws IOException;

    /**
     * Same as GET command in Redis
     *
     * @param key
     * @throws IOException
     */
    String get(String key) throws IOException;

    /**
     * Same as DEL command in Redis
     *
     * @param key
     * @throws IOException
     */
    void del(String key) throws IOException;
}