package dialog.factory.input.options

import dialog.factory.Wrapper
import net.minecraft.server.dialog.input.NumberRangeInput
import java.util.*

typealias NMSRangeInfo = NumberRangeInput.RangeInfo

class RangeInfo(
    private val start: Float,
    private val end: Float,
    private val initial: Optional<Float> = Optional.empty(),
    private val step: Optional<Float> = Optional.empty()
) : Wrapper<NMSRangeInfo>{
    override fun toNMS(): NMSRangeInfo {
        return NMSRangeInfo(start, end,initial,step)
    }
}