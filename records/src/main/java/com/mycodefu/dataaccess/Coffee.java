package com.mycodefu.dataaccess;

/**
 * A coffee bean.
 *
 * @param name The name of the bean.
 * @param stength The strength (0-10) of the bean.
 */
public record Coffee(
        String name,
        int stength
) { }
