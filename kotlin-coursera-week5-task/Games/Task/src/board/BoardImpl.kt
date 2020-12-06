package board

import board.Direction.*

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

class GBoard<T>(width: Int) : SBoard(width), GameBoard<T> {
    private val cellValues = mutableMapOf<Cell, T?>()

    init {
        (1..width).flatMap { i ->
            (1..width).map { j -> cellValues[super.getCell(i, j)] = null }
        }
    }

    override fun get(cell: Cell): T? = cellValues[cell]

    override fun set(cell: Cell, value: T?) {
        cellValues[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> = cellValues
            .toList()
            .filter { predicate.invoke(it.second) }
            .map { it.first }

    override fun find(predicate: (T?) -> Boolean): Cell? = cellValues
            .toList()
            .find { predicate.invoke(it.second) } ?.first

    override fun any(predicate: (T?) -> Boolean): Boolean = cellValues
            .toList()
            .any { predicate.invoke(it.second) }

    override fun all(predicate: (T?) -> Boolean): Boolean = cellValues
            .toList()
            .all { predicate.invoke(it.second) }
}

fun <T> createGameBoard(width: Int): GameBoard<T> = GBoard(width)

