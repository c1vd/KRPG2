package engine.scene

import engine.attack.Attack
import engine.attack.AttackCombo

abstract class BossfightScene: DefaultScene() {
    abstract val bossHealth: Double
    abstract val playerHealth: Double
    abstract val attackCombosDefense: List<AttackCombo>
    abstract val attackCombosMercy: List<AttackCombo>
}