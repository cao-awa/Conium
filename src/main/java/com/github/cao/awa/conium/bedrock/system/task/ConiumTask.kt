package com.github.cao.awa.conium.bedrock.system.task

class ConiumTask(private val interval: Int = -1, private val action: () -> Unit) {
    private var tickingInterval: Int = this.interval

    fun tick() {
        if (this.interval == -1) {
            this.action()
        } else {
            if (this.tickingInterval-- == 0) {
                this.action()
                this.tickingInterval = this.interval
            }
        }
    }
}
