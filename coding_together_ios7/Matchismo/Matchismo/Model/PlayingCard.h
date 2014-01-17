//
//  PlayingCard.h
//  Matchismo
//
//  Created by Guo Bingkun on 1/15/14.
//  Copyright (c) 2014 BingkunGuo. All rights reserved.
//

#import "Card.h"

@interface PlayingCard : Card

@property (strong, nonatomic) NSString *suit;

@property (nonatomic) NSUInteger rank;

+(NSArray *)validSuits;

+(NSUInteger)maxRank;

@end
