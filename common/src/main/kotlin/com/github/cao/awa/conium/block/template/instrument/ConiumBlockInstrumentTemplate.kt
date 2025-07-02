package com.github.cao.awa.conium.block.template.instrument

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Block.INSTRUMENT
import com.google.gson.JsonElement
import net.minecraft.block.AbstractBlock
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.registry.RegistryWrapper

open class ConiumBlockInstrumentTemplate(private val instrument: NoteBlockInstrument, name: String = INSTRUMENT) : ConiumBlockTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumBlockInstrumentTemplate = ConiumBlockInstrumentTemplate(createBehaviors(element.asString))

        @JvmStatic
        fun createBehaviors(name: String): NoteBlockInstrument {
            return when (name.lowercase()) {
                "harp" -> NoteBlockInstrument.HARP
                "basedrum" -> NoteBlockInstrument.BASEDRUM
                "snare" -> NoteBlockInstrument.SNARE
                "hat" -> NoteBlockInstrument.HAT
                "bass" -> NoteBlockInstrument.BASS
                "flute" -> NoteBlockInstrument.FLUTE
                "bell" -> NoteBlockInstrument.BELL
                "guitar" -> NoteBlockInstrument.GUITAR
                "chime" -> NoteBlockInstrument.CHIME
                "xylophone" -> NoteBlockInstrument.XYLOPHONE
                "iron_xylophone" -> NoteBlockInstrument.IRON_XYLOPHONE
                "cow_bell" -> NoteBlockInstrument.COW_BELL
                "didgeridoo" -> NoteBlockInstrument.DIDGERIDOO
                "bit" -> NoteBlockInstrument.BIT
                "banjo" -> NoteBlockInstrument.BANJO
                "pling" -> NoteBlockInstrument.PLING
                "zombie" -> NoteBlockInstrument.ZOMBIE
                "skeleton" -> NoteBlockInstrument.SKELETON
                "creeper" -> NoteBlockInstrument.CREEPER
                "dragon" -> NoteBlockInstrument.DRAGON
                "whiter_skeleton" -> NoteBlockInstrument.WITHER_SKELETON
                "piglin" -> NoteBlockInstrument.PIGLIN
                "custom_head" -> NoteBlockInstrument.CUSTOM_HEAD
                else -> throw IllegalArgumentException("No note block instrument: '$name'")
            }
        }
    }

    override fun settings(settings: AbstractBlock.Settings) {
        // Set piston behavior.
        settings.instrument(this.instrument)
    }
}
