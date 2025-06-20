package alepando.dev.dialogPlugin.dialog.test

import alepando.dev.dialogapi.body.types.PlainMessageDialogBody
import alepando.dev.dialogapi.body.types.builders.ItemDialogBodyBuilder
import alepando.dev.dialogapi.executor.Executor
import alepando.dev.dialogapi.factory.button.Button
import alepando.dev.dialogapi.factory.button.data.ButtonDataBuilder
import alepando.dev.dialogapi.factory.data.DialogDataBuilder
import alepando.dev.dialogapi.factory.input.options.MultilineOptions
import alepando.dev.dialogapi.factory.input.options.RangeInfo
import alepando.dev.dialogapi.factory.input.types.builders.NumberRangeInputBuilder
import alepando.dev.dialogapi.factory.input.types.builders.TextInputBuilder
import alepando.dev.dialogapi.types.builders.MultiActionDialogBuilder
import net.minecraft.core.Holder.Direct
import net.minecraft.network.chat.Component
import net.minecraft.server.dialog.Dialog
import net.minecraft.server.dialog.action.CustomAll
import net.minecraft.server.dialog.body.PlainMessage
import org.bukkit.Material
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

class Test(
    private val player: Player
) {

    fun testDialog(){
        val craftPlayer = player as CraftPlayer
        val nmsPlayer = craftPlayer.handle


        val buttonData = ButtonDataBuilder()
            .label(Component.literal("test"))
            .width(100)
            .build()

        val exitButtonData = ButtonDataBuilder()
            .label(Component.literal("exit"))
            .width(80)
            .build()

        val testButton = Button(buttonData, Optional.of(CustomAll(Executor.KILL_PLAYER.key, Optional.empty())))


        val exitButton = Button(exitButtonData)

        val booleanInput = NumberRangeInputBuilder()
            .label(Component.literal("Input"))
            .width(150)
            .rangeInfo(RangeInfo(1.0f,10.0f))
            .labelFormat("")
            .build()

        val stringInput = TextInputBuilder()
            .label(Component.literal("Text input"))
            .width(256)
            .initial("")
            .labelVisible(true)
            .maxLength(300)
            .multiline(MultilineOptions(10,20))
            .build()

        val itemBody =  ItemDialogBodyBuilder()
            .item(ItemStack(Material.LAPIS_LAZULI))
            .height(20)
            .width(20)
            .description(PlainMessage(Component.literal("Item test"),100))
            .showDecorations(true)
            .showTooltip(false)
            .build()

        val dialogBody = PlainMessageDialogBody(100, Component.literal("A").append(Component.literal(" message")))

        val dialogData = DialogDataBuilder()
            .title(Component.literal("Test Menu"))
            .externalTitle(Component.literal("Menu Test"))
            .canCloseWithEscape(true)
            .addBody(dialogBody)
            .addBody(itemBody)
            .addBody(dialogBody)
            //.addInput(booleanInput)
            .addInput(stringInput)
            .build()

        val confirmationDialog = MultiActionDialogBuilder()
            .data(dialogData)
            .columns(1)
            .exitButton(exitButton)
            .addButton(testButton)
            .addButton(testButton)
            .build()



        val holder = Direct<Dialog>(confirmationDialog.toNMS())

        nmsPlayer.openDialog(holder)
    }
}