package alepando.dev.dialogPlugin.dialog.factory

import alepando.dev.dialogPlugin.dialog.factory.data.DialogData
import alepando.dev.dialogPlugin.dialog.util.DynamicListener
import org.bukkit.event.Listener
import java.util.*

abstract class Dialog(
    val data: DialogData,
    private val dynamicListener: Optional<DynamicListener> = Optional.empty()
) {
    fun getBukkitListener(): Listener?{
        if(!dynamicListener.isEmpty) return dynamicListener.get().getListener()

        return null
    }

    fun getDynamicListener(): Optional<DynamicListener> {
        return dynamicListener
    }
}