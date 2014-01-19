//
//  Deck.h
//  Matchismo
//
//  Created by Guo Bingkun on 1/15/14.
//  Copyright (c) 2014 BingkunGuo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Card.h"

@interface Deck : NSObject

- (void)addCard:(Card *)card atTop:(BOOL)atTop;
- (void)addCard:(Card *)card;
- (Card *)drawRandomCard;

@end
