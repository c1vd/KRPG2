package message

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MessageControllerTest {
    @Test
    fun testAreMessagesEmpty() {
        val messageController = MessageController()
        assertTrue(messageController.areMessagesEmpty())
        messageController.addMessage(Message("Hello World!"))
        assertFalse(messageController.areMessagesEmpty())
    }

    @Test
    fun testAddMessage() {
        val messageController = MessageController()
        messageController.addMessage(Message("Hello World!"))
        assertTrue(messageController.getCurrentMessage() != NoMessagesMessage)
        assertTrue(messageController.getCurrentMessage() == Message("Hello World!"))
        messageController.addMessage(Message("Hello"))
        assertTrue(messageController.getCurrentMessage() != NoMessagesMessage)
        assertTrue(messageController.getCurrentMessage() == Message("Hello World!"))
    }
}