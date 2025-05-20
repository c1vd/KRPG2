package scene

import background.Background
import blocks.Block
import blocks.blocks.Unknown
import entities.NPC
import extensions.toIntArray
import other.idToBlock
import message.Message
import message.MessageController
import org.openrndr.collections.push
import other.Constants
import other.MessageColors
import java.io.File

abstract class Scene(private val filename: String) : DefaultScene() {
    val sceneWidth: Int
    val sceneHeight: Int
    private val blocks: BlockMatrix
    private val backgrounds: BackgroundMatrix
    private val nonPlayableCharacters = mutableListOf<NPC>()
    val messageController = MessageController()

    fun getBlock(x: Int, y: Int): Block? {
        return blocks.get(x, y)
    }

    fun doesBlockExist(x: Int, y: Int): Boolean{
        return getBlock(x, y) != null
    }

    private fun setBlock(block: Block, x: Int, y: Int) {
        blocks.set(block, x, y)
    }

    fun getBackground(x: Int, y: Int): Background? {
        return backgrounds.get(x, y)
    }

    private fun setBackground(background: Background, x: Int, y: Int) {
        backgrounds.set(background, x, y)
    }

    private fun openSceneFile(): File {
        return File(Constants.SCENES_DIRECTORY, filename)
    }

    fun save() {
        val file = openSceneFile()

        file.writeText("$sceneWidth $sceneHeight\n")
        for (x in 0..<sceneWidth) {
            for (y in 0..<sceneHeight) {
                val block = getBlock(x, y)
                file.appendText("${block?.id ?: continue} $x $y\n")
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
            blocks = BlockMatrix(sceneWidth, sceneHeight)
            backgrounds = BackgroundMatrix(sceneWidth, sceneHeight)
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