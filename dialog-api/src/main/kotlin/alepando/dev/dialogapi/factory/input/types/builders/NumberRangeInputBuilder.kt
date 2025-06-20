package alepando.dev.dialogapi.factory.input.types.builders

import alepando.dev.dialogapi.factory.input.options.RangeInfo
import alepando.dev.dialogapi.factory.input.types.NumberRangeInput
import net.minecraft.network.chat.Component

class NumberRangeInputBuilder {
    private var label: Component = Component.literal("N/D")
    private var with: Int = 100
    private var labelFormat: String = ""
    private var rangeInfo: RangeInfo? = null

    fun label(label: Component) = apply { this.label = label }
    fun width(width: Int) = apply { this.with = width }
    fun labelFormat(format: String) = apply { this.labelFormat = format }
    fun rangeInfo(rangeInfo: RangeInfo) = apply { this.rangeInfo = rangeInfo }

    fun build(): NumberRangeInput {

        rangeInfo ?: throw IllegalStateException("RangeInfo must be set for NumberRangeInput.")

        return NumberRangeInput(label, with, labelFormat, rangeInfo!!)
    }
}
