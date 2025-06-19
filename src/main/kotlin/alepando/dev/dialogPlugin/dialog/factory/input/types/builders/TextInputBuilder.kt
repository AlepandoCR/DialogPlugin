package dialog.factory.input.types.builders

import dialog.factory.input.options.MultilineOptions
import dialog.factory.input.types.TextInput
import net.minecraft.network.chat.Component

class TextInputBuilder {
    private var label: Component = Component.literal("Default Label")
    private var with: Int = 100
    private var labelVisible: Boolean = true
    private var initial: String = ""
    private var maxLength: Int = 255
    private var multiline: MultilineOptions? = null

    fun label(label: Component) = apply { this.label = label }
    fun with(width: Int) = apply { this.with = width }
    fun labelVisible(visible: Boolean) = apply { this.labelVisible = visible }
    fun initial(text: String) = apply { this.initial = text }
    fun maxLength(length: Int) = apply { this.maxLength = length }
    fun multiline(multiline: MultilineOptions) = apply { this.multiline = multiline }

    fun build(): TextInput {
        multiline ?: throw IllegalStateException("multiline debe estar definido")
        return TextInput(label, with, labelVisible, initial, maxLength, multiline!!)
    }
}
