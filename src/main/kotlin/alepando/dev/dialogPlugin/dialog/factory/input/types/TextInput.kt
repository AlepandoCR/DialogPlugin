package dialog.factory.input.types

import dialog.factory.Wrapper
import dialog.factory.input.LabelVisible
import dialog.factory.input.options.MultilineOptions
import net.minecraft.network.chat.Component
import net.minecraft.server.dialog.input.TextInput
import java.util.*

class TextInput(
    label: Component,
    with: Int,
    labelVisible: Boolean,
    private val initial: String,
    private val maxLength: Int,
    private val multiline: MultilineOptions

) : LabelVisible<TextInput>(label, with, labelVisible), Wrapper<TextInput> {
    override fun toNMS(): TextInput {
        return TextInput(with,label,labelVisible,initial,maxLength,Optional.of(multiline.toNMS()))
    }
}