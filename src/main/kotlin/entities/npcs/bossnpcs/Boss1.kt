package entities.npcs.bossnpcs

import entities.npcs.BossNPC
import org.openrndr.draw.ColorBuffer
import org.openrndr.math.Vector2
import scene.BossfightScene
import scene.Scene

object Boss1 : BossNPC() {
    override var scene: Scene
        get() = TODO("Not yet implemented")
        set(value) {}
    override val bossfightScene: BossfightScene
        get() = TODO("Not yet implemented")
    override val name: String = "Boss1"
    override var position: Vector2 = Vector2(10.0, 10.0)
    override val sizeVector: Vector2 = Vector2(16.0, 32.0)


    override val texture: ColorBuffer
        get() = TODO("Not yet implemented")

    override fun interact() {
        TODO("Not yet implemented")
    }

    override fun fight() {
        TODO("Not yet implemented")
    }
}