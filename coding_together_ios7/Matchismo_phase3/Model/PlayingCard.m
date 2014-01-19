//
//  PlayingCard.m
//  Matchismo
//
//  Created by Guo Bingkun on 1/15/14.
//  Copyright (c) 2014 BingkunGuo. All rights reserved.
//

#import "PlayingCard.h"

@implementation PlayingCard

@synthesize suit = _suit;

- (int)match:(NSArray *)otherCards
{
    int score = 0;
    if ([otherCards count] == 1) {
        PlayingCard *otherCard = [otherCards firstObject];
        if (self.rank == otherCard.rank) {
            score = 4;
        } else if ([self.suit isEqualToString:otherCard.suit]) {
            score = 1;
        }
    } else if ([otherCards count] == 2) {
        PlayingCard *first = [otherCards firstObject];
        PlayingCard *second = [otherCards lastObject];
        if (first.rank == second.rank == self.rank) {
            score = 100;
        } else if ([first.suit isEqualToString:second.suit] && [first.suit isEqualToString:self.suit]) {
            score = 25;
        } else if (first.rank == second.rank || first.rank == self.rank || second.rank == self.rank) {
            score = 4;
        } else if ([first.suit isEqualToString:second.suit] || [first.suit isEqualToString:self.suit]
                   || [second.suit isEqualToString:self.suit]) {
            score = 1;
        }
    }
    
    return score;
}

- (NSString *)content
{
    NSArray *rankingString = [PlayingCard rankStrings];
    return [rankingString[self.rank] stringByAppendingString:self.suit];
}

+ (NSArray *)validSuits
{
    return @[@"♠️", @"♣️", @"♥️", @"♦️"];
}

+ (NSArray *)rankStrings
{
    return @[@"?", @"A", @"2", @"3",@"4", @"5", @"6", @"7", @"8", @"9",@"10", @"J", @"Q", @"K"];
}

+ (NSUInteger)maxRank
{
    return [[PlayingCard rankStrings] count] - 1;
}

- (void)setRank:(NSUInteger)rank
{
    if (rank <= [PlayingCard maxRank]) {
        _rank = rank;
    }
}

- (void)setSuit:(NSString *)suit
{
    if ([[PlayingCard validSuits] containsObject:suit]) {
        _suit = suit;
    }
}

- (NSString *) suit
{
    return _suit ? _suit : @"?";
}

- (NSString *)description
{
    return self.content;
}

@end
