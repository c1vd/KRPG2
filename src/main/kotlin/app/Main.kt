package app

import engine.camera.Camera
import engine.entities.Player
import engine.extensions.setColor
import engine.extensions.setKeyDown
import engine.extensions.setKeyUp
import engine.render.Draw
import engine.message.Message
import org.openrndr.*
import org.openrndr.color.ColorRGBa
import org.openrndr.math.Vector2
import engine.other.Directions
import engine.other.MessageColors


import engine.other.updateValues
import engine.scene.Scene

object BeginningScene : Scene("beginningScene.scene")


var frameTime: Double = 0.0


fun main() {
    application {
        configure {
            configure()
        }
        program {
            println("[INFO] KRPG2 Started".setColor(MessageColors.INFO))
            var right = false
            var left = false
            var up = false
            var down = false

            val defaultPlayerX = 0.0
            val defaultPlayerY = 0.0

            val scene = BeginningScene
            scene.addNPC(npc1)
            scene.addNPC(npc2)
            val player = Player(
                scene,
                Vector2(defaultPlayerX, defaultPlayerY),
                Vector2(0.5, 0.5)
            )
            val camera = Camera(player)
            val renderer = Draw(drawer, camera)
            renderer.setTextColor(ColorRGBa.WHITE)
            var begin = 0.0
            keyboard.setKeyDown(KEY_ARROW_RIGHT) {
                right = true
            }
            keyboard.setKeyDown(KEY_ARROW_LEFT) {
                left = true
            }
            keyboard.setKeyDown(KEY_ARROW_UP) {
                up = true
            }
            keyboard.setKeyDown(KEY_ARROW_DOWN) {
                down = true
            }

            keyboard.setKeyUp(KEY_ARROW_RIGHT) {
                right = false
            }
            keyboard.setKeyUp(KEY_ARROW_LEFT) {
                left = false
            }
            keyboard.setKeyUp(KEY_ARROW_UP) {
                up = false
            }
            keyboard.setKeyUp(KEY_ARROW_DOWN) {
                down = false
            }

            mouse.buttonDown.listen {
                if (it.button == MouseButton.LEFT) {
                    scene.messageController.popMessage()
                }
            }
            scene.messageController.addMessage(
                Message("Hello World", player)
            )
            scene.messageController.addMessage(
                Message("Message2", player)
            )

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

                drawer.rectangle(
                    Vector2(
                        width / 2.0,
                        height / 2.0
                    ),
                    16.0,
                    16.0
                )

                if (right)
                    player.goInDirection(Directions.RIGHT, frameTime)

                if (left)
                    player.goInDirection(Directions.LEFT, frameTime)

                if (up)
                    player.goInDirection(Directions.UP, frameTime)

                if (down)
                    player.goInDirection(Directions.DOWN, frameTime)


                if (!scene.messageController.areMessagesEmpty())
                    renderer.showMessage(scene.messageController.getCurrentMessage())

                // render
                renderer.drawScene(player.scene)
                println(player.getClosestNPC()?.name)
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