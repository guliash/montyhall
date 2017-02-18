package com.guliash.montyhall

import java.util.*

enum class Tactic {
    SWAP, NO_SWAP
}

enum class RoundResult {
    WIN, LOSE
}

fun main(args: Array<String>) {
    if (args.size > 2) {
        println("Set more than two arguments")
        return
    }
    var roundsCount = 1000
    if (args.size >= 1) {
        roundsCount = args[0].toInt()
    }
    var tactic = Tactic.SWAP
    if (args.size == 2) {
        val tacticArg = args[1]
        tactic = when (tacticArg) {
            "swap" -> Tactic.SWAP
            "no_swap" -> Tactic.NO_SWAP
            else -> {
                println("Please set correct tactic: swap or no_swap")
                return
            }
        }
    }
    println("Will run ${roundsCount} rounds with ${tactic} tactic")
    var wins = 0
    var loses = 0
    for (it in 1..roundsCount) {
        if (round(tactic) == RoundResult.WIN) {
            wins++
        } else {
            loses++
        }
    }
    println("wins = ${wins} and loses = ${loses}")
}

fun round(tactic: Tactic): RoundResult {
    val rnd = Random()
    val doors = charArrayOf('x', 'x', 'o')
    val doorToChoose = rnd.nextInt(3)

    if (tactic == Tactic.NO_SWAP) {
        return if (doors[doorToChoose] == 'o') RoundResult.WIN else RoundResult.LOSE
    }

    var hostOpenedDoor = 0
    for ((index, value) in doors.withIndex()) {
        if (value == 'x' && index != doorToChoose) {
            hostOpenedDoor = index
            break
        }
    }
    for ((index, value) in doors.withIndex()) {
        if (index != hostOpenedDoor && index != doorToChoose) {
            return if (value == 'o') RoundResult.WIN else RoundResult.LOSE
        }
    }
    throw IllegalStateException()
}
