package engine.message

import org.openrndr.collections.push

class MessageController {
    private val messages = ArrayDeque<Message>()
    fun areMessagesEmpty(): Boolean {
        return messages.isEmpty()
    }

    fun addMessage(message: Message) {
        messages.push(message)
    }

    fun popMessage(): Message {
        return messages.removeFirstOrNull() ?: NoMessagesMessage
    }

    fun getCurrentMessage(): Message {
        return messages.firstOrNull() ?: NoMessagesMessage
    }

    fun clearMessages(){
        messages.clear()
    }
}