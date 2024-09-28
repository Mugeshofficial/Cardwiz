import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList; 
import java.util.Random;

public class Cardwiz {
    private class Card {
        String type; // The type of the card (e.g., C, S, H, D)
        String value; // The value of the card (e.g., A, 2, 3, ..., 10, J, Q, K)

        Card(String t, String v) {
            this.type = t;
            this.value = v;
        }

        public String toString() {
            return value + "-" + type; // Corrected variable name
        }

        public int getValue() {
            try {
                switch (value) {
                    case "A":
                        return 11;
                    case "J":
                    case "Q":
                    case "K":
                        return 10;
                    default:
                        return Integer.parseInt(value); // This will now only apply to numeric values
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid card value for parsing: " + value);
                throw e; // Rethrow the exception for further handling if needed
            }
        }

        public boolean isAce() {
            return value.equals("A");
        }

        public String getImagePath(){
            return "./cards/"+toString()+".png";
        }
    }

    ArrayList<Card> deck;
    Random random = new Random();

    // Dealer card
    Card hiddenCard;
    ArrayList<Card> dealerHand;
    int dealerSum;
    int dealerAceCount;

    //player card
    ArrayList<Card> playerHand;
    int playerSum;
    int playerAceCount;

    //window-gui
    int boardWidth=600;
    int boardHeight=boardWidth;

    int cardWidth=110;
    int cardHeight=154;

    JFrame frame=new JFrame("Cardwiz");
    JPanel gamePanel=new JPanel(){
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            try {
                Image hiddenCardImg =new ImageIcon(getClass().getResource("./cards/Back.png")).getImage();
                if(!stayButton.isEnabled()){
                    hiddenCardImg=new ImageIcon(getClass().getResource(hiddenCard.getImagePath())).getImage();
                }
                g.drawImage(hiddenCardImg,20,20,cardWidth,cardHeight,null);

                //draw dealer's hand
                for (int i = 0; i < dealerHand.size(); i++) {
                    Card card=dealerHand.get(i);
                    Image cardImage=new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                    g.drawImage(cardImage,cardWidth+25+(cardWidth+5)*i,20,cardWidth,cardHeight,null);
                }

                //draw player's hand
                for (int i = 0; i < playerHand.size(); i++) {
                    Card card=playerHand.get(i);
                    Image cardImage=new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                    g.drawImage(cardImage,20+(cardWidth+5)*i,320,cardWidth,cardHeight,null);
                }

                if(!stayButton.isEnabled()){
                    dealerSum=reduceDealerAce();
                    playerSum=reducePlayerAce();
                    System.out.println("STAY: ");
                    System.out.println(dealerSum);
                    System.out.println(playerSum);

                    //winning coditions or rule
                    String message="";
                    if(playerSum>21){
                        message="YOU LOSE!";
                    }
                    else if(dealerSum>21){ 
                        message="YOU WIN!";
                    }
                    else if(dealerSum==playerSum){
                        message="TIE!";
                    }
                    else if(playerSum>dealerSum){
                        message="YOU WIN!";
                    }
                    else if(playerSum<dealerSum){
                        message="YOU LOSE!";
                    }
                    g.setFont(new Font("Arial",Font.PLAIN,30));
                    g.setColor(Color.white);
                    g.drawString(message,220,250);
                }


 
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    };

    JPanel buttonPanel=new JPanel();
    JButton hitButton=new JButton("Hit");
    JButton stayButton=new JButton("Stay");
    JButton restartButton = new JButton("Restart");
    
    public void resetGame() {
        deck.clear();
        buildDeck();
        shuffleDeck();
    
        dealerHand.clear();
        dealerSum = 0;
        dealerAceCount = 0;
    
        playerHand.clear();
        playerSum = 0;
        playerAceCount = 0;
    
        // Reset buttons
        hitButton.setEnabled(true);
        stayButton.setEnabled(true);
    
        startGame();
        
        gamePanel.repaint();
    }        



    Cardwiz() {
        startGame();

        frame.setVisible(true);
        frame.setSize(boardHeight,boardWidth);
        frame.setLocationRelativeTo(null);//for open in center of the risk
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(53,100,80));
        frame.add(gamePanel);

        hitButton.setFocusable(false);
        buttonPanel.add(hitButton);
        stayButton.setFocusable(false);
        buttonPanel.add(stayButton);
        restartButton.setFocusable(false);
        buttonPanel.add(restartButton);

        frame.add(buttonPanel,BorderLayout.SOUTH);

        hitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Card card=deck.remove(deck.size()-1);
                playerSum+=card.getValue();
                playerAceCount+=card.isAce()?1:0;
                playerHand.add(card);
                if(reducePlayerAce()>21){
                    hitButton.setEnabled(false);
                }
                gamePanel.repaint();
            }
        });
        //
        stayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                hitButton.setEnabled(false);
                stayButton.setEnabled(false);
                
                while(dealerSum < 17){
                    Card card =deck.remove(deck.size()-1);
                    dealerSum+=card.getValue();
                    dealerAceCount+=card.isAce()?1:0;
                    dealerHand.add(card);
                }
                gamePanel.repaint();
            }
        });

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        gamePanel.repaint();
        
    }

    public void startGame() {
        buildDeck();
        shuffleDeck();

        // Dealer side
        dealerHand = new ArrayList<Card>();
        dealerSum = 0;
        dealerAceCount = 0;

        hiddenCard = deck.remove(deck.size() - 1);
        dealerSum += hiddenCard.getValue();
        dealerAceCount += hiddenCard.isAce() ? 1 : 0;

        Card card = deck.remove(deck.size() - 1);
        dealerSum += card.getValue();
        dealerAceCount += card.isAce() ? 1 : 0;
        dealerHand.add(card);
        
        System.out.println("Dealer side");
        System.out.println(hiddenCard);
        System.out.println(dealerHand);
        System.out.println(dealerSum);
        System.out.println(dealerAceCount);

        // player 
        playerHand=new ArrayList<Card>();
        playerSum=0;
        playerAceCount=0;

        for (int i = 0; i < 2; i++) {
            card = deck.remove(deck.size() - 1);
            playerSum += card.getValue();
            playerAceCount += card.isAce() ? 1 : 0;
            playerHand.add(card);
        }

        System.out.println("Player side");
        System.out.println(playerHand);
        System.out.println(playerSum);
        System.out.println(playerAceCount);


    }

    public void buildDeck() {
        deck = new ArrayList<Card>(); // Corrected initialization
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; // Corrected array initialization
        String[] types = {"C", "S", "H", "D"}; // Corrected array initialization

        for (String type : types) {
            for (String value : values) {
                Card card = new Card(type, value);
                deck.add(card);
            }
        }
    }

    public void shuffleDeck() {
        for (int i = 0; i < deck.size(); i++) {
            int j = random.nextInt(deck.size());
            Card curCard = deck.get(i);
            Card randomCard = deck.get(j);
            deck.set(i, randomCard);
            deck.set(j, curCard);
        }
    }

    // dealer ace reduce spl case 
    public int reduceDealerAce(){
        while(dealerSum>21 && dealerAceCount>0){
            dealerSum-=10;
            dealerAceCount-=1;
        }
        return dealerSum;
    }

    //player ace reduce spl case 
    public int reducePlayerAce(){
        while(playerSum >21 && playerAceCount>0){
            playerSum-=10;
            playerAceCount-=1;
        }
        return playerSum;
    }
}
