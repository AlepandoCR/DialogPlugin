package dialog.factory.actions

import dialog.util.DynamicListener
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.scheduler.BukkitRunnable
import org.fuchiBol.FuchiBol

abstract class CustomAction {

    var dynamicListener: DynamicListener? = null


    private fun registerListener(){
        val listener = listener()
        listener?: return
        dynamicListener?.setListener(listener)
    }

    protected abstract fun task(player: Player,plugin: FuchiBol)

    fun execute(player: Player,plugin: FuchiBol){
        dynamicListener = DynamicListener(plugin)
        registerListener()
        object : BukkitRunnable(){
            override fun run() {
                task(player,plugin)
            }
        }.runTask(plugin)
    }

    open fun listener(): Listener?{
        return null
    }

}