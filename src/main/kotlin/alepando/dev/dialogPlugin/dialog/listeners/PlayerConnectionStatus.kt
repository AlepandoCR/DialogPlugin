package alepando.dev.dialogPlugin.dialog.listeners

import alepando.dev.dialogPlugin.DialogPlugin
import alepando.dev.dialogPlugin.dialog.packets.PacketSniffer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerConnectionStatus(private val plugin: DialogPlugin): Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent){
        PacketSniffer.inject(event.player, plugin)
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent){
        PacketSniffer.eject(event.player)
    }
}