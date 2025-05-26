package engine.entities.npcs

import engine.entities.NPC
import engine.scene.BossfightScene

abstract class BossNPC : NPC() {
    abstract val bossfightScene: BossfightScene
    abstract fun fight()
}