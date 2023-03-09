package com.undsf.bitburner.filesync.messages.jsonrpc

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

class JsonRpcResponse<R>(
        @get:JsonProperty
        val jsonrpc: String? = JsonRpcRequest.JsonRpcVersion_2_0,

        @get:JsonProperty
        @get:JsonInclude(JsonInclude.Include.NON_NULL)
        val result: R? = null,

        @get:JsonProperty
        @get:JsonInclude(JsonInclude.Include.NON_NULL)
        val error: JsonRpcError<*>? = null,

        @get:JsonProperty
        val id: Int?
) {
}