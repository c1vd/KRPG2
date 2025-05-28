package engine.attack

abstract class WallAttack : Attack() {
    abstract val noJump: Boolean
    abstract val height: Double
    abstract val speed: Double
}