package dialog.factory

import dialog.factory.data.DialogData
import dialog.util.DynamicListener
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