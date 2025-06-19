package alepando.dev.dialogapi.factory.input.types.builders

import alepando.dev.dialogapi.factory.input.types.BooleanInput
import net.minecraft.network.chat.Component

class BooleanInputBuilder {
    private var label: Component = Component.literal("Default Label")
    private var initial: Boolean = false
    private var onTrue: String = "true"
    private var onFalse: String = "false"

    fun label(label: Component) = apply { this.label = label }
    fun initial(value: Boolean) = apply { this.initial = value }
    fun onTrue(action: String) = apply { this.onTrue = action }
    fun onFalse(action: String) = apply { this.onFalse = action }

    fun build(): BooleanInput {
        return BooleanInput(label, initial, onTrue, onFalse)
    }
}
