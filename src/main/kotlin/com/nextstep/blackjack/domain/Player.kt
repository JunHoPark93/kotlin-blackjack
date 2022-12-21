package com.nextstep.blackjack.domain

data class Player(val name: String) {
    private val basePlayerAction: BasePlayerAction = BasePlayerAction(name)

    fun isBeatDealer(dealer: Dealer): Boolean {
        if (isBust()) {
            return false
        }
        if (dealer.isBust()) {
            return true
        }
        return calculateScore() > dealer.calculateScore()
    }

    fun calculateScore(): Int {
        return basePlayerAction.calculateScore()
    }

    fun isBust(): Boolean {
        return basePlayerAction.isBust()
    }

    fun getCards(): List<Card> {
        return basePlayerAction.cards
    }

    fun draw(cards: List<Card>) {
        return basePlayerAction.addAll(cards)
    }
}
