package alepando.dev.dialogapi.util

import com.mojang.serialization.JsonOps
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.minecraft.network.chat.ComponentSerialization
import org.bukkit.Bukkit
import net.minecraft.network.chat.Component as NMSComponent

object ComponentTranslator{
    fun toNMS(component: Component): NMSComponent {
        val jsonElement = GsonComponentSerializer.gson().serializeToTree(component)
        val consumer: (String) -> Unit = { s ->
           Bukkit.getLogger().info(s)
        }
        return ComponentSerialization.CODEC.parse(JsonOps.INSTANCE, jsonElement).getOrThrow()
    }
}


