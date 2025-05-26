package engine.message

import engine.entities.Entity
import engine.entities.ErrorReporter

data class Message(val text: String, val speaker: Entity = ErrorReporter)
