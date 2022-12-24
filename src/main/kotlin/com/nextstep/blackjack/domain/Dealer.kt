package com.nextstep.blackjack.domain

data class Dealer(private var deck: Deck) {
    private val basePlayerAction: BasePlayerAction = BasePlayerAction("딜러")

    init {
        require(deck.isInitialState()) { "딜러는 초기화 상태의 덱으로 초기화해야합니다." }
    }

    fun initStage(players: Players) {
        basePlayerAction.addAll(deck.initialDraw())
        players.initState(deck)
    }

    fun draw() {
        basePlayerAction.addAll(listOf(deck.draw()))
    }

    fun draw(cards: List<Card>) {
        basePlayerAction.draw(cards)
    }

    fun isBust(): Boolean {
        return basePlayerAction.isBust()
    }

    fun isUpperThreshold(): Boolean {
        return basePlayerAction.calculateScore() > BlackJackConstants.DEALER_UPPER_BOUND
    }

    fun getBeatPlayer(players: Players): List<Player> {
        if (basePlayerAction.calculateScore() > BlackJackConstants.BLACKJACK_NUMBER) {
            return emptyList()
        }

        val bustPlayers = players.players.filter { it.isBust() }
        val beatPlayers =
            players.players.filter { !it.isBust() && it.calculateScore() <= basePlayerAction.calculateScore() }
        return bustPlayers + beatPlayers
    }

    fun calculateScore(): Int {
        return basePlayerAction.calculateScore()
    }

    fun getCards(): List<Card> {
        return basePlayerAction.cards
    }

    fun getFirstCard(): Card {
        return basePlayerAction.cards.first()
    }

    fun getName(): String {
        return basePlayerAction.name
    }
}
