package alepando.dev.dialogPlugin.dialog.test.command

import alepando.dev.dialogPlugin.dialog.test.Test
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import alepando.dev.dialogPlugin.DialogPlugin // Added import

class TestCommand(private val plugin: DialogPlugin) : CommandExecutor { // Added constructor
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender is Player) {
            Test(sender, plugin).testDialog() // Pass plugin instance
            return true
        }

        return false
    }
}