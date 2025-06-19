package dialog.listeners

import dialog.packets.PacketSniffer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.fuchiBol.FuchiBol

class PlayerConnectionStatus(private val plugin: FuchiBol): Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent){
        PacketSniffer.inject(event.player, plugin)
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent){
        PacketSniffer.eject(event.player)
    }
}