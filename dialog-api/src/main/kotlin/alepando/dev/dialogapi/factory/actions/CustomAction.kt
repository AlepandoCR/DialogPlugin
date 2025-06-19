package alepando.dev.dialogapi.factory.actions

import alepando.dev.dialogPlugin.DialogPlugin
import alepando.dev.dialogapi.util.DynamicListener
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.scheduler.BukkitRunnable

abstract class CustomAction {

    var dynamicListener: DynamicListener? = null


    private fun registerListener(){
        val listener = listener()
        listener?: return
        dynamicListener?.setListener(listener)
    }

    protected abstract fun task(player: Player,plugin: DialogPlugin)

    fun execute(player: Player,plugin: DialogPlugin){
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