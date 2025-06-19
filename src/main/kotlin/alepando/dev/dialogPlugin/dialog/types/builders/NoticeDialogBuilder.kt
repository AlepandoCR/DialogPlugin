package alepando.dev.dialogPlugin.dialog.types.builders

import alepando.dev.dialogPlugin.dialog.factory.button.Button
import alepando.dev.dialogPlugin.dialog.factory.data.DialogData
import alepando.dev.dialogPlugin.dialog.types.NoticeDialog

class NoticeDialogBuilder {
    private lateinit var data: DialogData
    private lateinit var button: Button

    fun data(data: DialogData) = apply { this.data = data }
    fun button(button: Button) = apply { this.button = button }

    fun build(): NoticeDialog {
        return NoticeDialog(data, button)
    }
}
