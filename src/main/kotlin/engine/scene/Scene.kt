package engine.scene


import engine.entities.NPC
import engine.extensions.setColor
import engine.extensions.split
import engine.extensions.toIntList
import engine.matrixes.BackgroundMatrix
import engine.matrixes.BlockMatrix
import engine.message.MessageController
import engine.other.Constants
import engine.other.MessageColors
import engine.other.Registrator.idToBlock
import java.io.File

abstract class Scene(private val filename: String) : DefaultScene() {
    val sceneWidth: Int
    val sceneHeight: Int
    val blocks: BlockMatrix
    val backgrounds: BackgroundMatrix
    val nonPlayableCharacters = mutableListOf<NPC>()
    val messageController = MessageController()

    private fun openSceneFile(): File {
        return File(Constants.SCENES_DIRECTORY, filename)
    }

    fun save() {
        val file = openSceneFile()

        file.writeText("$sceneWidth $sceneHeight\n")
        for (x in 0..<sceneWidth) {
            for (y in 0..<sceneHeight) {
                val block = blocks.get(x, y)
                file.appendText("${block?.id ?: continue} $x $y\n")
            }
        }
    }

    init {
        val file: File = openSceneFile()
        if (!file.exists()) {
            throw Exception("ERROR: No File or Directory".setColor(MessageColors.ERROR))
        }

        try {
            val lines = file.readLines()
            val (sceneX, sceneY) = lines.first().split().toIntList()
            sceneWidth = sceneX
            sceneHeight = sceneY
            blocks = BlockMatrix(sceneWidth, sceneHeight)
            backgrounds = BackgroundMatrix(sceneWidth, sceneHeight)
            for (line in lines.drop(1)) {
                try {
                    val (blockId, x, y) = line.split().toIntList()
                    val blockToAdd = idToBlock(blockId)

                    blocks.set(blockToAdd, x, y)
                } catch (_: Exception) {
                    println("WARNING: Wrong Block Format")
                }

            }
        } catch (_: Exception) {
            throw Exception("ERROR: Wrong Format".setColor(MessageColors.ERROR))
        }

    }


    fun addNPC(npc: NPC) {
        nonPlayableCharacters.add(npc)
    }
}