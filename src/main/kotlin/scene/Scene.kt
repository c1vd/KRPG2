package scene

import background.Background
import blocks.Block
import blocks.blocks.Unknown
import entities.NPC
import extensions.toIntArray
import other.idToBlock
import message.Message
import org.openrndr.collections.push
import other.Constants
import other.MessageColors
import java.io.File

abstract class Scene(private val filename: String) : DefaultScene() {
    val sceneWidth: Int
    val sceneHeight: Int
    private val blockList: Array<Array<Block?>>
    private val backgroundList: Array<Array<Background?>>
    private val nonPlayableCharacters = mutableListOf<NPC>()
    private val messages: ArrayDeque<Message> = ArrayDeque()

    fun areMessagesEmpty(): Boolean {
        return messages.isEmpty()
    }

    fun getCurrentMessage(): Message {
        return messages.firstOrNull() ?: Message("There are not messages")
    }

    fun deleteCurrentMessage() {
        messages.removeFirstOrNull()
    }

    fun addMessage(message: Message) {
        messages.push(message)
    }

    fun getBlock(x: Int, y: Int): Block? {
        return try {
            blockList[y][x]
        } catch (_: Exception) {
            null
        }
    }

    private fun setBlock(block: Block, x: Int, y: Int) {
        try {
            blockList[y][x] = block
        } catch (_: Exception) {
            throw Exception("ERROR: Invalid Index")
        }
    }

    fun getBackground(x: Int, y: Int): Background? {
        return try {
            backgroundList[y][x]
        } catch (_: Exception) {
            null
        }
    }

    private fun setBackground(background: Background, x: Int, y: Int) {
        try {
            backgroundList[y][x] = background
        } catch (_: Exception) {
            throw Exception("ERROR: Invalid Index")
        }
    }

    private fun openSceneFile(): File {
        return File(Constants.scenesDirectory, filename)
    }

    fun save() {
        val file = openSceneFile()

        file.writeText("$sceneWidth $sceneHeight\n")
        for (x in 0..<sceneWidth) {
            for (y in 0..<sceneHeight) {
                val block = getBlock(x, y)
                if (block != null) {
                    file.appendText("${block.id} $x $y\n")
                }
            }
        }
    }

    init {
        var file: File = openSceneFile()
        if (!file.exists()) {
            throw Exception(MessageColors.ERROR + "ERROR: No File or Directory")
        }

        try {
            val lines = file.readLines()
            val (sceneX, sceneY) = lines.first().split(' ').toIntArray()
            sceneWidth = sceneX
            sceneHeight = sceneY
            blockList = Array(sceneHeight) { Array(sceneWidth) { null } }
            backgroundList = Array(sceneHeight) { Array(sceneWidth) { null } }
            for (line in lines.drop(1)) {
                try {
                    val (blockId, x, y) = line.split(' ').toIntArray()
                    val blockToAdd = idToBlock(blockId)

                    setBlock(blockToAdd ?: Unknown, x, y)
                } catch (_: Exception) {
                    println("WARNING: Wrong Block")
                }

            }
        } catch (_: Exception) {
            throw Exception("ERROR: Wrong Format")
        }

    }

    fun addNPC(npc: NPC) {
        nonPlayableCharacters.add(npc)
    }
}