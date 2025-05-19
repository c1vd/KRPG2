package render

import background.Background
import blocks.Block
import camera.Camera
import other.clampProgression
import other.getCoordinatesOnScreen
import message.Message
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.math.Vector2
import other.blockSize
import other.renderDistance
import scene.BossfightScene
import scene.DefaultScene
import scene.Scene

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
            camera.position.x - renderDistance,
            camera.position.x + renderDistance,
            0,
            scene.sceneWidth - 1
        )
    }

    private fun getYRangeToRender(scene: Scene): IntProgression {
        return clampProgression(
            camera.position.y - renderDistance,
            camera.position.y + renderDistance,
            0,
            scene.sceneHeight - 1
        )
    }

    private fun drawBackgrounds(xRange: IntProgression, yRange: IntProgression, scene: Scene) {
        for (x in xRange) {
            for (y in yRange) {
                val backgroundToRender = scene.getBackground(x, y)
                if (backgroundToRender != null) {
                    drawBackground(backgroundToRender, Vector2(x.toDouble(), y.toDouble()))
                }
            }
        }
    }

    private fun drawBlocks(xRange: IntProgression, yRange: IntProgression, scene: Scene) {
        for (x in xRange) {
            for (y in yRange) {
                val blockToRender = scene.getBlock(x, y)
                if (blockToRender != null) {
                    drawBlock(blockToRender, Vector2(x.toDouble(), y.toDouble()))
                }
            }
        }
    }

    fun drawScene(scene: DefaultScene) {
        when (scene) {
            is Scene -> {
                val xRange = getXRangeToRender(scene)
                val yRange = getYRangeToRender(scene)
                drawBackgrounds(xRange, yRange, scene)
                drawBlocks(xRange, yRange, scene)
            }

            is BossfightScene -> {}
        }
    }
}