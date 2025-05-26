package engine.scene

import engine.attack.Attack

abstract class BossfightScene: DefaultScene() {
    abstract val attacksDefense: List<Attack>
    abstract val attacksMercy: List<Attack>
}