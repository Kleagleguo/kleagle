//
//  CardMatchingGame.h
//  Matchismo
//
//  Created by Guo Bingkun on 1/16/14.
//  Copyright (c) 2014 BingkunGuo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Deck.h"

@interface CardMatchingGame : NSObject

typedef enum
{
    TWO_CARD_MATCH,
    THREE_CARD_MATCH
} GameModes;

typedef enum
{
    BONUS,
    PENALTY,
    CHOOSE
} TouchResult;

-(instancetype)initWithCardCount:(NSUInteger)count usingDeck:(Deck *)deck;
-(TouchResult)chooseCardAtIndex:(NSUInteger)index;
-(Card *)cardAtIndex:(NSUInteger)index;
-(void)resetGame:(NSUInteger)count usingDeck:(Deck *)deck;

@property (nonatomic, readonly) NSInteger score;
@property (nonatomic) GameModes gameMode;

@end
