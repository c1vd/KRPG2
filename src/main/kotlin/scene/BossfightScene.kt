package scene

import attack.Attack

abstract class BossfightScene: DefaultScene() {
    abstract val attacksDefense1: List<Attack>
    abstract val attacksMercy: List<Attack>
}