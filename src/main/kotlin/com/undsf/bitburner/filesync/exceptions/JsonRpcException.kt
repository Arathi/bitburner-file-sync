package com.undsf.bitburner.filesync.exceptions

import com.undsf.bitburner.filesync.messages.jsonrpc.JsonRpcError

class JsonRpcException(
        val error: JsonRpcError<*>,
        message: String? = error.message,
        cause: Throwable? = null) : RuntimeException(message, cause)