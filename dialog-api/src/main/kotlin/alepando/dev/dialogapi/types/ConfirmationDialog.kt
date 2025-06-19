package alepando.dev.dialogapi.types

import alepando.dev.dialogapi.factory.Dialog
import alepando.dev.dialogapi.factory.data.DialogData
import alepando.dev.dialogapi.util.DynamicListener
import net.minecraft.server.dialog.ActionButton
import java.util.*

typealias NMSConfirmationDialog = net.minecraft.server.dialog.ConfirmationDialog

class ConfirmationDialog(
    private val yesButton: ActionButton,
    private val noButton: ActionButton, data: DialogData,
    dynamicListener: Optional<DynamicListener> = Optional.empty()
): Dialog(data, dynamicListener){

    fun toNMS(): NMSConfirmationDialog {
        return NMSConfirmationDialog(data.toNMS(),yesButton,noButton)
    }

}