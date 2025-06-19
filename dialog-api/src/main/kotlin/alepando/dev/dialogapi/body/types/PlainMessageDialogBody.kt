package alepando.dev.dialogapi.body.types

import alepando.dev.dialogapi.body.DialogBody
import net.minecraft.network.chat.Component
import net.minecraft.server.dialog.body.PlainMessage

class PlainMessageDialogBody(width: Int, private val contents: Component) : DialogBody<PlainMessage>(width) {
    override fun toNMS(): PlainMessage {
        return PlainMessage(contents,width)
    }
}