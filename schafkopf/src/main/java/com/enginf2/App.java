package com.enginf2;

/**
 * Main method.
 */
public class App 
{
    public static void main( String[] args )
    {
        Game game = new Game(init_players());
        game.shuffle();
        game.distribute();
        game.setGameMode(game.getTurn(), GameMode.SAUSPIEL_EICHEL);
        System.out.println(game);
        while(true) {
            game.shuffle();
            game.distribute();
            for (int i = 0; i < Game.PLAYERCOUNT; i++) {
                game.playCard(game.getTurn(), getCardPlayed(game, game.getTurn()));
            }
            System.out.println(game);
        }
    }

    private static int getCardPlayed(Game game, Player player) {
        System.out.print(player.getName() + ", your hand: " + player.getHand().toString() + "\n -> Position of the card you want to play: ");
        int pos = -1;
        while(pos < 0 || pos >= player.getHand().size() || !game.move_is_valid(player, pos)) try{pos = Integer.parseInt(System.console().readLine());} catch(NumberFormatException e) {}
        return pos;
    }

    /**
     * Get all players as input from user.
     * @return the players
     */
    private static Player[] init_players() {
        Player players[] = new Player[Game.PLAYERCOUNT];
        for (int i = 0; i < Game.PLAYERCOUNT; i++) {
            System.out.print("Player " + (i+1) + ", enter name: ");
            String name = null;
            while(name == null) name = System.console().readLine();
            players[i] = new Player(name, i); 
        }
        return players;
    }
}
