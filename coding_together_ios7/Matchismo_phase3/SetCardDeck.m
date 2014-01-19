//
//  SetCardDeck.m
//  Matchismo
//
//  Created by Guo Bingkun on 1/18/14.
//  Copyright (c) 2014 BingkunGuo. All rights reserved.
//

#import "SetCardDeck.h"
#import "SetCard.h"

@implementation SetCardDeck

-(instancetype)init
{
    self = [super init];
    
    if (self) {
        for (NSString *shading in [SetCard validShading]) {
            for (NSString *symbol in [SetCard validSymbol]) {
                for (UIColor *color in [SetCard validColor]) {
                    for (NSUInteger number = 1; number <= [SetCard maxNumber]; number++) {
                        SetCard *card = [[SetCard alloc] init];
                        card.symbol = symbol;
                        card.number = number;
                        card.shading = shading;
                        card.color = color;
                        [self addCard:card];
                    }
                }
            }
        }
    }
    
    return self;
}

@end
