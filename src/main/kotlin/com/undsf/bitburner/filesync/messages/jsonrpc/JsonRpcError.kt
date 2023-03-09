package com.undsf.bitburner.filesync.messages.jsonrpc

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

class JsonRpcError<D>(
        @get:JsonProperty
        val code: Int,

        @get:JsonProperty
        var message: String? = null,

        @get:JsonProperty
        @get:JsonInclude(JsonInclude.Include.NON_NULL)
        val data: D? = null
) {
    init {
        if (message == null) {
            message = Descriptions.getOrDefault(code, "未知错误")
        }
    }

    companion object {
        const val CodeParseError = -32700
        const val CodeInvalidRequest = -32600
        const val CodeMethodNotFound = -32601
        const val CodeInvalidParams = -32602
        const val CodeInternalError = -32603

        val Descriptions = mutableMapOf(
                CodeParseError to "解析错误",
                CodeInvalidRequest to "无效的请求",
                CodeMethodNotFound to "方法未找到",
                CodeInvalidParams to "无效的参数",
                CodeInternalError to "内部错误"
        )
    }
}