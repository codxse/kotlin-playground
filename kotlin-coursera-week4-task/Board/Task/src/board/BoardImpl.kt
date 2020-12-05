package board

import board.Direction.*
import java.util.function.BiPredicate

open class SBoard(final override val width: Int) : SquareBoard {
    private val cells: Collection<Cell>

    init {
        this.cells = (1..width).flatMap { i ->
            (1..width).map { j -> Cell(i, j) }
        }
    }

    override fun getAllCells(): Collection<Cell> = this.cells

    override fun getCell(i: Int, j: Int): Cell {
        require(i <= width &&  j <= width)
        return this.getCellOrNull(i, j)!!
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return cells.find { it.i == i && it.j == j }
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return jRange.flatMap { j ->
            cells.filter { it.i == i && it.j == j }
        }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return iRange.flatMap { i ->
            cells.filter { it.i == i && it.j == j }
        }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction) {
            UP -> getCellOrNull(this.i - 1, this.j)
            DOWN -> getCellOrNull( this.i + 1, this.j)
            LEFT -> getCellOrNull( this.i, this.j - 1)
            RIGHT -> getCellOrNull( this.i, this.j + 1)
        }
    }
}

fun createSquareBoard(width: Int): SquareBoard = SBoard(width)


data class Pos<T>(val cell: Cell, var value: T? = null)

class GBoard<T>(width: Int) : SBoard(width), GameBoard<T> {
    private val cellValues: List<Pos<T>>

    init {
        cellValues = (1..width).flatMap { i ->
            (1..width).map { j -> Pos(super.getCell(i, j))}
        }
    }

    override fun get(cell: Cell): T? {
        val pos: Pos<T>? = cellValues.find { cell == it.cell }
        if (pos != null) {
            return pos.value
        }
        return null
    }

    override fun set(cell: Cell, value: T?) {
        cellValues.map {
            if (cell == it.cell) {
                it.value = value
            }
        }
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> = cellValues
            .filter { predicate.invoke(it.value) }
            .map { it.cell }

    override fun find(predicate: (T?) -> Boolean): Cell? = cellValues
            .find { predicate.invoke(it.value) } ?.cell

    override fun any(predicate: (T?) -> Boolean): Boolean = cellValues
            .any { predicate.invoke(it.value) }

    override fun all(predicate: (T?) -> Boolean): Boolean = cellValues
            .all { predicate.invoke(it.value) }
}

fun <T> createGameBoard(width: Int): GameBoard<T> = GBoard(width)

