package engine.render

import engine.background.Background
import engine.blocks.Block
import engine.camera.Camera
import engine.extensions.infix.multiply
import engine.other.clampProgression
import engine.other.getCoordinatesOnScreen
import engine.message.Message
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.math.Vector2
import engine.other.IntCoordinates
import engine.other.blockSize
import engine.other.renderDistance
import engine.scene.BossfightScene
import engine.scene.DefaultScene
import engine.scene.Scene

class Draw(private val drawer: Drawer, private val camera: Camera) {
    fun clearScreen(color: ColorRGBa = ColorRGBa.BLACK) {
        drawer.clear(color)
    }

    fun setFillColor(color: ColorRGBa) {
        drawer.fill = color
    }

    fun showMessage(message: Message) {
        drawer.text("${message.speaker.name}: ${message.text}", Vector2(10.0, 10.0))
    }

    private fun drawBlock(block: Block, blockPosition: Vector2) {
        drawer.image(block.texture, getCoordinatesOnScreen(blockPosition, camera), blockSize, blockSize)
    }

    private fun drawBackground(background: Background, backgroundPosition: Vector2) {
        drawer.image(background.texture, getCoordinatesOnScreen(backgroundPosition, camera), blockSize, blockSize)
    }

    private fun getXRangeToRender(scene: Scene): IntProgression {
        return clampProgression(
            camera.x - renderDistance,
            camera.x + renderDistance,
            0,
            scene.sceneWidth - 1
        )
    }

    private fun getYRangeToRender(scene: Scene): IntProgression {
        return clampProgression(
            camera.y - renderDistance,
            camera.y + renderDistance,
            0,
            scene.sceneHeight - 1
        )
    }

    private fun drawBackgrounds(coordinatesToRender: List<IntCoordinates>, scene: Scene) {
        for ((x, y) in coordinatesToRender) {
                val backgroundToRender = scene.backgrounds.get(x, y)
                drawBackground(backgroundToRender ?: continue, Vector2(x.toDouble(), y.toDouble()))
        }
    }

    private fun drawBlocks(coordinatesToRender: List<IntCoordinates>, scene: Scene) {
        for ((x, y) in coordinatesToRender) {
            val blockToRender = scene.blocks.get(x, y)
            drawBlock(blockToRender ?: continue, Vector2(x.toDouble(), y.toDouble()))
        }
    }

    fun drawScene(scene: DefaultScene) {
        when (scene) {
            is Scene -> {
                val coordinatesToRender = getXRangeToRender(scene) multiply getYRangeToRender(scene)

                drawBackgrounds(coordinatesToRender, scene)
                drawBlocks(coordinatesToRender, scene)
            }

            is BossfightScene -> {}
        }
    }
}