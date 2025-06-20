package alepando.dev.dialogapi.packets.reader

import alepando.dev.dialogapi.executor.Executor

internal data class ListedReader(val reader: InputReader, val resourceKey: Executor)
