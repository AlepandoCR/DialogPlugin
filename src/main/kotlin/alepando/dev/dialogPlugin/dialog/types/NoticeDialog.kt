package dialog.types

import dialog.factory.Dialog
import dialog.factory.Wrapper
import dialog.factory.button.Button
import dialog.factory.data.DialogData
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
