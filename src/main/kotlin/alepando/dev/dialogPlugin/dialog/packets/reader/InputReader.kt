package alepando.dev.dialogPlugin.dialog.packets.reader

import alepando.dev.dialogPlugin.dialog.packets.parser.CustomActionPacket
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
import org.bukkit.entity.Player

interface InputReader {
    fun task(player: Player, packet: ServerboundCustomClickActionPacket, value: Any? = CustomActionPacket.getValue(packet))
}