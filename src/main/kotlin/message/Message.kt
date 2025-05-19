package message

import entities.Entity
import entities.ErrorReporter

data class Message(val text: String, val speaker: Entity = ErrorReporter)
