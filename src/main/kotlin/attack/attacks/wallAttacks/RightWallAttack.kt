package attack.attacks.wallAttacks

import attack.attacks.WallAttack

class RightWallAttack(
    override val damage: Double,
    override val noJump: Boolean,
    override val height: Double,
    override val speed: Double
) : WallAttack()