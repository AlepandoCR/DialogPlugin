package alepando.dev.dialogPlugin

import alepando.dev.dialogPlugin.dialog.test.command.TestCommand
import alepando.dev.dialogapi.listeners.PlayerConnectionStatus
import org.bukkit.plugin.java.JavaPlugin

class DialogPlugin : JavaPlugin() {

    override fun onEnable() {
        server.pluginManager.registerEvents(PlayerConnectionStatus(this),this)
        getCommand("test")!!.setExecutor(TestCommand(this)) // Pass plugin instance
    }


    override fun onDisable() {

    }
}
