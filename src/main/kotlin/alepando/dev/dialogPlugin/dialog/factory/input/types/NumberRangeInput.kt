package dialog.factory.input.types

import dialog.factory.Wrapper
import dialog.factory.input.SizeableInput
import dialog.factory.input.options.RangeInfo
import net.minecraft.network.chat.Component
import net.minecraft.server.dialog.input.NumberRangeInput

class NumberRangeInput(
    label: Component,
    with: Int,
    private val labelFormat: String,
    private val rangeInfo: RangeInfo
) : SizeableInput<NumberRangeInput>(label, with), Wrapper<NumberRangeInput> {
    override fun toNMS(): NumberRangeInput {
        return NumberRangeInput(with,label,labelFormat,rangeInfo.toNMS())
    }
}