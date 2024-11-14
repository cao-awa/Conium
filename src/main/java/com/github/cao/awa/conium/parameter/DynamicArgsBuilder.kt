package com.github.cao.awa.conium.parameter

import com.github.cao.awa.conium.event.type.DynamicArgType

class DynamicArgsBuilder {
    companion object {
        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R> requires(): DynamicArgs<ParameterSelective1<R?, Any>, R?> {
            return DynamicArgs({ identity, _, p ->
                p.arise(
                    identity
                )
            })
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R> requires(resultWhenFailure: R): DynamicArgs<ParameterSelective1<R, Any>, R> {
            return DynamicArgs({ identity, _, p ->
                p.runCatching {
                    arise(
                        identity
                    )
                }.getOrNull() ?: resultWhenFailure
            })
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R> force(): DynamicArgs<ParameterSelective1<R, Any>, R> {
            return DynamicArgs({ identity, _, p ->
                p.arise(
                    identity
                )
            })
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1> requires(
            arg1: DynamicArgType<P1>
        ): DynamicArgs<ParameterSelective2<R?, Any, P1>, R?> {
            return DynamicArgs({ identity, args, p ->
                val p1 = args[arg1] as P1

                if (p1 == null) {
                    null
                } else {
                    p.arise(
                        identity,
                        p1
                    )
                }
            }, arg1)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1> requires(
            arg1: DynamicArgType<P1>,
            resultWhenFailure: R
        ): DynamicArgs<ParameterSelective2<R, Any, P1>, R> {
            return DynamicArgs({ identity, args, p ->
                val p1 = args[arg1] as P1

                if (p1 == null) {
                    resultWhenFailure
                } else {
                    p.arise(
                        identity,
                        p1
                    )
                }
            }, arg1)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1> force(
            arg1: DynamicArgType<P1>
        ): DynamicArgs<ParameterSelective2<R, Any, P1>, R> {
            return DynamicArgs({ identity, args, p ->
                p.arise(
                    identity,
                    args[arg1] as P1
                )
            }, arg1)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2> requires(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>
        ): DynamicArgs<ParameterSelective3<R?, Any, P1, P2>, R?> {
            return DynamicArgs({ identity, args, p ->
                val p1 = args[arg1] as P1
                val p2 = args[arg2] as P2

                if (p1 == null || p2 == null) {
                    null
                } else {
                    p.arise(
                        identity,
                        p1,
                        p2
                    )
                }
            }, arg1, arg2)
        }

        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2> requires(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            resultWhenFailure: R
        ): DynamicArgs<ParameterSelective3<R, Any, P1, P2>, R> {
            return DynamicArgs({ identity, args, p ->
                val p1 = args[arg1] as P1
                val p2 = args[arg2] as P2

                if (p1 == null || p2 == null) {
                    resultWhenFailure
                } else {
                    p.arise(
                        identity,
                        p1,
                        p2
                    )
                }
            }, arg1, arg2)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2> force(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>
        ): DynamicArgs<ParameterSelective3<R, Any, P1, P2>, R> {
            return DynamicArgs({ identity, args, p ->
                p.arise(
                    identity,
                    args[arg1] as P1,
                    args[arg2] as P2
                )
            }, arg1, arg2)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3> requires(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>
        ): DynamicArgs<ParameterSelective4<R?, Any, P1, P2, P3>, R?> {
            return DynamicArgs({ identity, args, p ->
                val p1 = args[arg1] as P1
                val p2 = args[arg2] as P2
                val p3 = args[arg3] as P3

                if (p1 == null || p2 == null || p3 == null) {
                    null
                } else {
                    p.arise(
                        identity,
                        p1,
                        p2,
                        p3
                    )
                }
            }, arg1, arg2, arg3)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3> requires(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            resultWhenFailure: R
        ): DynamicArgs<ParameterSelective4<R, Any, P1, P2, P3>, R> {
            return DynamicArgs({ identity, args, p ->
                val p1 = args[arg1] as P1
                val p2 = args[arg2] as P2
                val p3 = args[arg3] as P3

                if (p1 == null || p2 == null || p3 == null) {
                    resultWhenFailure
                } else {
                    p.arise(
                        identity,
                        p1,
                        p2,
                        p3
                    )
                }
            }, arg1, arg2, arg3)
        }

        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3> force(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>
        ): DynamicArgs<ParameterSelective4<R, Any, P1, P2, P3>, R> {
            return DynamicArgs({ identity, args, p ->
                p.arise(
                    identity,
                    args[arg1] as P1,
                    args[arg2] as P2,
                    args[arg3] as P3
                )
            }, arg1, arg2, arg3)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4> requires(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>
        ): DynamicArgs<ParameterSelective5<R?, Any, P1, P2, P3, P4>, R?> {
            return DynamicArgs({ identity, args, p ->
                val p1 = args[arg1] as P1
                val p2 = args[arg2] as P2
                val p3 = args[arg3] as P3
                val p4 = args[arg4] as P4

                if (p1 == null || p2 == null || p3 == null || p4 == null) {
                    null
                } else {
                    p.arise(
                        identity,
                        p1,
                        p2,
                        p3,
                        p4
                    )
                }
            }, arg1, arg2, arg3, arg4)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4> requires(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>,
            resultWhenFailure: R
        ): DynamicArgs<ParameterSelective5<R, Any, P1, P2, P3, P4>, R> {
            return DynamicArgs({ identity, args, p ->
                val p1 = args[arg1] as P1
                val p2 = args[arg2] as P2
                val p3 = args[arg3] as P3
                val p4 = args[arg4] as P4

                if (p1 == null || p2 == null || p3 == null || p4 == null) {
                    resultWhenFailure
                } else {
                    p.arise(
                        identity,
                        p1,
                        p2,
                        p3,
                        p4
                    )
                }
            }, arg1, arg2, arg3, arg4)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4> force(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>
        ): DynamicArgs<ParameterSelective5<R, Any, P1, P2, P3, P4>, R> {
            return DynamicArgs({ identity, args, p ->
                p.arise(
                    identity,
                    args[arg1] as P1,
                    args[arg2] as P2,
                    args[arg3] as P3,
                    args[arg4] as P4
                )
            }, arg1, arg2, arg3, arg4)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4, P5> requires(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>,
            arg5: DynamicArgType<P5>
        ): DynamicArgs<ParameterSelective6<R?, Any, P1, P2, P3, P4, P5>, R?> {
            return DynamicArgs({ identity, args, p ->
                val p1 = args[arg1] as P1
                val p2 = args[arg2] as P2
                val p3 = args[arg3] as P3
                val p4 = args[arg4] as P4
                val p5 = args[arg5] as P5

                if (p1 == null || p2 == null || p3 == null || p4 == null || p5 == null) {
                    null
                } else {
                    p.arise(
                        identity,
                        p1,
                        p2,
                        p3,
                        p4,
                        p5
                    )
                }
            }, arg1, arg2, arg3, arg4, arg5)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4, P5> requires(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>,
            arg5: DynamicArgType<P5>,
            resultWhenFailure: R
        ): DynamicArgs<ParameterSelective6<R, Any, P1, P2, P3, P4, P5>, R> {
            return DynamicArgs({ identity, args, p ->
                val p1 = args[arg1] as P1
                val p2 = args[arg2] as P2
                val p3 = args[arg3] as P3
                val p4 = args[arg4] as P4
                val p5 = args[arg5] as P5

                if (p1 == null || p2 == null || p3 == null || p4 == null || p5 == null) {
                    resultWhenFailure
                } else {
                    p.arise(
                        identity,
                        p1,
                        p2,
                        p3,
                        p4,
                        p5
                    )
                }
            }, arg1, arg2, arg3, arg4, arg5)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4, P5> force(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>,
            arg5: DynamicArgType<P5>
        ): DynamicArgs<ParameterSelective6<R, Any, P1, P2, P3, P4, P5>, R> {
            return DynamicArgs({ identity, args, p ->
                p.arise(
                    identity,
                    args[arg1] as P1,
                    args[arg2] as P2,
                    args[arg3] as P3,
                    args[arg4] as P4,
                    args[arg5] as P5
                )
            }, arg1, arg2, arg3, arg4, arg5)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4, P5, P6> requires(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>,
            arg5: DynamicArgType<P5>,
            arg6: DynamicArgType<P6>
        ): DynamicArgs<ParameterSelective7<R?, Any, P1, P2, P3, P4, P5, P6>, R?> {
            return DynamicArgs({ identity, args, p ->
                val p1 = args[arg1] as P1
                val p2 = args[arg2] as P2
                val p3 = args[arg3] as P3
                val p4 = args[arg4] as P4
                val p5 = args[arg5] as P5
                val p6 = args[arg6] as P6

                if (p1 == null || p2 == null || p3 == null || p4 == null || p5 == null || p6 == null) {
                    null
                } else {
                    p.arise(
                        identity,
                        p1,
                        p2,
                        p3,
                        p4,
                        p5,
                        p6
                    )
                }
            }, arg1, arg2, arg3, arg4, arg5, arg6)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4, P5, P6> requires(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>,
            arg5: DynamicArgType<P5>,
            arg6: DynamicArgType<P6>,
            resultWhenFailure: R
        ): DynamicArgs<ParameterSelective7<R, Any, P1, P2, P3, P4, P5, P6>, R> {
            return DynamicArgs({ identity, args, p ->
                val p1 = args[arg1] as P1
                val p2 = args[arg2] as P2
                val p3 = args[arg3] as P3
                val p4 = args[arg4] as P4
                val p5 = args[arg5] as P5
                val p6 = args[arg6] as P6

                if (p1 == null || p2 == null || p3 == null || p4 == null || p5 == null || p6 == null) {
                    resultWhenFailure
                } else {
                    p.arise(
                        identity,
                        p1,
                        p2,
                        p3,
                        p4,
                        p5,
                        p6
                    )
                }
            }, arg1, arg2, arg3, arg4, arg5, arg6)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4, P5, P6> force(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>,
            arg5: DynamicArgType<P5>,
            arg6: DynamicArgType<P6>
        ): DynamicArgs<ParameterSelective7<R, Any, P1, P2, P3, P4, P5, P6>, R> {
            return DynamicArgs({ identity, args, p ->
                p.arise(
                    identity,
                    args[arg1] as P1,
                    args[arg2] as P2,
                    args[arg3] as P3,
                    args[arg4] as P4,
                    args[arg5] as P5,
                    args[arg6] as P6
                )
            }, arg1, arg2, arg3, arg4, arg5, arg6)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4, P5, P6, P7> requires(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>,
            arg5: DynamicArgType<P5>,
            arg6: DynamicArgType<P6>,
            arg7: DynamicArgType<P7>
        ): DynamicArgs<ParameterSelective8<R?, Any, P1, P2, P3, P4, P5, P6, P7>, R?> {
            return DynamicArgs({ identity, args, p ->
                val p1 = args[arg1] as P1
                val p2 = args[arg2] as P2
                val p3 = args[arg3] as P3
                val p4 = args[arg4] as P4
                val p5 = args[arg5] as P5
                val p6 = args[arg6] as P6
                val p7 = args[arg7] as P7

                if (p1 == null || p2 == null || p3 == null || p4 == null || p5 == null || p6 == null || p7 == null) {
                    null
                } else {
                    p.arise(
                        identity,
                        p1,
                        p2,
                        p3,
                        p4,
                        p5,
                        p6,
                        p7
                    )
                }
            }, arg1, arg2, arg3, arg4, arg5, arg6, arg7)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4, P5, P6, P7> requires(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>,
            arg5: DynamicArgType<P5>,
            arg6: DynamicArgType<P6>,
            arg7: DynamicArgType<P7>,
            resultWhenFailure: R
        ): DynamicArgs<ParameterSelective8<R, Any, P1, P2, P3, P4, P5, P6, P7>, R> {
            return DynamicArgs({ identity, args, p ->
                val p1 = args[arg1] as P1
                val p2 = args[arg2] as P2
                val p3 = args[arg3] as P3
                val p4 = args[arg4] as P4
                val p5 = args[arg5] as P5
                val p6 = args[arg6] as P6
                val p7 = args[arg7] as P7

                if (p1 == null || p2 == null || p3 == null || p4 == null || p5 == null || p6 == null || p7 == null) {
                    resultWhenFailure
                } else {
                    p.arise(
                        identity,
                        p1,
                        p2,
                        p3,
                        p4,
                        p5,
                        p6,
                        p7
                    )
                }
            }, arg1, arg2, arg3, arg4, arg5, arg6, arg7)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4, P5, P6, P7> force(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>,
            arg5: DynamicArgType<P5>,
            arg6: DynamicArgType<P6>,
            arg7: DynamicArgType<P7>
        ): DynamicArgs<ParameterSelective8<R, Any, P1, P2, P3, P4, P5, P6, P7>, R> {
            return DynamicArgs({ identity, args, p ->
                p.arise(
                    identity,
                    args[arg1] as P1,
                    args[arg2] as P2,
                    args[arg3] as P3,
                    args[arg4] as P4,
                    args[arg5] as P5,
                    args[arg6] as P6,
                    args[arg7] as P7
                )
            }, arg1, arg2, arg3, arg4, arg5, arg6, arg7)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R> transform(
            p: ParameterSelective1<R, Any>
        ): DynamicArgs<ParameterSelective1<R, Any>, R?> {
            return DynamicArgs<ParameterSelective1<R, Any>, R?>({ identity, args, _ ->
                p.arise(
                    identity
                )
            }).lifecycle(DynamicArgsLifecycle.TRANSFORM)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1> transform(
            arg1: DynamicArgType<P1>,
            p: ParameterSelective1<R, P1>
        ): DynamicArgs<ParameterSelective1<R, P1>, R?> {
            return DynamicArgs<ParameterSelective1<R, P1>, R?>({ _, args, _ ->
                p.arise(
                    args[arg1] as P1
                )
            }, arg1).lifecycle(DynamicArgsLifecycle.TRANSFORM)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1> transform(
            arg1: DynamicArgType<P1>,
            p: ParameterSelective2<R, Any, P1>
        ): DynamicArgs<ParameterSelective2<R, Any, P1>, R?> {
            return DynamicArgs<ParameterSelective2<R, Any, P1>, R?>({ identity, args, _ ->
                p.arise(
                    identity,
                    args[arg1] as P1
                )
            }, arg1).lifecycle(DynamicArgsLifecycle.TRANSFORM)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2> transform(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            p: ParameterSelective3<R, Any, P1, P2>
        ): DynamicArgs<ParameterSelective3<R, Any, P1, P2>, R?> {
            return DynamicArgs<ParameterSelective3<R, Any, P1, P2>, R?>({ identity, args, _ ->
                p.arise(
                    identity,
                    args[arg1] as P1,
                    args[arg2] as P2
                )
            }, arg1, arg2).lifecycle(DynamicArgsLifecycle.TRANSFORM)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3> transform(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            p: ParameterSelective4<R, Any, P1, P2, P3>
        ): DynamicArgs<ParameterSelective4<R, Any, P1, P2, P3>, R?> {
            return DynamicArgs<ParameterSelective4<R, Any, P1, P2, P3>, R?>({ identity, args, _ ->
                p.arise(
                    identity,
                    args[arg1] as P1,
                    args[arg2] as P2,
                    args[arg3] as P3
                )
            }, arg1, arg2, arg3).lifecycle(DynamicArgsLifecycle.TRANSFORM)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4> transform(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>,
            p: ParameterSelective5<R, Any, P1, P2, P3, P4>
        ): DynamicArgs<ParameterSelective5<R, Any, P1, P2, P3, P4>, R?> {
            return DynamicArgs<ParameterSelective5<R, Any, P1, P2, P3, P4>, R?>({ identity, args, _ ->
                p.arise(
                    identity,
                    args[arg1] as P1,
                    args[arg2] as P2,
                    args[arg3] as P3,
                    args[arg4] as P4
                )
            }, arg1, arg2, arg3, arg4).lifecycle(DynamicArgsLifecycle.TRANSFORM)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4, P5> transform(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>,
            arg5: DynamicArgType<P5>,
            p: ParameterSelective6<R, Any, P1, P2, P3, P4, P5>
        ): DynamicArgs<ParameterSelective6<R, Any, P1, P2, P3, P4, P5>, R?> {
            return DynamicArgs<ParameterSelective6<R, Any, P1, P2, P3, P4, P5>, R?>({ identity, args, _ ->
                p.arise(
                    identity,
                    args[arg1] as P1,
                    args[arg2] as P2,
                    args[arg3] as P3,
                    args[arg4] as P4,
                    args[arg5] as P5
                )
            }, arg1, arg2, arg3, arg4, arg5).lifecycle(DynamicArgsLifecycle.TRANSFORM)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4, P5, P6> transform(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>,
            arg5: DynamicArgType<P5>,
            arg6: DynamicArgType<P6>,
            p: ParameterSelective7<R, Any, P1, P2, P3, P4, P5, P6>
        ): DynamicArgs<ParameterSelective7<R, Any, P1, P2, P3, P4, P5, P6>, R?> {
            return DynamicArgs<ParameterSelective7<R, Any, P1, P2, P3, P4, P5, P6>, R?>({ identity, args, _ ->
                p.arise(
                    identity,
                    args[arg1] as P1,
                    args[arg2] as P2,
                    args[arg3] as P3,
                    args[arg4] as P4,
                    args[arg5] as P5,
                    args[arg6] as P6
                )
            }, arg1, arg2, arg3, arg4, arg5, arg6).lifecycle(DynamicArgsLifecycle.TRANSFORM)
        }

        @JvmStatic
        @SuppressWarnings("UNCHECKED_CAST")
        fun <R, P1, P2, P3, P4, P5, P6, P7> transform(
            arg1: DynamicArgType<P1>,
            arg2: DynamicArgType<P2>,
            arg3: DynamicArgType<P3>,
            arg4: DynamicArgType<P4>,
            arg5: DynamicArgType<P5>,
            arg6: DynamicArgType<P6>,
            arg7: DynamicArgType<P7>,
            p: ParameterSelective8<R, Any, P1, P2, P3, P4, P5, P6, P7>
        ): DynamicArgs<ParameterSelective8<R, Any, P1, P2, P3, P4, P5, P6, P7>, R?> {
            return DynamicArgs<ParameterSelective8<R, Any, P1, P2, P3, P4, P5, P6, P7>, R?>(
                { identity, args, _ ->
                    p.arise(
                        identity,
                        args[arg1] as P1,
                        args[arg2] as P2,
                        args[arg3] as P3,
                        args[arg4] as P4,
                        args[arg5] as P5,
                        args[arg6] as P6,
                        args[arg7] as P7
                    )
                },
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                arg6,
                arg7
            ).lifecycle(DynamicArgsLifecycle.TRANSFORM)
        }
    }
}
