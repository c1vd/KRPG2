package scene

import attack.Attack

abstract class BossfightScene: DefaultScene() {
    abstract val attacksDefense: List<Attack>
    abstract val attacksMercy: List<Attack>
}