package message

import org.openrndr.collections.push

class MessageController {
    private val messages = ArrayDeque<Message>()
    fun areMessagesEmpty(): Boolean{
        return messages.isEmpty()
    }
    fun addMessage(message: Message){
        messages.push(message)
    }
    fun popMessage(): Message?{
        return messages.removeFirstOrNull()
    }
    fun getCurrentMessage(): Message{
        return messages.firstOrNull() ?: Message("No Messages")
    }
}