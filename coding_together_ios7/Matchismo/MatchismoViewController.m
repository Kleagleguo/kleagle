//
//  MatchismoViewController.m
//  Matchismo
//
//  Created by Guo Bingkun on 1/15/14.
//  Copyright (c) 2014 BingkunGuo. All rights reserved.
//

#import "MatchismoViewController.h"
#import "Deck.h"
#import "PlayingCardDeck.h"
#import "Card.h"

@interface MatchismoViewController ()
@property (weak, nonatomic) IBOutlet UILabel *flipsLabel;
@property (nonatomic) int flipCount;
@property (strong, nonatomic) Deck *deck;
@end

@implementation MatchismoViewController

-(Deck *)deck
{
    if (!_deck) {
        _deck = [[PlayingCardDeck alloc] init];
    }
    return _deck;
}

-(void)setFlipCount:(int)flipCount
{
    _flipCount = flipCount;
    [self.flipsLabel setText:[NSString stringWithFormat:@"Flips %d", flipCount]];
}

- (IBAction)touchCardButton:(UIButton *)sender
{
    if ([sender.currentTitle length]) {
        [sender setBackgroundImage:[UIImage imageNamed:@"cardback"] forState:UIControlStateNormal];
        [sender setTitle:@"" forState:UIControlStateNormal];
    } else {
        Card *card = [self.deck drawRandomCard];
        if (!card) {
            [sender setBackgroundImage:[UIImage imageNamed:@"cardback"] forState:UIControlStateNormal];
            [sender setTitle:@"" forState:UIControlStateNormal];
            [sender setEnabled:NO];
        } else {
            [sender setBackgroundImage:[UIImage imageNamed:@"cardfront"] forState:UIControlStateNormal];
            [sender setTitle:card.content forState:UIControlStateNormal];
        }
    }
    self.flipCount++;
}

@end
