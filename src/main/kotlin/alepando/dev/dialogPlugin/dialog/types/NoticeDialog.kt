package alepando.dev.dialogPlugin.dialog.types

import alepando.dev.dialogPlugin.dialog.factory.Dialog
import alepando.dev.dialogPlugin.dialog.factory.Wrapper
import alepando.dev.dialogPlugin.dialog.factory.button.Button
import alepando.dev.dialogPlugin.dialog.factory.data.DialogData
import net.minecraft.server.dialog.NoticeDialog

typealias NMSNoticeDialog = NoticeDialog

class NoticeDialog(
    data: DialogData,
    private val button: Button
) : Dialog(data), Wrapper<NMSNoticeDialog> {

    override fun toNMS(): NMSNoticeDialog {
        return NMSNoticeDialog(
            data.toNMS(),
            button.toNMS()
        )
    }
}
