//
//  CardMatchingGame.m
//  Matchismo
//
//  Created by Guo Bingkun on 1/16/14.
//  Copyright (c) 2014 BingkunGuo. All rights reserved.
//

#import "CardMatchingGame.h"

@interface CardMatchingGame ()
@property (nonatomic, strong, readwrite) NSMutableArray *cards;
@end

@implementation CardMatchingGame

#define MISMATCH_PENALTY 2
#define MATCH_BONUS 4
#define COST_TO_CHOOSE 1

- (TouchResult)chooseCardAtIndex:(NSUInteger)index
{
    Card *card = self.cards[index];
    BOOL bonus = NO;
    BOOL penalty = NO;
    
    if (self.gameMode == TWO_CARD_MATCH) {
        if (!card.isMatched) {
            if (card.isChosen) {
                card.chosen = NO;
            } else {
                for (Card *otherCard in self.cards) {
                    if (!otherCard.isMatched && otherCard.isChosen) {
                        int matchScore = [card match:@[otherCard]];
                        if (matchScore) {
                            self.score += matchScore * MATCH_BONUS;
                            card.matched = YES;
                            otherCard.matched = YES;
                            bonus = YES;
                        } else {
                            self.score -= MISMATCH_PENALTY;
                            otherCard.chosen = NO;
                            penalty = YES;
                        }
                        break;
                    }
                }
                self.score -= COST_TO_CHOOSE;
                card.chosen = YES;
            }
        }
    } else if (self.gameMode == THREE_CARD_MATCH) {
        if (!card.isMatched) {
            if (card.isChosen) {
                card.chosen = NO;
            } else {
                NSMutableArray *cardsToMatch = [[NSMutableArray alloc] init];
                for (Card *otherCard in self.cards) {
                    if (!otherCard.isMatched && otherCard.isChosen) {
                        [cardsToMatch addObject:otherCard];
                        if ([cardsToMatch count] == 2) {
                            int matchScore = [card match:cardsToMatch];
                            if (matchScore) {
                                self.score += matchScore * MATCH_BONUS;
                                card.matched = YES;
                                ((Card *)[cardsToMatch firstObject]).matched = YES;
                                ((Card *)[cardsToMatch lastObject]).matched = YES;
                                bonus = YES;
                            } else {
                                self.score -= MISMATCH_PENALTY;
                                ((Card *)[cardsToMatch firstObject]).chosen = NO;
                                ((Card *)[cardsToMatch lastObject]).chosen = NO;
                                penalty = YES;
                            }
                            break;
                        }
                    }
                }
                self.score -= COST_TO_CHOOSE;
                card.chosen = YES;
            }
        }
    }
    
    if (penalty) {
        return MISMATCH;
    }
    if (bonus) {
        return MATCH;
    }
    
    return CHOOSE;
}

- (Card *)cardAtIndex:(NSUInteger)index
{
    return (index < [self.cards count]) ? self.cards[index] : nil;
}

- (NSMutableArray *)cards
{
    if (!_cards) {
        _cards = [[NSMutableArray alloc] init];
    }
    
    return _cards;
}

- (void)resetGame:(NSUInteger)count usingDeck:(Deck *)deck
{
    self.score = 0;
    self.cards = nil;
    for (int i = 0; i < count; i++) {
        Card *card = [deck drawRandomCard];
        if (card) {
            [self.cards addObject:card];
        }
    }
}

- (instancetype)initWithCardCount:(NSUInteger)count usingDeck:(Deck *)deck
{
    self = [super init];
        
    if (self) {
        for (int i = 0; i < count; i++) {
            Card *card = [deck drawRandomCard];
            if (card) {
                [self.cards addObject:card];
            } else {
                self = nil;
                break;
            }

        }
    }
    
    return self;
}

@end
