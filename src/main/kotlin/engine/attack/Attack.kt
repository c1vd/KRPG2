package engine.attack

abstract class Attack {
    abstract val damage: Double
    abstract val duration: Double
    abstract val atSecond: Double
}