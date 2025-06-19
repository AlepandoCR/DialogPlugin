package alepando.dev.dialogapi.packets.reader

import alepando.dev.dialogapi.executor.Keys
import alepando.dev.dialogapi.packets.reader.InputReader

data class ListedReader(val reader: InputReader, val resourceKey: Keys)
