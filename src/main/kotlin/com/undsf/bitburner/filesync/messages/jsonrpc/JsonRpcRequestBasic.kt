package com.undsf.bitburner.filesync.messages.jsonrpc

class JsonRpcRequestBasic(
        jsonrpc: String = "2.0",
        method: String,
        id: Int
) : JsonRpcRequest<Any>(
        jsonrpc,
        method,
        null,
        id
)