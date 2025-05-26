package app

import camera.Camera
import entities.Player
import extensions.setColor
import extensions.setKeyDown
import extensions.setKeyUp
import render.Draw
import message.Message
import org.openrndr.*
import org.openrndr.color.ColorRGBa
import org.openrndr.math.Vector2
import other.Directions
import other.EnhancedBoolean
import other.MessageColors
import other.registerBlock

import other.updateValues
import scene.Scene

object BeginningScene : Scene("beginningScene.scene")


var frameTime: Double = 0.0


fun main() {
    application {
        configure {
            configure()
        }
        program {
            println("[INFO] KRPG2 Started".setColor(MessageColors.INFO))
            val right = EnhancedBoolean(false)
            val left = EnhancedBoolean(false)
            val up = EnhancedBoolean(false)
            val down = EnhancedBoolean(false)

            val defaultPlayerX = 0.0
            val defaultPlayerY = 0.0

            val scene = BeginningScene
            val player = Player(scene, Vector2(defaultPlayerX, defaultPlayerY), Vector2(0.5, 0.5))
            val camera = Camera(player)
            val renderer = Draw(drawer, camera)

            var begin = 0.0
            keyboard.setKeyDown(KEY_ARROW_RIGHT) {
                right.setTrue()
            }
            keyboard.setKeyDown(KEY_ARROW_LEFT) {
                left.setTrue()
            }
            keyboard.setKeyDown(KEY_ARROW_UP) {
                up.setTrue()
            }
            keyboard.setKeyDown(KEY_ARROW_DOWN) {
                down.setTrue()
            }

            keyboard.setKeyUp(KEY_ARROW_RIGHT) {
                right.setFalse()
            }
            keyboard.setKeyUp(KEY_ARROW_LEFT) {
                left.setFalse()
            }
            keyboard.setKeyUp(KEY_ARROW_UP) {
                up.setFalse()
            }
            keyboard.setKeyUp(KEY_ARROW_DOWN) {
                down.setFalse()
            }

            mouse.buttonDown.listen {
                if (it.button == MouseButton.LEFT) {
                    scene.messageController.popMessage()
                }
            }
            scene.messageController.addMessage(Message("Hello World", player))
            scene.messageController.addMessage(Message("Message2", player))

            extend {
                frameTime = seconds - begin
                begin = seconds

                updateValues(width, height)
                // camera moving
                camera.moveCamera()

                // screen cleaning
                renderer.clearScreen()

                // FPS and FrameTime
                renderer.setFillColor(ColorRGBa.WHITE)


                drawer.text(
                    "FrameTime: ${
                        (frameTime * 10000).toInt().toDouble() / 10
                    } ms; FPS: ${(1 / frameTime).toInt()}; Player X: ${player.position.x}; Player Y: ${player.position.y}",
                    10.0,
                    20.0
                )

                drawer.rectangle(Vector2(width / 2.0, height / 2.0), 16.0, 16.0)

                if (right.getValue())
                    player.goInDirection(Directions.RIGHT, frameTime)

                if (left.getValue())
                    player.goInDirection(Directions.LEFT, frameTime)

                if (up.getValue())
                    player.goInDirection(Directions.UP, frameTime)

                if (down.getValue())
                    player.goInDirection(Directions.DOWN, frameTime)


                if (!scene.messageController.areMessagesEmpty())
                    renderer.showMessage(scene.messageController.getCurrentMessage())

                // render
                renderer.drawScene(player.scene)
            }
            scene.save()
        }
    }
}

private fun Configuration.configure() {
    width = 1280
    height = 720

    windowResizable = true
}