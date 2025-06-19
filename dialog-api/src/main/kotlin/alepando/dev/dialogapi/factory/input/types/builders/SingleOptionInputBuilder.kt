package alepando.dev.dialogapi.factory.input.types.builders

import alepando.dev.dialogapi.factory.input.options.Entry
import alepando.dev.dialogapi.factory.input.types.SingleOptionInput
import net.minecraft.network.chat.Component

class SingleOptionInputBuilder {
    private var label: Component = Component.literal("N/D")
    private var with: Int = 100
    private var entries: MutableList<Entry> = mutableListOf()
    private var labelVisible: Boolean = true

    fun label(label: Component) = apply { this.label = label }
    fun with(width: Int) = apply { this.with = width }
    fun addEntry(entry: Entry) = apply { this.entries.add(entry) }
    fun entries(entries: List<Entry>) = apply { this.entries = entries.toMutableList() }
    fun labelVisible(visible: Boolean) = apply { this.labelVisible = visible }

    fun build(): SingleOptionInput {
        return SingleOptionInput(label, with, entries, labelVisible)
    }
}
