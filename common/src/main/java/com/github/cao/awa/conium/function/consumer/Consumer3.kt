package com.github.cao.awa.conium.function.consumer

import org.apache.logging.log4j.util.TriConsumer

@FunctionalInterface
fun interface Consumer3<I1, I2, I3> : TriConsumer<I1, I2, I3>
