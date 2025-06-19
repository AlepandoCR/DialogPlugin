package alepando.dev.dialogPlugin

import alepando.dev.dialogapi.listeners.PlayerConnectionStatus
import alepando.dev.dialogPlugin.dialog.test.command.TestCommand
import org.bukkit.plugin.java.JavaPlugin

class DialogPlugin : JavaPlugin() {

    override fun onEnable() {
        server.pluginManager.registerEvents(PlayerConnectionStatus(this),this)
        getCommand("test")!!.setExecutor(TestCommand())
    }

    override fun onDisable() {

    }
}
