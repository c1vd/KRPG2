package entities

abstract class NPC : Entity {
    abstract val name: String
    abstract fun interact()
}