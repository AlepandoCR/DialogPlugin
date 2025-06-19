package dialog.factory.input

import dialog.factory.Wrapper
import net.minecraft.network.chat.Component

abstract class Input<T>(
    val label: Component
) : Wrapper<T>{
}