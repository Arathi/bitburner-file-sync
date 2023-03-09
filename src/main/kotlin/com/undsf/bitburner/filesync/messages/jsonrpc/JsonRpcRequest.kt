package com.undsf.bitburner.filesync.messages.jsonrpc

open class JsonRpcRequest<P>(
        val jsonrpc: String? = JsonRpcVersion_2_0,
        val method: String?,
        val params: P? = null,
        val id: Int?
) {
    companion object {
        const val JsonRpcVersion_2_0 = "2.0"
    }
}