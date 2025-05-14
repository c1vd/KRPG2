package entities.npcs

import entities.NPC
import scene.BossfightScene

abstract class BossNPC : NPC() {
    abstract val bossfightScene: BossfightScene
    abstract fun fight()
}