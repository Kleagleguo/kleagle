//
//  SetCard.h
//  Matchismo
//
//  Created by Guo Bingkun on 1/18/14.
//  Copyright (c) 2014 BingkunGuo. All rights reserved.
//

#import "Card.h"

@interface SetCard : Card

@property (nonatomic) NSUInteger number;
@property (nonatomic, strong) NSString *symbol;
@property (nonatomic, strong) NSString *shading;
@property (nonatomic, strong) UIColor *color;

+ (NSArray *)validColor;
+ (NSArray *)validSymbol;
+ (NSArray *)validShading;
+ (NSUInteger)maxNumber;

@end
