package com.github.cao.awa.conium.hitokoto

import com.github.cao.awa.sinuatum.util.collection.CollectionFactor

/**
 * Provide a list of sentences, can randomly pick once or take all strings.
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
class ConiumHitokoto {
    companion object {
        private val hitokotos: MutableList<String> = mutableListOf(
            "新世代を呼ぶハロー、声はまだ途切れないだろう、",  // By 'Yunomi' in the music: ハローニュージェネレーション
            "新世界の扉を、ほら叩き続けよう、",             // By 'Yunomi' in the music: ハローニュージェネレーション
            "バイバイまたねの次は、何か残るのか いつか？"     // By 'ぽわぽわP' in the music: シティライツ
        )

        /**
         * Roll a sentence.
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        fun roll(): String = this.hitokotos.random()

        /**
         * Clone the hitokotos list.
         *
         * @return the clone list of hitokotos.
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        fun hitokotos(): MutableList<String> = CollectionFactor.arrayList(this.hitokotos)
    }
}
