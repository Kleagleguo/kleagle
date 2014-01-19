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
#import "CardMatchingGame.h"

@interface MatchismoViewController ()
@property (strong, nonatomic) IBOutletCollection(UIButton) NSArray *cardButtons;
@property (strong, nonatomic) CardMatchingGame *game;
@property (weak, nonatomic) IBOutlet UILabel *scoreLabel;
@property (weak, nonatomic) IBOutlet UISegmentedControl *modeSwitch;
@property (strong, nonatomic) NSMutableArray *lastChoosenCards;
@property (weak, nonatomic) IBOutlet UILabel *resultAfterChoose;
@property (nonatomic) TouchResult touchResult;
@end

@implementation MatchismoViewController

- (NSMutableArray *)lastChoosenCards
{
    if (!_lastChoosenCards) {
        _lastChoosenCards = [[NSMutableArray alloc] init];
    }
    
    return _lastChoosenCards;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.modeSwitch.enabled = NO;
}

- (CardMatchingGame *)game
{
    if (!_game) {
        _game = [[CardMatchingGame alloc] initWithCardCount:[self.cardButtons count]
                                                  usingDeck:[self creatDeck]];
    }
    _game.gameMode = TWO_CARD_MATCH;
    return _game;
}

- (Deck *)creatDeck
{
    return [[PlayingCardDeck alloc] init];
}

- (IBAction)touchCardButton:(UIButton *)sender
{
    NSUInteger chosenButtonIndex = [self.cardButtons indexOfObject:sender];
    [self.lastChoosenCards addObject:[self.game cardAtIndex:chosenButtonIndex]];
    self.touchResult = [self.game chooseCardAtIndex:chosenButtonIndex];
    [self updateUI];
}

- (IBAction)resetGame:(UIButton *)sender
{
    [self.resultAfterChoose setText:@""];
    [self.lastChoosenCards removeAllObjects];
    [self.game resetGame:[self.cardButtons count] usingDeck:[self creatDeck]];
    [self.scoreLabel setText:[NSString stringWithFormat:@"Score: %ld", (long)self.game.score]];
    for (UIButton *cardButton in self.cardButtons) {
        cardButton.enabled = YES;
        [cardButton setTitle:@"" forState:UIControlStateNormal];
        [cardButton setBackgroundImage:[UIImage imageNamed:@"cardback"] forState:UIControlStateNormal];
    }
    self.modeSwitch.enabled = YES;
}

- (void)updateUI
{
    if (self.touchResult == MATCH) {
        if ([self.lastChoosenCards count] == 2) {
            [self.resultAfterChoose setText:[NSString stringWithFormat:@"Matched %@ %@",
                                             [self.lastChoosenCards firstObject], [self.lastChoosenCards lastObject]]];
        } else if ([self.lastChoosenCards count] == 3) {
            [self.resultAfterChoose setText:[NSString stringWithFormat:@"Matched %@ %@ %@",
                                             self.lastChoosenCards[0], self.lastChoosenCards[1], self.lastChoosenCards[2]]];
        }
        [self.lastChoosenCards removeAllObjects];
    } else if (self.touchResult == MISMATCH) {
        if ([self.lastChoosenCards count] == 2) {
            [self.resultAfterChoose setText:[NSString stringWithFormat:@"%@ %@ don't match",
                                             [self.lastChoosenCards firstObject], [self.lastChoosenCards lastObject]]];
        } else if ([self.lastChoosenCards count] == 3) {
            [self.resultAfterChoose setText:[NSString stringWithFormat:@"%@ %@ %@ don't match",
                                             self.lastChoosenCards[0], self.lastChoosenCards[1], self.lastChoosenCards[2]]];
        }
        NSUInteger removeNum = self.lastChoosenCards.count - 1;
        for (int i = 0; i < removeNum; i++) {
            NSLog(@"removing %@", self.lastChoosenCards[0]);
            [self.lastChoosenCards removeObjectAtIndex:0];
        }
    } else if (self.touchResult == CHOOSE) {
        [self.resultAfterChoose setText:[NSString stringWithFormat:@"chose %@", [self.lastChoosenCards lastObject]]];
    }
    
    NSLog(@"---");
    for (int i = 0; i < self.lastChoosenCards.count; i++) {
        NSLog(@"%@", self.lastChoosenCards[i]);
    }
    NSLog(@"---");
    
    for (UIButton *cardButton in self.cardButtons) {
        NSUInteger cardButtonIndex = [self.cardButtons indexOfObject:cardButton];
        Card *card = [self.game cardAtIndex:cardButtonIndex];
        [cardButton setTitle:[self titleForCard:card] forState:UIControlStateNormal];
        [cardButton setBackgroundImage:[self backgroundImageForCard:card]
                              forState:UIControlStateNormal];
        cardButton.enabled = !card.isMatched;
    }
    [self.scoreLabel setText:[NSString stringWithFormat:@"Score: %ld", (long)self.game.score]];
}

- (IBAction)modeChanged:(UISegmentedControl *)sender
{
    if (sender.selectedSegmentIndex == 0) {
        //NSLog(@"2 card match");
        self.game.gameMode = TWO_CARD_MATCH;
    } else if (sender.selectedSegmentIndex == 1) {
        //NSLog(@"3 card match");
        self.game.gameMode = THREE_CARD_MATCH;
    }
}

- (NSString *)titleForCard:(Card *)card
{
    return card.isChosen ? card.content : @"";
}

- (UIImage *)backgroundImageForCard:(Card *)card
{
    return [UIImage imageNamed:(card.isChosen ? @"cardfront" : @"cardback")];
}

@end
