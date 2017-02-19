#BlackJack Program

This program simulates single deck BlackJack.

The rules for single deck BlackJack can be found below.

This program does not allow players to split or purchase insurance,
however players can double down.

Every time the player starts the program he/she starts with $10000 and can
bet any whole dollar amount. The game continues until the player decides to
quit or goes broke.

<a href="https://en.wikipedia.org/wiki/Blackjack#Rules_of_play_at_casinos">Single Deck BlackJack Rules</a>

Class Structure:

Main is located in VegasStakes. VegasStakes calls the public method twentyOne
in Casino. The twentyOne method controls player funds and instantiates a Table
object. The Table class implements the rules for BlackJack and instantiates a
Dealer object. The Dealer class controls the Deck, which contains a List of Card
objects.

Created for Skill Distillery by Matt Gmur
