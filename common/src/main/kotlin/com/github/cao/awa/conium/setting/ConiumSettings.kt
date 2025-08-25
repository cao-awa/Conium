package com.github.cao.awa.conium.setting

import com.github.cao.awa.conium.kotlin.extend.manipulate.doCast
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor

abstract class ConiumSettings<T : ConiumSettings<T, M>, M : T> {
    private val migrates: MutableMap<String, M> = CollectionFactor.hashMap()

    /**
     * Migrate settings to new settings instance.
     *
     * Do attention when calls, 'migrate' will always create a new instance for every get.
     *
     * @see migrateTo
     * @see compute
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    // Self migrate, do not call 'migrateTo'.
    val migrate: M get() = migrateTo(newInstance())

    fun hasMigrate(name: String): Boolean = this.migrates.containsKey(name)

    abstract fun newInstance(): M

    abstract fun migrateTo(settings: M): M

    fun migrate(name: String): M {
        // Migrate this settings to new migrating instance.
        return this.migrate.also {
            // Migrate target group settings to result.
            this.migrates[name]?.migrateTo(it)
        }
    }

    open fun migrate(name: String, settings: M) {
        this.migrates[name] = settings
    }

    open fun migrate(name: String, operator: (M) -> Unit) {
        // Get and operate the settings, then set it back to migrates.
        this.migrates[name] = (this.migrates[name] ?: newInstance()).also(operator)
    }

    open fun compute(vararg names: String): T {
        var result: T = doCast()
        for (name in names) {
            // Migrates the settings.
            result = result.migrate(name)
        }
        return result
    }
}
