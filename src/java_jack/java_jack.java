package java_jack;

import java.util.*;

public class java_jack {
	
	static ArrayList deck = new ArrayList(52);
	static ArrayList player_hand = new ArrayList();
	static ArrayList dealer_hand = new ArrayList();
	static Random random_num = new Random();
	
	public static void shuffle_deck() {
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 4; j++) {
				deck.add((i+1));
			}
			Collections.shuffle(deck);
		}
		for (int k = 0; k < 52; k++) {
			if (deck.get(k).equals(1)) {
				deck.set(k, "Ace");
			}
			if (deck.get(k).equals(11)) {
				deck.set(k, "Jack");
			}
			if (deck.get(k).equals(12)) {
				deck.set(k, "Queen");
			}
			if (deck.get(k).equals(13)) {
				deck.set(k, "King");
			}
		}
//		System.out.println(deck);
	}
	
	public static void draw_card(ArrayList hand) {
		hand.add(deck.get(0));
		deck.remove(0);
	}
	
	public static void start_game() {
		draw_card(player_hand);
		draw_card(dealer_hand);
		draw_card(player_hand);
		draw_card(dealer_hand);
		
		hit_or_stay();
	}
	
	public static void convert_face_cards(ArrayList hand) {
		for (int i = 0; i < hand.size(); i++) {
			if ( (hand.get(i)).equals("Jack") || (hand.get(i)).equals("Queen") || (hand.get(i)).equals("King") ) {
				hand.set(i, 10);
			}
			if ((hand.get(i)).equals("Ace")) {
				hand.set(i, 11);
			}
		}
		
	}
	
	public static void convert_aces(ArrayList hand, int hand_value) {
		if (hand_value > 21) {
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i).equals(11)) {
					hand.set(i, 1);
				}
			}
		}
	}
	
	public static int total(ArrayList hand) {
		convert_face_cards(hand);
		int sum = 0;
		for (int i = 0; i < hand.size(); i++) {
			sum += (int) hand.get(i);
			convert_aces(hand, sum);
		}
		return sum;
	}
	
	public static void hit_or_stay() {
		Scanner user_input = new Scanner(System.in);
		while ((total(player_hand)) < 21) {
			System.out.println("Your hand " + player_hand);
			System.out.println("Dealer shows " + dealer_hand.get(0));
			System.out.println("Your current total is " + total(player_hand));
			System.out.println("Would you like to hit or stay");
			String player_choice = user_input.next();
			player_choice.toLowerCase();
			if (player_choice.equals("hit")) {
				draw_card(player_hand);
				total(player_hand);
				if ( (total(dealer_hand)) < 16) {
					draw_card(dealer_hand);
					}
				if ( (total(player_hand)) > 21) {
					System.out.println("Your hand " + player_hand);
					System.out.println("Your current total is " + total(player_hand));
					System.out.println("Bust, You lose!");
				}
				else if ( (total(player_hand)) == 21 ) {
					show_hands();
					System.out.println("You win!");
				}
			} else if (player_choice.equals("stay")) {
				determine_winner();
				break;
			}
		}
	}
	
	public static void determine_winner() {
		if ( (total(player_hand)) == (total(dealer_hand)) ) {
			show_hands();
			System.out.println("Tie game");
		}
		else if ( ( (total(player_hand)) < (total(dealer_hand)) ) && (total(dealer_hand)) <= 21) {
			show_hands();
			System.out.println("Dealer wins :(");
		}
		else if ( (total(player_hand)) > (total(dealer_hand)) ) {
			show_hands();
			System.out.println("You win!");
		}
		
	}
	
	public static void show_hands() {
		System.out.println("Your current total is " + total(player_hand));
		System.out.println("Dealer has " + total(dealer_hand));
	}

	public static void main(String[] args) {
		shuffle_deck();
		start_game();
	}

}
