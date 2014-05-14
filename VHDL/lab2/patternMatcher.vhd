----------------------------------------------------------------------------
-- Pattern Matcher
-- 
-- Name: Bingkun Guo
-- Date: Feb/2014
-- Email: guobingkun@wustl.edu
-- Assignment: CSE260_SP2014 Lab1
-- File Name: patternMatcher.vhd
--
-- 
-- Document of my changes:
-- 
-- I designed 4 states for my state machine.
-- 
-- wait_A: Wait for letter 'a' to come in so we can start matching a string.
--
-- wait_BorC: After having letter 'a', wait for letter 'b' or letter 'c'
-- 
-- wait_More_B_D: Letter 'b' comes after letter 'a', so we'll wait for 
-- more 'b' to match the pattern. If letter 'd' comes and there are currently 3 'b',
-- we increment the patCount.
-- 
-- wait_D: Letter 'c' comes after letter 'a', so we only need a 'd' to complete the matching.
-- 
-- The code below is derived from my state diagram on the lab report
--
-- Added internal signal:
--
-- signal state: indicate the current state
-- countB: count how many Bs have been repeated
-- countMatch: count how many patterns have been matched 
-- copyRepCount: a copy of repCount
--
----------------------------------------------------------------------------
library IEEE;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
use ieee.std_logic_unsigned.all;
use work.commonDefs.all;

entity patternMatcher is port(
	clk, restart, valid: in std_logic;
	inSym: in nibble;
	repCount: in nibble;
	patCount: out byte);
end patternMatcher;

architecture a1 of patternMatcher is

type stateType is (wait_A, wait_BorC, wait_more_B_D, wait_D);
signal state : stateType; -- indicate the current state
signal countB : nibble := (others => '0'); -- count repeated Bs
signal countMatch : byte := (others => '0'); -- count patterns have been matched
signal copyRepCount : nibble := (others => '0'); -- a copy of repCount

begin
process (clk) begin
	if rising_edge(clk) then
		if restart = '1' then
			state <= wait_A;
			countB <= (others => '0');
			countMatch <= (others => '0');
			copyRepCount <= repCount;
		elsif valid = '1' then
			case state is
			-- Wait for letter 'a' to come in 
			when wait_A => if inSym = x"0" then state <= wait_BorC; end if;
			-- After having letter 'a', wait for letter 'b' or letter 'c'
			when wait_BorC => if inSym = x"1" then 
								      state <= wait_more_B_D;
										countB <= x"1";
								  elsif inSym = x"2" then 
								      state <= wait_D;
								  elsif inSym /= x"0" then
										state <= wait_A;
								  end if;
			-- Letter 'b' comes after letter 'a', so we'll wait for 
         -- more 'b' to match the pattern. If letter 'd' comes 
			-- and there are currently 3 'b',we increment the patCount.
			when wait_more_B_D => if inSym = x"1" and countB /= copyRepCount then
										countB <= countB + x"1";
									elsif inSym = x"3" and countB = copyRepCount then
										countMatch <= countMatch + x"1";
										countB <= (others => '0');
										state <= wait_A;
									elsif inSym = x"0" then
										countB <= (others => '0');
										state <= wait_BorC;
									else
										state <= wait_A;
										countB <= (others => '0');
									end if;
			-- wait_D: Letter 'c' comes after letter 'a', 
			-- so we only need a 'd' to complete the matching.						
			when wait_D => if inSym = x"3" then
									countMatch <= countMatch + x"1";
									countB <= (others => '0');
									state <= wait_A;
							  elsif inSym = x"0" then
							      countB <= (others => '0');
									state <= wait_BorC;
							  else
									countB <= (others => '0');
									state <= wait_A;
							  end if;
			when others =>
			end case;
		end if;
	end if;
end process;

patCount <= countMatch;			
			
end a1;
