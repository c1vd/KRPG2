package app.attacks.wallAttacks

import app.attacks.WallAttack

class RightWallAttack(
    override val damage: Double,
    override val noJump: Boolean,
    override val height: Double,
    override val speed: Double
) : WallAttack()