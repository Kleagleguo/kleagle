//
//  SetViewController.m
//  Matchismo
//
//  Created by Guo Bingkun on 1/18/14.
//  Copyright (c) 2014 BingkunGuo. All rights reserved.
//

#import "SetViewController.h"
#import "SetCardMatchingGame.h"
#import "SetCardDeck.h"
#import "SetCard.h"

@interface SetViewController ()

@property (strong, nonatomic) IBOutletCollection(UIButton) NSArray *cardButtons;
@property (weak, nonatomic) IBOutlet UILabel *scoreLabel;
@property (strong, nonatomic) SetCardMatchingGame *game;
@property (nonatomic) TouchResult touchResult;
@property (strong, nonatomic) NSMutableArray *lastChoosenIndices;
@end

@implementation SetViewController

- (NSMutableArray *)lastChoosenIndices
{
    if (!_lastChoosenIndices) {
        _lastChoosenIndices = [[NSMutableArray alloc] init];
    }
    return _lastChoosenIndices;
}

- (SetCardMatchingGame *)game
{
    if (!_game) {
        _game = [[SetCardMatchingGame alloc] initWithCardCount:[self.cardButtons count] usingDeck:[self createDeck]];
    }
    return _game;
}

- (Deck *)createDeck
{
    return [[SetCardDeck alloc] init];
}

- (IBAction)touchCardButton:(UIButton *)sender
{
    NSUInteger chosenButtonIndex = [self.cardButtons indexOfObject:sender];
    [self.lastChoosenIndices addObject:[NSNumber numberWithUnsignedInteger:chosenButtonIndex]];
    self.touchResult = [self.game chooseCardAtIndex:chosenButtonIndex];
    [self updateUI];
}

- (void)updateUI
{
    if (self.touchResult == MATCH || self.touchResult == MISMATCH) {
        if ([self.lastChoosenIndices count] == 3) {
            NSUInteger index0 = [(NSNumber *)self.lastChoosenIndices[0] integerValue];
            NSUInteger index1 = [(NSNumber *)self.lastChoosenIndices[1] integerValue];
            NSUInteger index2 = [(NSNumber *)self.lastChoosenIndices[2] integerValue];
            NSLog(@"%lu", (unsigned long)index0);
            NSLog(@"%lu", (unsigned long)index1);
            NSLog(@"%lu", (unsigned long)index2);
            
            ((UIButton *)[self.cardButtons objectAtIndex:index0]).alpha = 1;
            ((UIButton *)[self.cardButtons objectAtIndex:index1]).alpha = 1;
            ((UIButton *)[self.cardButtons objectAtIndex:index2]).alpha = 1;
        }
        [self.lastChoosenIndices removeAllObjects];
    } else if (self.touchResult == CHOOSE) {
        
    }
    
    for (UIButton *cardButton in self.cardButtons) {
        NSUInteger cardButtonIndex = [self.cardButtons indexOfObject:cardButton];
        Card *card = [self.game cardAtIndex:cardButtonIndex];
        if (card.chosen) {
            cardButton.alpha = 0.5;
        }
        cardButton.enabled = !card.isMatched;
    }
    [self.scoreLabel setText:[NSString stringWithFormat:@"Score: %ld", (long)self.game.score]];
}

- (IBAction)resetGame:(UIButton *)sender
{
    [self.game resetGame:[self.cardButtons count] usingDeck:[self createDeck]];
    [self setupButtons];
}

- (void)setupButtons
{
    NSLog(@"num = %lu", (unsigned long)self.cardButtons.count);
    for (NSUInteger i = 0; i < [self.cardButtons count]; i++) {
        SetCard *card = (SetCard *)[self.game cardAtIndex:i];
        NSString *titleString = [[NSString alloc] init];
        if (card.number == 1) {
            titleString = card.symbol;
        } else if (card.number == 2) {
            titleString = [NSString stringWithFormat:@"%@%@", card.symbol, card.symbol];
        } else if (card.number == 3) {
            titleString = [NSString stringWithFormat:@"%@%@%@", card.symbol, card.symbol, card.symbol];
        } else {
            NSLog(@"what??!!  %lu", card.number);
        }
        
        NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
        paragraphStyle.alignment = NSTextAlignmentCenter;
        
        NSMutableDictionary *attributes = [[NSMutableDictionary alloc] init];
        
        [attributes addEntriesFromDictionary:@{ NSParagraphStyleAttributeName : paragraphStyle,
                                                NSForegroundColorAttributeName : card.color}];
        
        if ([card.shading isEqualToString:@"solid"]) {
            [attributes setObject:@-5 forKey:NSStrokeWidthAttributeName];
        } else if ([card.shading isEqualToString:@"striped"]) {
            //NSLog(@"here");
            [attributes addEntriesFromDictionary:@{
                                                   NSStrokeWidthAttributeName : @-5,
                                                   NSStrokeColorAttributeName : card.color,
                                                   NSForegroundColorAttributeName : [card.color colorWithAlphaComponent:0.1]
                                                   }];
        } else if ([card.shading isEqualToString:@"open"]) {
            [attributes setObject:@5 forKey:NSStrokeWidthAttributeName];
        }
        
        NSMutableAttributedString *title = [[NSMutableAttributedString alloc] initWithString:titleString];
        [title setAttributes:attributes range:NSMakeRange(0, titleString.length)];
        [self.cardButtons[i] setAlpha:1];
        [self.cardButtons[i] setAttributedTitle:title forState:UIControlStateNormal];
    }
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    [self setupButtons];
}

@end
