package alepando.dev.dialogapi.factory.button.data

import net.minecraft.network.chat.Component
import java.util.*

class ButtonDataBuilder {
    private var label: Component = Component.literal("Button")
    private var width: Int = 100
    private var tooltip: Optional<Component> = Optional.empty()

    fun label(label: Component): ButtonDataBuilder {
        this.label = label
        return this
    }

    fun width(width: Int): ButtonDataBuilder {
        this.width = width
        return this
    }

    fun tooltip(tooltip: Component?): ButtonDataBuilder {
        this.tooltip = Optional.ofNullable(tooltip)
        return this
    }

    fun build(): ButtonData {
        return ButtonData(label, width, tooltip)
    }
}
