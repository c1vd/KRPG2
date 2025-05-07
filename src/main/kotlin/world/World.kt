package world

import blocks.Block
import math.idToBlock
import math.worldSizeX
import math.worldSizeY
import java.io.File

class World(private val filename: String) {
    var blockList: Array<Array<Block?>> = Array(worldSizeY) { Array(worldSizeX) { null } }
    fun getBlock(x: Int, y: Int): Block? {
        try {
            return blockList[y][x]
        } catch (e: Exception) {
            return null
        }
    }

    fun save() {
        val file = File("data/worlds/", filename)
        file.writeText("$worldSizeX $worldSizeY\n")
        for(x in 0..<worldSizeX){
            for (y in 0..<worldSizeY){
                val block = blockList[y][x]
                if (block != null){
                    file.appendText("${block.id} $x $y\n")
                }
            }
        }
    }

    init {
        try {
            val file = File("data/worlds/", filename)
            val lines = file.readLines()
            val (worldX, worldY) = lines[0].split(' ').map { it.toInt() }
            worldSizeX = worldX
            worldSizeY = worldY
            blockList = Array(worldSizeY) { Array(worldSizeX) { null } }
            for (line in lines.drop(1)) {
                try {
                    val (blockId, x, y) = line.split(' ').map { it.toInt() }
                    blockList[y][x] = idToBlock(blockId)
                }catch (_: Exception){
                    println("WARNING")
                }

            }
        } catch (e: Exception) {
            throw Exception("ERROR: Wrong Format")
        }

    }

}