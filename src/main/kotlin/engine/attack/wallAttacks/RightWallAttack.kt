package engine.attack.wallAttacks

import engine.attack.WallAttack

class RightWallAttack(
    override val damage: Double,
    override val noJump: Boolean,
    override val height: Double,
    override val speed: Double,
    override val duration: Double,
    override val atSecond: Double
) : WallAttack()