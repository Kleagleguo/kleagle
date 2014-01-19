//
//  SetCardMatchingGame.m
//  Matchismo
//
//  Created by Guo Bingkun on 1/18/14.
//  Copyright (c) 2014 BingkunGuo. All rights reserved.
//

#import "SetCardMatchingGame.h"

@implementation SetCardMatchingGame

- (TouchResult)chooseCardAtIndex:(NSUInteger)index
{
    Card *card = [self cardAtIndex:index];
    BOOL bonus = NO;
    BOOL penalty = NO;
    
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
                        if (matchScore > 0) {
                            self.score += matchScore;
                            card.matched = YES;
                            ((Card *)[cardsToMatch firstObject]).matched = YES;
                            ((Card *)[cardsToMatch lastObject]).matched = YES;
                            bonus = YES;
                        } else if (matchScore < 0) {
                            self.score += matchScore;
                            ((Card *)[cardsToMatch firstObject]).chosen = NO;
                            ((Card *)[cardsToMatch lastObject]).chosen = NO;
                            penalty = YES;
                        }
                        break;
                    }
                }
            }
            if (penalty) {
                card.chosen = NO;
            } else {
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

@end
