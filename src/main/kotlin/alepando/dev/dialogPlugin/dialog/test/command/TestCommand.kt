package alepando.dev.dialogPlugin.dialog.test.command

import alepando.dev.dialogPlugin.dialog.test.Test
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TestCommand:CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender is Player) {
            Test(sender).testDialog()
            return true
        }

        return false
    }
}