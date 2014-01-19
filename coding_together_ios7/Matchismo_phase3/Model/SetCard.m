//
//  SetCard.m
//  Matchismo
//
//  Created by Guo Bingkun on 1/18/14.
//  Copyright (c) 2014 BingkunGuo. All rights reserved.
//

#import "SetCard.h"

@implementation SetCard

- (int)match:(NSArray *)otherCards
{
    int score = 0;
    BOOL match = NO;
    if (otherCards.count == 2) {
        SetCard *card1 = otherCards[0];
        SetCard *card2 = otherCards[1];
        
        if ( ([self.symbol isEqualToString:card1.symbol]
            && [self.symbol isEqualToString:card2.symbol]
            && [card1.symbol isEqualToString:card2.symbol])
            || ( ![self.symbol isEqualToString:card1.symbol]
                && ![self.symbol isEqualToString:card2.symbol]
                && ![card1.symbol isEqualToString:card2.symbol] ) ) {
                
            if ( ([self.shading isEqualToString:card1.shading]
                  && [self.shading isEqualToString:card2.shading]
                  && [card1.shading isEqualToString:card2.shading])
                  || (![self.shading isEqualToString:card1.shading]
                      && ![self.shading isEqualToString:card2.shading]
                      && ![card1.shading isEqualToString:card2.shading] ) ) {
                      
                      if ( (self.number == card1.number
                            && self.number == card2.number
                            && card1.number == card2.number)
                            || (self.number != card1.number
                                && self.number != card2.number
                                && card1.number != card2.number) ) {
                            
                                if ( ([self.color isEqual:card1.color]
                                      && [self.color isEqual:card2.color]
                                      && [card1.color isEqual:card2.color])
                                    || (![self.color isEqual:card1.color]
                                        && ![self.color isEqual:card2.color]
                                        && ![card1.color isEqual:card2.color]) ) {
                        
                                    match = YES;
                                }
                            
                            }
            
                  }
        
        
        }
        
        if (match) {
            score = 10;
        } else {
            score = -5;
        }
    }
    
    return score;
}

+ (NSArray *)validSymbol
{
    return @[@"▲", @"■", @"●"];
}

+ (NSArray *)validColor
{
    return @[[UIColor redColor], [UIColor greenColor], [UIColor purpleColor]];
}

+ (NSArray *)validShading
{
    return @[@"solid", @"striped", @"open"];
}

+ (NSArray *)validNumber
{
    return @[@1, @2, @3];
}

+ (NSUInteger)maxNumber
{
    return [self validNumber].count;
}

@end
