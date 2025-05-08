import camera.Camera
import render.Draw
import entities.Player
import extensions.toBlockVector2
import math.*
import org.openrndr.*
import org.openrndr.color.ColorRGBa
import org.openrndr.math.Vector2
import world.World


var frameTime: Double = 0.0

fun main() {
    application {
        configure {
            width = widthOfScreen
            height = heightOfScreen
            windowResizable = true
        }
        program {
            var isMouseButtonPressed = false
            val defaultPlayerX = 0.0
            val defaultPlayerY = 0.0

            val world = World("world1.world")
            val player = Player(Vector2(defaultPlayerX, defaultPlayerY), 100.0, world, Vector2(0.5, 0.5), speed = 10.0)
            val camera = Camera(player)
            val renderer = Draw(drawer, world, camera)

            var begin = 0.0
            mouse.buttonDown.listen {
                if (it.button == MouseButton.LEFT)
                    isMouseButtonPressed = true
            }
            mouse.buttonUp.listen {
                if (it.button == MouseButton.LEFT)
                    isMouseButtonPressed = false
            }

            extend {
                frameTime = seconds - begin
                begin = seconds

                widthOfScreen = width
                heightOfScreen = height

                updateValues()
                // camera moving
                camera.moveCamera()

                // screen cleaning
                renderer.clearScreen()

                // FPS and FrameTime
                drawer.fill = ColorRGBa.WHITE
                drawer.text(
                    "FrameTime: ${
                        (frameTime * 10000).toInt().toDouble() / 10
                    } ms; FPS: ${(1 / frameTime).toInt()}", 10.0, 20.0
                )

                drawer.rectangle(Vector2(widthOfScreen / 2.0, heightOfScreen / 2.0), blockSize / 2.0, blockSize / 2.0)

                if (isMouseButtonPressed) {
                    player.goInDirection(getGlobalCursorPosition(mouse.position.toBlockVector2(), camera), frameTime)
                }

                // render
                renderer.drawWorld()
            }
            world.save()
        }
    }
}