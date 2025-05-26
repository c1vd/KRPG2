package other

import background.Background
import background.UnknownBackground
import blocks.Block
import blocks.Unknown
import items.Food
import items.UnknownFood

object Registrator {
    val registeredBlocks = mutableMapOf<Int, Block>()
    val registeredFood = mutableMapOf<Int, Food>()
    val registeredBackgrounds = mutableMapOf<Int, Background>()

    fun idToBlock(id: Int): Block = registeredBlocks.getOrDefault(id, Unknown)
    fun idToFood(id: Int): Food = registeredFood.getOrDefault(id, UnknownFood)
    fun idToBackground(id: Int): Background = registeredBackgrounds.getOrDefault(id, UnknownBackground)

    fun registerBlock(block: Block) = registeredBlocks.put(block.id, block)
    fun registerFood(food: Food) = registeredFood.put(food.id, food)
    fun registerBackground(background: Background) = registeredBackgrounds.put(background.id, background)
}