package engine.other

import engine.background.Background
import engine.background.UnknownBackground
import engine.blocks.Block
import engine.blocks.Unknown
import engine.items.Food
import engine.items.UnknownFood

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