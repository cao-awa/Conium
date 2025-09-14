package com.github.cao.awa.conium.parameter.dynamic.lifecycle

enum class DynamicArgsLifecycle {
    /**
     * The once contract mean the dynamic args should invalidate after once arising.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    ONCE,

    /**
     * The transform contract mean the dynamic args should only arise in args transforming.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    TRANSFORM,

    /**
     * The forever contract mean the dynamic args will always reusable until the environment reset.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    FOREVER,

    /**
     * The unnamed contract mean the dynamic args should only arise in something attaches or additional arising.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    UNNAMED;
}