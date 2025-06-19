package alepando.dev.dialogPlugin.dialog.factory.data

import alepando.dev.dialogPlugin.dialog.body.DialogBody
import alepando.dev.dialogPlugin.dialog.factory.Wrapper
import alepando.dev.dialogPlugin.dialog.factory.input.Input
import net.minecraft.network.chat.Component
import net.minecraft.server.dialog.CommonDialogData
import net.minecraft.server.dialog.DialogAction
import java.util.*

typealias NMSDialogBody = net.minecraft.server.dialog.body.DialogBody

typealias NMSInputControl = net.minecraft.server.dialog.input.InputControl

typealias NMSInput = net.minecraft.server.dialog.Input

class DialogData(
    private val title: Component,
    private val externalTitle: Optional<Component>,
    private val canCloseWithEscape: Boolean,
    private val pause: Boolean,
    private val afterAction: DialogAction,
    private val dialogBody: List<DialogBody<*>>,
    private val inputs: List<Input<*>>
): Wrapper<CommonDialogData> {
    override fun toNMS(): CommonDialogData{
        return CommonDialogData(title,externalTitle,canCloseWithEscape,pause,afterAction,nmsBodyList(),nmsInputList())
    }

    private fun nmsInputList():MutableList<NMSInput>{
        val list = mutableListOf<NMSInput>()
        for (input in inputs) {
            val nmsInput = input.toNMS()
            if(nmsInput is NMSInputControl) {
                val aux = NMSInput("fuchibol",nmsInput)
                list.add(aux)
            }
        }
        return list
    }

    private fun nmsBodyList():MutableList<NMSDialogBody>{
        val list = mutableListOf<NMSDialogBody>()
        for (body in dialogBody) {
            val nmsBody = body.toNMS()
            if(nmsBody is NMSDialogBody) {
                list.add(nmsBody)
            }
        }
        return list
    }

}