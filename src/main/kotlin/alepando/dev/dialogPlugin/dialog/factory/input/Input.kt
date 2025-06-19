package alepando.dev.dialogPlugin.dialog.factory.input

import alepando.dev.dialogPlugin.dialog.factory.Wrapper
import net.minecraft.network.chat.Component

abstract class Input<T>(
    val label: Component
) : Wrapper<T> {
}